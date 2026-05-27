const API = "http://localhost:8080/api";

function initAuthPages(isRegister){

const form=document.getElementById(
isRegister?"registerForm":"loginForm"
);

if(!form)return;

form.addEventListener("submit",async(e)=>{

e.preventDefault();

const username=document.getElementById(
isRegister?"reg-username":"login-username"
).value;

const password=document.getElementById(
isRegister?"reg-password":"login-password"
).value;

try{

const response=await fetch(
isRegister?
`${API}/auth/register`:
`${API}/auth/login`,
{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
username,
password
})
}
);

const data=await response.json();

console.log(data);

alert("Success");

window.location.href="index.html";

}catch(err){

console.log(err);
alert("Request failed");

}

});

}