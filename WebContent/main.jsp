<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Social Stalker Platform</title>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="main.css">
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script> 
  <script src="http://code.jquery.com/jquery-latest.js"></script> 
  <script type="text/javascript" src="main.js"></script>
  <script type="text/javascript" src="signin.js"></script>
  <script type="text/javascript" src="signup.js"></script>
  <!--- <script type="text/javascript" src="js/jquery.js"></script> -->
  <script type="text/javascript">
   function go(){
    <% String id =request.getParameter("id");
    String login =request.getParameter("login");
    String key = request.getParameter("key");
    if((id != null) && (login != null) && (key != null)){ 
     out.println("main("+id+",\""+login+"\",\""+key+"\");");
   }
     %>
          console.log("je passe par la ");

   };
   $(go);

  </script>
</head>
<body>
<header>
  <nav id="menu">
    <ul>
      <li class="active"> <a href="###">Home</a> </li>
      <li> <a href="###">Notifications</a> </li>
      <!-- <li> <a href="###">Messages</a> </li> -->
      <li class="last"> <a href="######">Discover</a> </li>
      <li id="divconnexion">
        <button href="#signinModal" role="button" class="button"
        data-toggle="modal">Sign in</button> <button
        href="#signupModal" role="button" class="button"
        data-toggle="modal">Sign up</button>
      </li>
    </ul>
  </nav>
</header> 
<div id="wrapper">
  <div id="statistique"> Statistiques </div>
  <div id="post">
   <form id="form_poster" action="javascript:(function(){return;})()" method="get" OnSubmit="javascript:poster(this)"> <!-- ajouter fonction poster Commentaire  -->
    <textarea id="txt_message" name="message"
    placeholder="Votre message"></textarea>
    <input class="button" type="submit" value="Poster" />
  </form>
</div>
<div id="list_tweet">
  <div class="panel panel-default"> 
   <div class="panel-header">
    Nom du user
   </div>
    <div class="panel-body">
      Message du tweet 
    </div>
    <div class="panel-footer clearfix">
        <div class="pull-right">
            <a href="javascript:ajouter_user" class="btn btn-primary"> <span class="glyphicon glyphicon-plus"></span> User</a>
            <a href="" class="btn btn-default">Retweet</a>
        </div>
    </div>

  </div>
</div>

<div id="zone_humeur">
  <h1>Humeur</h1>
  <div id="humeur"></div>
  <form id="form_humeur" method="get" action="javascript:(function(){return;})()" OnSubmit=""> <!-- ajouter la fonction addHumeur -->
    <textarea name="humeur" id="text_humeur" placeholder="Votre Hummeur"></textarea>
    <input class="button" id="button_humeur" type=submit value="Ajouter Humeur" />
  </form>
</div>
</div>

<div id="recherche"> 
  <form id="form_recherche" method="get" action="javascript:(function(){return;})()" OnSubmit="javascript:search(this)">
    <input name="request" id="text_recherche" placeholder="Votre recherche"></input>
    <input id="button_request" name="but_request" class="button"  type=submit value="Rechercher"/>
    <input id="check_friends" type="checkbox" value="SelectFriends"/>
    <span id="box_friends">Friends Only</span>
  </form> 
</div>


<div id="welcome" class="modal modal-wide fade"> </div>

<div id="signinModal" class="modal modal-wide fade">
  <!-- modal -->
  <div class="modal-dialog modal-md">
    <div class="modal-content">
      <div class="modal-header"> <button type="button"
        class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h4 class="modal-title text-center">Sign in</h4>
      </div>
      <div class="modal-body">
        <div id="login">
          <form name="signinform" method="get" action="javascript:(function(){return;})()" autocomplete="on" onsubmit="javascript:connexion(this);">
            <div class="form-group">
              <div class="icon-addon addon-sm"> 
               <input id="login" name="login" placeholder="Your username" class="form-control" type="text"> 
               <label for="login" class="glyphicon glyphicon-user" rel="tooltip" title="login"> </label>
             </div>
           </div>
           <div class="form-group icon-addon addon-sm">
            <input id="password" name="pwd" placeholder="Your password" class="form-control" type="password">
            <label for="password" class="glyphicon glyphicon-lock" rel="tooltip" title="password"></label>
          </div>
          <div class="form-group">
            <input value="Login" class="btn" type="submit">
          </div>
          <div class="change_link"> Not a member yet ? <a href="#signupModal">Join us</a></div>
        </form>
      </div>
    </div>
  </div> <!-- /.modal-content -->
</div> <!-- /.modal-dialog -->
</div> <!-- /.modal -->
<div id="signupModal" class="modal modal-wide fade"> <!-- modal -->
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header"> <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h4 class="modal-title text-center">Sign up</h4>
      </div>
      <div class="modal-body">
        <div id="register" class="animate form">
          <form name="signupform" method="get" action="javascript:(function(){return;})()" autocomplete="on" onsubmit="javascript:inscr(this)">
            <div class="form-group">
              <div class="icon-addon addon-sm">
                <label for="usernamesignup" class="glyphicon glyphicon-user" rel="tooltip" title="user"></label>
                <input id="usernamesignup" name="login" required="required" placeholder="Your username" class="form-control" type="text"> 
              </div>
            </div>
            <div class="form-group icon-addon addon-sm"> 
              <label for="nom" class="glyphicon glyphicon-user" rel="tooltip" title="nom"></label>
              <input id="nom" name="nom" required="required" placeholder="Your last name" class="form-control" type="text"> 
            </div>
            <div class="form-group icon-addon addon-sm"> 
              <label for="prenom" class="glyphicon glyphicon-user" rel="tooltip" title="nom"></label>
              <input id="prenom" name="prenom" required="required" placeholder="Your first name" class="form-control" type="text">
            </div>
            <div class="form-group icon-addon addon-sm">
              <label for="pwd" class="glyphicon glyphicon-lock" rel="tooltip" title="Password"></label>
              <input id="password" name="pwd" required="required" placeholder="Your password" class="form-control" type="password">
            </div>
            <div class="form-group icon-addon addon-sm">
              <label for="pwd_c" class="glyphicon glyphicon-lock" rel="tooltip" title="Password"> </label>
              <input id="pwd_c" name="pwd_c" required="required" placeholder="Please confirm your password" class="form-control" type="password"> 
            </div>
            <div class="signin">
              <input value="Sign up" type="submit">
            </div>
            <div class="change_link"> Already a member ? <a
              href="#signinModal"> Go and log in </a>
            </div>
          </form>
        </div>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script src="js/bootstrap.min.js"></script>
  </body>
  </html>