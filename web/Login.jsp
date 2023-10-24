<%-- 
    Document   : login
    Created on : 18 oct 2023, 4:30:13 p. m.
    Author     : cana0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <% 
            response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires",0);
        %>
    </head>
    <body>
    <center>
        <div class="container" style="display: flex; width: 100vw; height: 100vh;justify-content: center; align-items: center;">
            <form method="post" action="sr_login" class="form-group">
                <div class="card">
                        <div class="card-body">
                            <h2>Iniciar sesion.</h2>
                            <br><input type="text" name="txt_usuario" id="txt_usuario" class="form-control"placeholder="Ingrese su usuario">
                            <br><input type="password" name="txt_password" id="txt_password" class="form-control" placeholder="Ingrese su contraseña">
                            <br><button class="btn btn-primary" name="btn_login" id="btn_login" value="login">Ingresar</button>
                            
                            <%
                            if (request.getParameter("login")!=null){
                                out.println("<br><h6 style='color: red;'>Error, contraseña incorrecta.</h6>");
                            }
                            %>
                        </div>
                </div>
            </form>
        </div>
    </center>        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>
