const fs=require('fs'),path=require('path'),assert=require('assert/strict'),crypto=require('crypto');
const base='http://127.0.0.1:8083/ProyectoPrueba',api='http://127.0.0.1:8083/webService/webapi';
const directory=path.resolve(process.env.FIT_PACKAGE_ROOT || '.', '.runtime/mail');
(async()=>{
 const contact=await fetch(base+'/contact-servlet',{method:'POST',body:new URLSearchParams({nameContact:'Demo test',emailContact:'test@example.test',contactMessage:'Fictional local test'})});
 assert.equal(contact.status,200);assert((await contact.text()).includes('Hemos recibido'));
 const profile=await (await fetch(api+'/profile/id/1')).json();assert.equal(profile.userId,1);
 const recovery=await fetch(base+'/restore-password-servlet',{method:'POST',body:new URLSearchParams({email:profile.email})});
 assert.equal(recovery.status,200);assert((await recovery.text()).includes('Se ha enviado un email'));
 let password;
 for(const file of fs.readdirSync(directory)){
  const raw=fs.readFileSync(path.join(directory,file),'utf8');
  let body=raw.slice(raw.indexOf('\r\n\r\n')+4);
  if(/Content-Transfer-Encoding:\s*base64/i.test(raw))body=Buffer.from(body.replace(/\s/g,''),'base64').toString('utf8');
  else body=body.replace(/=\r?\n/g,'').replace(/=([0-9a-f]{2})/gi,(_,hex)=>String.fromCharCode(parseInt(hex,16)));
  const match=body.match(/nueva contrase[^:]*:\s*([A-Za-z0-9]{8})/);if(match)password=match[1];
 }
 assert(password,'No recovery message was captured');
 const login=await fetch(base+'/login-servlet',{method:'POST',redirect:'manual',body:new URLSearchParams({userName:'demo',userPassword:password})});assert.equal(login.status,302);
 const user=await (await fetch(api+'/user/id/1')).json();user.userPassword=crypto.createHash('sha256').update('DemoOnly123!').digest('hex');
 const reset=await fetch(api+'/user/update',{method:'PUT',headers:{'Content-Type':'application/json'},body:JSON.stringify(user)});assert.equal(await reset.json(),true);
 console.log('Fit-Pocket: contacto y recuperación capturados localmente; nuevo acceso comprobado y credencial ficticia restaurada. No se envió correo real.');
})().catch(error=>{console.error(error.message);process.exitCode=1;});
