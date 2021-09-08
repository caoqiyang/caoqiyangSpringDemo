<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Hello</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <script>
      window.onload = function (){
        var img = document.getElementById("checkCode");
        img.onclick = function (){
          img.src = "/cqy/check?" + new Date().getTime();
        }
        var img1 = document.getElementById("change");
        img1.onclick = function (){
          img.src = "/cqy/check?" + new Date().getTime();
        }
      }
    </script>

  </head>

  <body>

    <div class="container">

      <form class="form-signin" action="/cqy/login" method="post">
        <h2 class="form-signin-heading">Please Sign In</h2>
        <label for="name" class="sr-only">name</label>
        <input type="text" id="name" name="name" class="form-control" placeholder="name" required autofocus>
        <label for="album" class="sr-only">album</label>
        <input type="password" id="album" name="album" class="form-control" placeholder="album" required>
        <label for="checkCodeText" class="sr-only">checkCode</label>
        <input type="text" id="checkCodeText" name="checkCodeText" class="form-control" placeholder="checkCode" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <img id="checkCode" src="/cqy/check" />
        <a id="change" >change for another one </a>
        <h4><div><%=request.getAttribute("error") == null ? "" : request.getAttribute("error")%></div></h4>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> <!-- /container -->


  </body>
</html>
