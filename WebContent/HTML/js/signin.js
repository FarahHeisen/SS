function connexion(formulaire){
	var login = formulaire.login.value;
	var pwd = formulaire.pwd.value;
	console.log(login, pwd);
	if(verifFormSig(login,pwd))
		connect(login, pwd);
}

function connect(login, password){
	$.ajax({
		type : "GET",
		url : "http://li328.lip6.fr:8280/BERTRAND_DHOUIB/log",
		data : "log=" + login + "&pwd=" + password,
		dataType : "json",
		contentType : "application/json",
		success : traiteReponseConnexion,
		cache : true,
		error : function(XHR, testStatus, errorThrown) {
			alert(XHR + "\n" + testStatus + "\n" + errorThrown);}
		});
}


function traiteReponseConnexion(el){
	if(el.error != undefined){
		console.log("ici" +el.id);
	}else{
		$("#signinModal").modal('hide');
		window.location.href="main.jsp?id="+el.id+"&login="+el.login+"&key="+el.key+"";
		//main(el.id, el.login, el.key);
	}
}

function verifFormSig(login, pwd){
	if((login==undefined)||(login.length==0)){
		funcError("Login required !");
		return false;
	}
	if(login.length>20){
		funcError("Login too long"); 
		return false;
	}
	if((pwd==undefined)||(pwd.length==0)){
		funcError("Password required !");
		return false;
	}
	if(pwd.length>20){
		funcError("Password too long"); 
		return false;
	}
	return true;
}

function funcError(msg){
	var box ="<div id='msgErreurConnexion'>"+msg+"</div>";
	var e= $("#msgErreurConnexion");
	if(e.length == 0){
		$("form").prepend(box);
	}else{
		$("#msgErreurConnexion").replacewith(box);
	}
	$("#msgErreurConnexion").css({"color":"red","margin-top":"20px","margin-bottom":"40px","margin-left":"40px","word-wrap":"break-word"});
}