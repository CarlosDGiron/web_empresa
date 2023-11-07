<%-- 
    Document   : Principal
    Created on : 18 oct 2023, 8:05:41 p. m.
    Author     : cana0
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Empresa</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>

        <%
            int idUsuario = 0;
            if (request.getSession().getAttribute("idUsuario") != null) {
                idUsuario = (int) request.getSession().getAttribute("idUsuario");
            }
            if (idUsuario != 0) {
                Usuario u = new Usuario();%>
        <div class="container-fluid">
            <form action="sr_login" method="post" class="form-group">
                <nav class="navbar navbar-dark bg-primary">
                    <span class="navbar-text"><h5 style="color: white;"><% out.println(request.getSession().getAttribute("nombre"));%></h5></span>
                    <a class="navbar-brand mx-auto" href="Principal.jsp">Web Empresa</a>
                    <button class="btn btn-primary" id="accion" name="accion" value="Logout">Cerrar Sesión</button>
                </nav>
            </form>
        </div>
        <!--% if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 2)) {
                    out.println("<h2><a href='Productos.jsp'>Productos</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 3)) {
                    out.println("<h2><a href='Marcas.jsp'>Marcas</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 4)| u.tienePermisoId(idUsuario, 5)) {
                    out.println("<h2><a href='Ventas.jsp'>Ventas</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 6)) {
                    out.println("<h2><a href='Clientes.jsp'>Clientes</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 7)) {
                    out.println("<h2><a href='Empleados.jsp'>Empleados</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 8)) {
                    out.println("<h2><a href='Puestos.jsp'>Puestos</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 9)| u.tienePermisoId(idUsuario, 10)) {
                    out.println("<h2><a href='NuevaCompra.jsp'>Nueva Compra</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 11)) {
                    out.println("<h2><a href='Proveedores.jsp'>Proveedores</a></h2>");
                }
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 12)) {
                    out.println("<h2><a href='Reportes.jsp'>Reportes</a></h2>");
                }
            } else {
                out.println("<h1>Usted no tiene permisos.</h1>");
            }%-->
        <style>
            .sidebar {
                position: fixed;
                top: 0;
                left: 0;
                height: 100%;
                width: 250px;
                background-color: #343a40; /* Color de fondo del menú */
                padding-top: 20px;
            }

            .sidebar a {
                padding: 10px 15px;
                text-decoration: none;
                font-size: 18px;
                color: #ffffff; /* Color de texto del menú */
                display: block;
            }

            .submenu {
                padding-left: 15px;
            }
        </style>

        <div class="sidebar bg-primary">
            <center><span class="navbar-text"><h5 style="color: white;"><% out.println(request.getSession().getAttribute("nombre"));%></h5></span></center>
            <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 2)) { %>
            <a href="Productos.jsp">Productos</a>
            <% } %>
            <div class="submenu">
            <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 3)) { %>
            <a href="Marcas.jsp" class="submenu">Marcas</a>
            <% } %>
            </div>
            <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 4) || u.tienePermisoId(idUsuario, 5)) { %>
            <a href="Ventas.jsp">Ventas</a>
            <div class="submenu">
                <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 6)) { %>
                <a href="Clientes.jsp">Clientes</a>
                <% } %>
                <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 7)) { %>
                <a href="Empleados.jsp">Empleados</a>
                <div class="submenu">
                    <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 8)) { %>
                    <a href="Puestos.jsp">Puestos</a>
                    <% } %>
                </div>
                <% } %>
            </div>
            <% } else{%>
            <% if (u.tienePermisoId(idUsuario, 7)) { %>
                <a href="Empleados.jsp">Empleados</a> 
            <%}if (u.tienePermisoId(idUsuario, 8)) { %>
                <a href="Puestos.jsp">Puestos</a>
            <% }}if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 9) || u.tienePermisoId(idUsuario, 10)) { %>
            <a href="Compras.jsp">Compras</a>
            <% } %>
            <div class="submenu">
            <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 11)) { %>
            <a href="Proveedores.jsp">Proveedores</a>
            <% } %>
            </div>
            <% if (u.tienePermisoId(idUsuario, 1) || u.tienePermisoId(idUsuario, 12)) { %>
            <a href="Reportes.jsp">Reportes</a>
             <%}%>
                
            
            <%} else {
                    out.println("<h1>Usted no tiene permisos.</h1>");
                }
            %>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>

</html>
