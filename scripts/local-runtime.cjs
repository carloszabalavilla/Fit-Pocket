// Windows local demo runtime. Uses an isolated MySQL data directory and loopback ports.
const fs=require('node:fs'),path=require('node:path'),net=require('node:net');
const {spawn}=require('node:child_process');
const root=fs.existsSync(path.join(__dirname,'demo-runtime.json'))?__dirname:path.resolve(__dirname,'..');
const config=JSON.parse(fs.readFileSync(path.join(root,'demo-runtime.json'),'utf8'));
const runtime=path.join(root,'.runtime');fs.mkdirSync(runtime,{recursive:true});
const mysqlHome=process.env.MYSQL_HOME||path.join(process.env.ProgramFiles||'C:\\Program Files','MySQL','MySQL Server 8.0');
const binary=name=>path.join(mysqlHome,'bin',name+'.exe');
const log=fs.createWriteStream(path.join(runtime,'demo.log'),{flags:'w'});
let db,app,port,closing=false;
function run(command,args,options={}){
 return new Promise((resolve,reject)=>{
  const child=spawn(command,args,{cwd:root,windowsHide:true,...options,stdio:['pipe','pipe','pipe']});
  child.stdout.pipe(log,{end:false});child.stderr.pipe(log,{end:false});
  child.on('error',reject);child.on('exit',code=>code===0?resolve():reject(new Error(path.basename(command)+' terminó con código '+code)));
  child.stdin.end(options.input||'');
 });
}
async function stop(code=0){
 if(closing)return;closing=true;
 if(app&&!app.killed)app.kill();
 if(db&&!db.killed){try{await run(binary('mysqladmin'),['--no-defaults','--protocol=TCP','-h','127.0.0.1','-P',String(port),'-u','root','shutdown']);}catch{db.kill();}}
 process.exitCode=code;
}
process.on('SIGINT',()=>stop());process.on('SIGTERM',()=>stop());
async function main(){
 if(!fs.existsSync(binary('mysqld')))throw new Error('Instala MySQL Server 8.0 o define MYSQL_HOME. No se usará ni modificará tu servicio MySQL existente.');
 const reservation=net.createServer();await new Promise((res,rej)=>{reservation.on('error',rej);reservation.listen(0,'127.0.0.1',res);});port=reservation.address().port;await new Promise(res=>reservation.close(res));
 const data=path.join(runtime,'mysql');const fresh=!fs.existsSync(path.join(data,'mysql'));
 if(fresh){fs.mkdirSync(data,{recursive:true});await run(binary('mysqld'),['--no-defaults','--initialize-insecure','--datadir='+data,'--console']);}
 db=spawn(binary('mysqld'),['--no-defaults','--datadir='+data,'--bind-address=127.0.0.1','--port='+port,'--mysqlx=OFF','--console'],{windowsHide:true,stdio:['ignore','pipe','pipe']});
 db.stdout.pipe(log,{end:false});db.stderr.pipe(log,{end:false});db.on('error',error=>{console.error(error.message);stop(1);});
 db.on('exit',()=>{if(!closing){console.error('No se pudo iniciar la base de demostración. Cierra otras instancias de esta carpeta y consulta .runtime/demo.log.');stop(1);}});
 const client=['--no-defaults','--connect-timeout=1','--protocol=TCP','-h','127.0.0.1','-P',String(port),'-u','root'];
 let ready=false;for(let i=0;i<100&&!closing;i++){try{await run(binary('mysql'),[...client,'-e','SELECT 1']);ready=true;break;}catch{await new Promise(res=>setTimeout(res,500));}}
 if(!ready)throw new Error('MySQL no terminó de arrancar. Consulta .runtime/demo.log.');
 if(fresh){const sql=config.sql.map(file=>fs.readFileSync(path.join(root,file),'utf8')).join('\n');await run(binary('mysql'),client,{input:sql});}
 let command,args,env={...process.env};
 if(config.kind==='fit'){
  const tomcat=path.join(root,'tomcat');
  command=process.env.JAVA_HOME?path.join(process.env.JAVA_HOME,'bin','java.exe'):'java';
  args=['-Dcatalina.home='+tomcat,'-Dcatalina.base='+tomcat,'-Djava.io.tmpdir='+path.join(tomcat,'temp'),'-classpath',path.join(tomcat,'bin','bootstrap.jar')+';'+path.join(tomcat,'bin','tomcat-juli.jar'),'org.apache.catalina.startup.Bootstrap','start'];
  env={...env,DB_URL:'jdbc:mysql://127.0.0.1:'+port+'/fitpocket?serverTimezone=UTC',DB_USERNAME:'root',DB_PASSWORD:'',LOCAL_MAIL_DIR:path.join(runtime,'mail'),FIT_API_URL:'http://127.0.0.1:8083/webService/webapi/'};
 }else{
  const php=process.env.PHP_HOME||path.join(root,'php');command=path.join(php,'php.exe');
  args=['-d','extension_dir='+path.join(php,'ext'),'-d','extension=mysqli','-S','127.0.0.1:8084','-t',path.join(root,'www')];
  env={...env,DB_HOST:'127.0.0.1',DB_PORT:String(port),DB_USERNAME:'root',DB_PASSWORD:'',DB_NAME:'catalogo'};
 }
 app=spawn(command,args,{cwd:root,env,windowsHide:true,stdio:['ignore','pipe','pipe']});app.stdout.pipe(log,{end:false});app.stderr.pipe(log,{end:false});
 app.on('error',error=>{console.error(error.message);stop(1);});app.on('exit',()=>{if(!closing){console.error('El servidor se ha detenido. Consulta .runtime/demo.log.');stop(1);}});
 for(let i=0;i<120&&!closing;i++){try{const res=await fetch(config.check,{signal:AbortSignal.timeout(1000)});if(res.ok){console.log(config.name+' listo: '+config.url);console.log('Ctrl+C para detener. Los datos quedan en .runtime/mysql, separados de cualquier base personal.');return;}}catch{}await new Promise(res=>setTimeout(res,500));}
 throw new Error('La aplicación no terminó de arrancar. Consulta .runtime/demo.log.');
}
main().catch(error=>{console.error(error.message);stop(1);});
