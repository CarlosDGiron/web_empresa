<%-- 
    Document   : Ventas
    Created on : 2 nov 2023, 8:41:26 p. m.
    Author     : cana0
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        %>
    </head>
    <body>
        <%
            int idUsuario = 0;
            if (request.getSession().getAttribute("idUsuario") != null) {
                idUsuario = (int) request.getSession().getAttribute("idUsuario");
            }
            if (idUsuario != 0) {
                Usuario u = new Usuario();
                if (u.tienePermisoId(idUsuario, 1)) {
        %>
        <div class="container-fluid">
            <form action="sr_login" method="post" class="form-group">
                <nav class="navbar navbar-dark bg-primary">
                    <span class="navbar-text"><h5 style="color: white;"><% out.println(request.getSession().getAttribute("nombre"));%></h5></span>
                    <a class="navbar-brand mx-auto" href="Principal.jsp">Web Empresa</a>
                    <button class="btn btn-primary" id="accion" name="accion" value="Logout">Cerrar Sesión</button>
                </nav>
            </form>
        </div>
    <center>
        <div class="container" style="display: flex; width: 100vw; height: 100vh;justify-content: center; align-items: center;">
            <form method="post" class="form-group">
                <div class="card">
                    <div class="card-body">
                        <h2>Ventas</h2>
                        <input type="button" class="btn btn-primary" onclick="window.location.href = 'NuevaVenta.jsp';" value="Agregar">
                        <br><br><input type="button" class="btn btn-primary" onclick="location.href = 'Ventas_Detalles.jsp';" value="Historico">
                    </div>
                </div>
            </form>
        </div>
    </center><script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>

    <% } else {%>
    <h1>Usted no tiene permisos.</h1>
    <%}
} else {%>
    <h1>Usted no tiene permisos.</h1>
    <%}%>
</body>
</html>