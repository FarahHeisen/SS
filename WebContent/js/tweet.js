/**
 * Pour poster un tweet
 * 
 * @param formulaire
 */
function poster(formulaire) {
	var message = formulaire.txt_message.value;
	$.ajax({
		type : "Get",
		url : "ttp://li328.lip6.fr:8280/BERTRAND_DHOUIB/write",
		data : "key=" + env.key + "&comment=" + message,
		dataType : "json",
		success : affiche,
		error : function(e) {
			$("#list_tweet").html(e);
		}
	});
}


/* fonction traiter message */


function affiche(){
$.ajax({
			type : "Get",
			url : "", /// a ajotuer 
			data : "",
			dataType : "json",
			success : Tweet.traiteReponse,
			error : function(e) {
				$("#liste_tweet").html(e);
			}
		});

}