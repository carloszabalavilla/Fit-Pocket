const assert=require('node:assert/strict');
const results=[];
async function request(url,method='GET',body,headers={}){
 const res=await fetch(url,{method,headers:body?{'Content-Type':'application/json',...headers}:headers,body:body?JSON.stringify(body):undefined,redirect:'manual',signal:AbortSignal.timeout(15000)});
 const text=await res.text();let data;try{data=JSON.parse(text);}catch{data=text;}
 assert(res.status<500,method+' '+url+' returned '+res.status);
 return {res,data};
}
async function fit(){
 const base='http://127.0.0.1:8083/webService/webapi/';
 assert.equal((await request(base+'user/ping')).res.status,200);
 const budget=(await request(base+'budget/allbyuserid/1')).data[0];assert(budget.budgetId>0);assert(Array.isArray(budget.incomeList));
 for(const kind of ['income','expense']){
  const body={budgetId:1,[kind+'Name']:'Smoke '+kind+' '+Date.now(),description:'Fictional',categoryId:1,amount:12.5,recurrent:false};
  assert.equal((await request(base+kind+'/create','POST',body)).data,true);
  const item=(await request(base+kind+'/all')).data.find(x=>x[kind+'Name']===body[kind+'Name']);assert(item);body[kind+'Id']=item[kind+'Id'];
  body.amount=7.25;body.recurrent=true;
  assert.equal((await request(base+kind+'/update','PUT',body)).data,true);
  const updated=(await request(base+kind+'/id/'+body[kind+'Id'])).data;assert.equal(updated.amount,7.25);assert.equal(updated.isRecurrent??updated.recurrent,true);
  assert.equal((await request(base+kind+'/delete','POST',body)).data,true);
 }
 const web='http://127.0.0.1:8083/ProyectoPrueba';
 assert.equal((await request(web+'/')).res.status,200);
 const login=await fetch(web+'/login-servlet',{method:'POST',body:new URLSearchParams({userName:'demo',userPassword:'DemoOnly123!'}),redirect:'manual'});
 assert.equal(login.status,302);assert(login.headers.get('location').includes('/jsp/common/home.jsp'));
 const cookie=login.headers.get('set-cookie').split(';')[0];assert.equal((await request(new URL(login.headers.get('location'),web).href,'GET',undefined,{cookie})).res.status,200);
 results.push('Fit-Pocket: lectura de presupuesto, ingresos/gastos CRUD e inicio de sesión JSP correctos');
}

fit().then(()=>console.log(results.join("\n"))).catch(error=>{console.error(error.message);process.exitCode=1;});
