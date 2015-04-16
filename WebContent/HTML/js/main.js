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
 function Tweet(id_msg, author_id, auteur, texte, date, score, hashtag, sharedBy) {
 	this.idmsg = id_msg;
 	this.author_id = author_id;
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
 * @param Tweet
 * @returns {String}
 */


 Tweet.prototype.getHTML = function(Tweet){
	var res  = " <div class=\"panel panel-default\" id = "+ this.id_msg+"> ";
    res += "<div class=\"panel-heading\">";
    res += this.auteur + "<div class=\"pull-right\"><a href=\"javascript:ajouter_user\"("+this.author_id+") "
    res += "class=\"btn btn-success addUser\" > ";
     res += "  	<span class=\"glyphicon glyphicon-plus\"></span></a></div>";
    res += " </div>";
   res += "  <div class=\"panel-body\">";
   res += this.texte;
   res += "  </div>";
   res += "  <div class=\"panel-footer clearfix\">";
    res += "   <div class=\"pull-right\">";
     res += "<a href=\"\" class=\"btn btn-default\">Retweet</a>";
     res += "  </div>";
  res += "   </div>";
  res += " </div> ";	


 }

/**
 * Traite la reponse de la servlet AfficherMessage
 * 
 * @param json
 */
Tweet.traiteReponseTweetJSON = function(json) {
	var s = "";
	var tmp = new Array();
	for ( var i = 0; i < json.length; i++) {
		// id_msg, author_id, auteur, texte, date, score, hashtag, sharedBy
		var msg = new Tweet(json[i].id_msg, json[i].author_id,
				json[i].auteur, json[i].texte, json[i].date.$date, json[i].sharedBy);
		tmp[i] = msg;
		s += msg.getHTML(msg);
	}
	$("#list_tweet").html(s);
};


function revive (key, value){
	if (key=="author_id"){ // le cas pour le user
		//ajouter trois lignes d'appelle a des fonctions et comparateurs
		//+ ajax pour vérifier le resultat
		return new User(value.id, value.login, value.contact);
	}
	if(key=="comment"){
		return new Tweet(value.id_msg, value.id, value.auteur, value.texte, value.date, value.score,value.hashtag, value.sharedBy);
	}
	if(key==""){}

}



function search(formulaire){
	var friends = ($("#check_friends").checked?true:false);
	var shared = ($("#check_shared").checked?true:false);
	var rquery = formulaire.recherche.value;
	console.log("query " + rquery);
	// replace
	$.ajax({
		type: "GET",
		url:"http://li328.lip6.fr:8280/BERTRAND_DHOUIB/search",
		data: traiteQuery(rquery, friends, shared),
		dataType: "json",
		success:traiteReponseTweetJSON,
		error:function(jqXHR,textStatus,errorThrown){
			alert(jqXHR+ " "+textStatus+ " " + errorThrown);

		}
	});
}



function traiteQuery(query, friends, shared){
	/*récupération des hashtag de la recherche*/
	var res_htag = "";
	var re_htag = /#([A-Z]*)/igm; 
	var str = query;
	var m_htag;
	if ((m_htag = str.match(re_htag)) !== null) {
		console.log("htag found");
		for (var i = 0; i < m_htag.length; i++) {
			res_htag += "&hashtag="+ m_htag[i].replace(/#([A-Z]*)/igm, '$1');
		};
	}

	/*récupération des auteurs de la recherche*/
	var res_author = "";
	var re_author = /@([A-Z]*)/igm; 
	var str = query;
	var m_author;
	if ((m_author = str.match(re_author)) !== null) {
		for (var i = 0; i < m_author.length; i++) {
			res_author += "&author="+ m_author[i].replace(/@([A-Z]*)/igm, '$1'); 
		};
	}


	res = "key="+ env.actif +""+ res_author +""+res_htag+ "&shared=" + shared + "&followed="+ friends;

	console.log(res);

}


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



/**
 * Traite la reponse d'ajout/suppresion du contact
 * 
 * @param rep
 *            reponse de la servlet
 * @param id
 *            id du contact
 */
 function traiteReponceAjoutSupContact(rep, id) {
 	if (rep.error != undefined) {
 		alert(rep.error);
 	} else {
 		var user = environnement.users[id];
 		user.modifStatut();
 		liste();
 	}
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