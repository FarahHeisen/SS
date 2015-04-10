sfunction inscription(formulaire){
	var login = formulaire.login.value;
	var pass = formulaire.pwd.value;
	var passconfirm = formulaire.pwd_c.value;
	var prenom = formulaire.prenom.value;	
	var nom = formulaire.nom.value;
	console.log("avant verif form");
	var ok = verifFormReg(login, pass,passconfirm);
	if (ok){
		console.log("ok");
		register(login, pass);
	}
}

function inscr(formulaire){
	var login = formulaire.login.value;
	var pass = formulaire.pwd.value;
	var passconfirm = formulaire.pwd_c.value;
	var prenom = formulaire.prenom.value;	
	var nom = formulaire.nom.value;
	console.log("avant verif form");
	var ok = verifFormReg(login, pass,passconfirm);
	if (ok){
		console.log("ok");
		register(login,pass,prenom,nom);
	}
}

function register(login, pwd,prenom,nom){
	$.ajax({
		type:"GET", //post
		url:"http://li328.lip6.fr:8280/BERTRAND_DHOUIB/register", //<- servlet 
		data:"log="+login+"&pwd="+pwd+"&prenom="+prenom+"&nom="+nom,
		dataType:"json",//text, html, script
		success:traiteReponseInscription,
		error:function(jqXHR,textStatus,errorThrown){
			alert(jqXHR+ " "+textStatus+ " " + errorThrown);}});
}



function traiteReponseInscription(rep){
	if(rep.erreur != undefined){
		alert(rep.erreur);
	}else{
		$("#signupModal").modal('hide');
		afficherBienvenu(rep.login);
	}

}



function afficherBienvenu(login){
	$("#welcome").append("<div id=\"welcomeModal\" class=\"modal modal-wide fade\" <div class=\"modal-dialog modal-sm\"> <div class=\"modal-content\"> <div class=\"modal-header\"> <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">×</button> <h4 class=\"modal-title text-center\">Welcome !</h4> </div> <div class=\"modal-body\"> Bienvenue sur social Stalker "+ login +"! Le site où vous pouvez stalker qui vous voulez quand vous voulez. </div> </div> </div>");
	window.setTimeout($("#welcome").modal("show"),10000);
}

function verifFormReg(login, pass, passconfirm){
	if((login==undefined)||(login.length==0)){
		funcError("Login required !");
		return false;
	}
	if(login.length>20){
		funcError("Login too long..."); 
		return false;
	}
	if((pass==undefined)||(pass.length==0)){
		funcError("Password required !");
		return false;
	}
	if(pass.length>20){
		funcError("Password too long..."); 
		return false;
	}
	if(pass.length<8){
		funcError("Password too short..."); 
		return false;
	}
	if(pass != passconfirm){
		funcError("Mot de passe mal reecrit");
		return false;
	}
	return true;
}

function funcError(msg){
	var box ="<div id='msgErreurConnexion'>"+msg+"</div>"
	var e= $("#msgErreurConnexion");
	if(e.length == 0){
		$("form").prepend(box);
	}else{
		$("#msgErreurConnexion").replacewith(box);
	}
	$("#msgErreurConnexion").css({"color":"red","margin-top":"20px","margin-bottom":"30px","margin-left":"40px","word-wrap":"break-word"});
}