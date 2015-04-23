
var env = new Object();

function main(id,login,key){ /* se lance au chargement */
	// var env = new Object(); /* ou env = {} */
	env.users = new Array(); /* env.users = [] */
	if((id != null) && (login != null) && (key != null)){
		console.log( "id " + id);
		env.key=key;
		env.actif = new User(id,login);
		gererDivConnexion();
		search();
		$(document).ready(function(){
			$("#link_disconnect").click(disconnect);
			console.log("hoho");
		});
	}
	console.log("main");
}

function gererDivConnexion(){
	var user = env.actif;
	console.log("gererDiv");
	if(user != undefined){
		var html = '<a id=\"link_disconnect\" href=\"javascript:void(0)\"> <button role=\'button\' class=\'btn-disconnect lastbtn\' id=\'disconnect\'>Disconnect ';
		html += '<span class=\'italic\'>' + user.login +'</span></button></a>';
		$("#divconnexion").html(html);
	}else{
		var html = '<div id="divconnexion">' ;
		html +=  '<button href="#signinModal" role="button" class="btn"';
		html +=  '  onclick=\'showModal("signinModal")\'>Sign in</button> ';
		html +=  ' <button href="#signupModal" role="button" class="btn lastbtn"';
		html +=  ' onclick=\'showModal("signupModal")\'>Sign up</button>';
		html +=  ' </div>' ;
		$("#divconnexion").html(html);
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
 function Tweet(id_msg, author_id, texte, date, humeur, hashtag, sharedBy) {
 	this.idmsg = id_msg;
 	this.author_id = author_id;
 	this.texte = texte;
 	this.date = date;
 	this.humeur=humeur;
 	this.hastag = hashtag;
 	this.sharedBy=sharedBy;
 }

 
/**
	A FAIRE
 * Renvoie le html d'un message
 * @param Tweet
 * @returns {String}
 */

//A REVOIRE!!
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
	if (key=="author"){ // le cas pour le user
		return new User(value.id, value.login, value.contact);
	}
	if(key=="comment"){
		return new Tweet(value._id, value.author_id, value.text, value.date, value.humeur, value.citer,value.hashtag, value.sharedBy);
	}
	if(key=="notif"){
		return new Notif(value.cible_id,value.message,value.date,value.vue,value.sender_id);
	}
}


/**
* Creer un objet 
* @param cible_id
 * @param message
 * @param date
 * @param vue
 * @param sender_id
 * @returns un objet notif
*
**/
function Notif(cible_id,message,date,vue,sender_id){
	this.cible_id=cible_id;
	this.message=message;
	this.date=date;
	this.vue=vue;
	this.sender_id=sender_id;
}




function search(){
	var friends = ($("#check_friends").checked?true:false);
	var shared = ($("#check_shared").checked?true:false);
	var rquery = document.forms["form_recherche"]["recherche"].value;
	console.log("query " + rquery);
	// replace
	$.ajax({
		type: "GET",
		url:"http://li328.lip6.fr:8280/BERTRAND_DHOUIB/search",
		data: traiteQuery(rquery, friends, shared),
		dataType: "json",
		success: Tweet.traiteReponseTweetJSON,
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

	/*récupération des keyword de la recherche*/
	var res_keyword = "";
	var re_keyword = /([A-Z]+)/igm; 
	var str = query;
	var m_keyword;
	if ((m_htag = str.match(re_htag)) !== null) {
		console.log("keyword");
		for (var i = 0; i < m_htag.length; i++) {
			res_keyword  += "&keyword="+ m_keyword[i].replace(/([A-Z]+)/igm, '$1');
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

//
res = "key="+ env.key + "" + res_author + "" +res_htag+ "&shared=" + shared + "&followed="+ friends;

console.log(res);
return res;

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
 		success:function(rep){traiteReponseAjouteSup(rep,id);},
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
 function traiteReponseAjoutSupContact(rep, id) {
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
 		url:"http://li328.lip6.fr:8280/BERTRAND_DHOUIB/",
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
 		data:"key="+ env.key,
 		dataType: "json",
 		success:traiteReponseDeconnexion,
 		error:function(jqXHR,textStatus,errorThrown){
 			alert(jqXHR+ " "+textStatus+ " " + errorThrown);
 		}});
 	console.log("disconnect");
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


 function showModal(href) {
 	el = document.getElementById(href);
 	el.style.visibility = (el.style.visibility == "visible") ? "hidden" : "visible";
 	console.log(el.style.visibility);
 }

 function changeModal (href){
 	if (href == "signupModal"){
 		showModal("signupModal");
 		showModal("signinModal");
 	}else{
 		showModal("signinModal");
 		showModal("signupModal");
 	}
 }