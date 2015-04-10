// function main(){ /* se lance au chargement */
// 	var env = new Object(); /* ou env = {} */
// 	env.users = new Array(); /* env.users = [] */
// }
var env = new Object();

function main(id,login,key){ /* se lance au chargement */
	// var env = new Object(); /* ou env = {} */
	env.users = new Array(); /* env.users = [] */
		console.log(main);
	if((id != null) && (login != null) && (key != null)){
		env.key=key;
		env.actif = new User(id,login);
		gererDivConnexion();
		search();
		$("#link_disconnect").click(disconnect);
	}
}

function gererDivConnexion(){
	var user = env.actif;
	console.log("gererDiv");
	if(user != undefined){
		console.log(env.actif.id);
		$("#divconnexion").html(
				"<a id=\"link_disconnect\" href=\"javascript:void(0)\"> <button role='button' class='button' id='disconnect'>Disconnect "+ env.actif.login +"</button> </a>");
		console.log(env.actif.login);
		console.log("divconnexion");
	}else{
		$("#divconnexion").html("<button href='#signinModal' role='button' class='button' data-toggle='modal'>Sign in</button> <button href='#signupModal' role='button' class='button' data-toggle='modal'>Sign up</button>");
	}
}




/* sur la page html main.html on ajoute dans la balise <body onload="javascript main()" > */

function User(id,login,contact){ /* majuscule pour les objects */
	this.id = id;
	this.login = login;
	if (contact == undefined)
		this.contact = false;
	this.contact = contact; 
	if (env.users != undefined)
		env.users[id] = this;
	/*ne jamais définir de fonction à l'intérieur d'un object !!!!!!!!!! */
}



/* pour les fonctions associés aux object on les met dans les prototype des fonctions pour pointer tous les objects vers la même fonction */

User.prototype.modifyStatus = function(){ this.contact = !this.contact; }; /* renverse la valeur du booléen */

/* RechercheCommentaire */



function traiteReponseCommentaireJSON(jsontext) {
	// var obj = JSON.parse(jsontext,revive);
	// if (obj.erreur == undefined){
	// 	alert(obj.getHTML());
	// }
	// else{
	// 	alert(obj.erreur);
	// }
}

function envoiCommentaire (){ 		///////// A L'EXAMEN !!!
	var user1 = new User(1,"Jean",false);
	var user2 = new User(45,"Tom",false);
	var user3 = new User(254,"Marie");
	var com1 = new Connexion(23,user2,"blabla", new Date(),45);
	var com2 = new Connexion(36,user1,"blablaB", new Date(),1);
	var com2 = new Connexion(254,user1,"blablaR", new Date(),45);

	var tab = [com1,com2,com3];
	var res = new RechercheCommentaire(tab,"test", false, user3);
	return JSON.stringify(res); /* serialise au format JSON */
}

function revive (key, value){
	if (key="auteur"){ // le cas pour le user
		return new User(value.id, value.login, value.contact);
	}
	if(key="login"){
		env.key = value.key;
		return new User(value.id, value.login,undefined);

	}
	if(key=""){}

}



function search(){
	var friends = ($("#check_friends").checked?1:0);
	var query = $("request").val;
	$.ajax({
		type: "GET",
		url:"http://li328.lip6.fr:8280/BERTRAND_DHOUIB/search",
		data:"",// pour utiliser revival
		dataType: "text",
		success:traiteReponseCommentaireJSON,
		error:function(jqXHR,textStatus,errorThrown){
			alert(jqXHR+ " "+textStatus+ " " + errorThrown);

		}
	});
}



/**
 * Creer un tweet
 * 
 * @param id
 * @param auteur
 * @param texte
 * @param date
 * @param score
 * @param hashtad
 * @param citation_id
 * @returns un objet message
 */
 function Tweet(id_msg, id, auteur, texte, date, score, hashtag, sharedBy) {
 	this.idmsg = id_msg;
 	this.id = id;
 	this.auteur = auteur;
 	this.texte = texte;
 	this.date = date;
 	this.score=score;
 	this.hastag = like;
 	this.sharedBy=sharedBy;
 }
/**
	A FAIRE
 * Renvoie le html d'un message
 * 
 * @param message
 * @returns {String}
 */

 function ajout_sup_contact(id){
 	var user="http://li328.lip6.fr:8280/BERTRAND_DHOUIB/addFriend"; // a vérifier avec servlet
 	if(user.contact){
 		url="http://li328.lip6.fr:8280/BERTRAND_DHOUIB/removeFriend";
 	}
 	$.ajax({
 		type:"GET",
 		url:url,
 		data:"key="+env.key+"&id_friend="+id,
 		dataType:"json",
 		success:function(rep){traiteResponseAjouteSup(rep,id);},
 		error:function(jqXHR,textStatus,errorThrown){
 			alert(jqXHR+ " "+textStatus+ " " + errorThrown);	}
 		});
 }


 function add_new_com(text){
 	$.ajax({
 		type: "GET",
 		url:"http://li328.lip6.fr:8280/BERTRAND_DHOUIB/search",
 		data:"key="+env.key+"&text="+id,
 		dataType: "jsonp",
 		success:traiteResponseNewCom,
 		error:function(jqXHR,textStatus,errorThrown){
 			alert(jqXHR+ " "+textStatus+ " " + errorThrown);
 		}
 	});
 }

 function traiteResponseNewCom(rep){
 	if(rep.erreur != undefined){
 		alert(rep.erreur);
 		disconnect();
 	}else{
 		search();
 	}

 }


/**
 * deconnection, suppresion de l'environnement, modification de la page,
 * affichage des tweet de base par appel de la fonction search
 */
 function disconnect(){
 	$.ajax({
 		type: "GET",
 		url:"http://li328.lip6.fr:8280/BERTRAND_DHOUIB/logout",
 		data:"key="+env.key,
 		dataType: "json",
 		success:traiteReponseDeconnexion,
 		error:function(jqXHR,textStatus,errorThrown){
 			alert(jqXHR+ " "+textStatus+ " " + errorThrown);
 		}});
 	env.actif = undefined;
 	env.key = undefined;
 	gererDivConnexion();
 	search();
 }

 function traiteReponseDeconnexion(el){
	if(el.error != undefined){
		console.log(el.error.value);
	}else{
		window.location.href="main.jsp";
	}
}