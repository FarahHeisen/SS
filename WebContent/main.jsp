<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<html>
<head>
  <title>Social Stalker Platform</title>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/main.css">
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script> 
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script> 
  <script type="text/javascript" src="js/main.js"></script>
  <script type="text/javascript" src="js/signin.js"></script>
  <script type="text/javascript" src="js/signup.js"></script>
  <script type="text/javascript" src="js/outils.js"></script>

  <%="<script type=\"text/javascript\">"%>
  <%="function go () {"%>
  <% 
  String id =request.getParameter("id");
  String login =request.getParameter("login");
  String key = request.getParameter("key");
  if (id != null && login != null && key != null)
   out.println("main(\""+id+"\",\""+login+"\",\""+key+"\");");  %>
 <%= "};"%>
 <%="$(go);" %>
 <%="</script>" %>

</head>
<body>
  <header>
    <div id="menu">
      <ul>
        <li class="active"> <a href="###">Home</a> </li>
        <li class="last" id="notif"> <a href="" onmouseover="javascript:dropdownOpen('list_notification')" onmouseout="javascript:dropdownClose('list_notification')">Notifications</a>  
          <div id="list_notification">
            <a href="#" id="notif_1" onclick="javascript:readNotification()">Première notif</a>
            <a href="#" id="notif_2" onclick="javascript:readNotification()">Deuxième notif</a>
            <a href="#" id="notif_3" onclick="javascript:readNotification()">Troisième notif</a>
          </div></li>
          <!-- <li> <a href="###">Messages</a> </li> -->
          <!-- <li class="last"> <a href="######">Discover</a> </li> -->
        </ul>
        <div id="divconnexion">
            <!--         <button role='button' class='btn-disconnect lastbtn' id='disconnect'>Disconnect 
            <span class='italic'>' + user.login +'</span></button> -->
            <button href="#signinModal" role="button" class="btn"
            onclick='showModal("signinModal")'>Sign in</button>
            <button href="#signupModal" role="button" class="btn lastbtn"
            onclick='showModal("signupModal")'>Sign up</button>
          </div>
        </div>
      </header> 
      <div id="wrapper">
             <!-- 
        STATISTIQUES DE L'UTILISATEUR
      -->  
      <div id="statistique"> Statistiques </div>
      <div id="post">
       <form id="form_poster" action="javascript:(function(){return;})()" method="get" OnSubmit="javascript:poster(this)"> 
        <textarea id="txt_message" name="message"
        placeholder="Votre message"></textarea>
        <input class="btn" type="submit" value="Poster" />
      </form>
    </div>
     <!-- 
    LIST TWEET
  -->
  <div id="list_tweet">
    <div class="tweet"> 
     <div class="tweettitle">
      <span class="author">Nom du user</span><div class="toright"><a href="javascript:ajouter_user" class="addUser"></a></div>
    </div>
    <div class="tweetbody">
      Message du tweet tres tres tres long message de quelque centaines de caractères youhouuuuuujfdksbfsbdjfbsfsdbfhdfhgsfv  bhfdshvghvsdhgfvhvdsghfhg
    </div>
    <div class="tweetfooter">
     <div class="toleft date">
      Posté le : 09090909 à HH MM SS
    </div>
    <div class="toright">
      <a href="" class="btn">Retweet</a>
    </div>
  </div>
</div> 

<div class="tweet"> 
 <div class="tweettitle">
  <span class="author">Nom du user </span><div class="toright"><a href="javascript:ajouter_user" class="addUser" ></a></div>
</div> 
<div class="tweetbody">
  Message du tweet 
</div>
<div class="tweetfooter">
  <div class="toleft date">
    Posté le : 09090909 à HH MM SS
  </div>
  <div class="toright">
    <a href="" class="btn">Retweet</a>
  </div>
</div>
</div> 

</div>
</div>

<!-- 
    RECHERCHE
  -->
  <div id="recherche" > 
    <form id="form_recherche" method="get" action="javascript:void(0);" OnSubmit="javascript:search(this)">
      <div class="form-group">
        <input type="text" class="form-control" name="recherche" id="request" placeholder="Votre recherche"/>
        <input type="submit" class="icon-search">
      </input>
    </div>
    <input id="check_friends" type="checkbox" value="SelectFriends"/>
    <span>Friends Only</span>
    <input id="check_shared" type="checkbox" value="SelectShared"/>
    <span>Shared</span>
  </form> 
</div>



<!-- 
    MODAL WINDOWS
  -->

  <div id="welcome" class="modal fade"> </div>

  <div id="signinModal" class="modal fade">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="title">
          <button type="button" class="btn-close" onclick='showModal("signinModal")'>x</button>
          <h4 >Sign in</h4>
        </div>
        <div class="modal-body">
          <form name="signinform" method="get" action="javascript:void(0)" autocomplete="on" onsubmit="javascript:connexion(this);">
            <div class="form-group"> 
              <input id="login" name="login" required="required" placeholder="Your login" class="form-control icon-user" type="text">
            </div>
            <div class="form-group">
              <input id="password" name="pwd" required="required" placeholder="Your password" class="form-control icon-password" type="password">
            </div>
            <div class="signin">
              <input value="Login" class="btn" type="submit">
            </div>
            <div class="change_link"> Not a member yet ? <a href="#" onclick='changeModal("signinModal")'>Join us</a></div>
          </form>
        </div>
      </div> 
    </div> 
  </div> 
  <div id="signupModal" class="modal fade"> <!-- modal -->
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="title"> 
          <button type="button" class="btn-close" onclick='showModal("signupModal")'>x</button>
          <h4>Sign up</h4>
        </div>
        <div class="modal-body">
          <form name="signupform" method="get" action="javascript:void(0)" autocomplete="on" onsubmit="javascript:inscr(this)">
            <div class="form-group"> 
              <input id="usernamesignup" name="login" required="required" placeholder="Your username" class="form-control icon-user" type="text"> 
            </div>
            <div class="form-group"> 
             <input id="nom" name="nom" required="required" placeholder="Your last name" class="form-control icon-user" type="text"> 
           </div>
           <div class="form-group"> 
            <input id="prenom" name="prenom" required="required" placeholder="Your first name" class="form-control icon-user" type="text">
          </div>
          <div class="form-group">
            <input id="password" name="pwd" required="required" placeholder="Your password" class="form-control icon-password" type="password">
          </div>
          <div class="form-group">
            <input id="pwd_c" name="pwd_c" required="required" placeholder="Please confirm your password" class="form-control icon-password" type="password"> 
          </div>
          <div class="signup">
            <input value="Sign up" type="submit" class="btn">
          </div>
          <div class="change_link"> Already a member ? <a
            href="#" onclick='changeModal("signupModal")'> Go and log in </a>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<!-- fin de signupModal -->
</body>
</html>