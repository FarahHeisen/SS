var menuOpen = 0;
var closeTimer = 0;

function dropdownOpen(notif){
	if (menuOpen) menuOpen.style.visibility = 'hidden';
	menuOpen =  document.getElementById(notif);
	menuOpen.style.visibility = 'visible';
}

function close (){
 	if (menuOpen) menuOpen.style.visibility = 'hidden';
}

function dropdownClose(){
	closeTimer = window.setTimeout( close, 2000);
}

document.onclick = dropdownClose; 