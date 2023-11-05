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
        int idUsuario=0;
        if(request.getSession().getAttribute("idUsuario")!=null){
            idUsuario=(int)request.getSession().getAttribute("idUsuario");
        }
        if(idUsuario!=0){
            Usuario u=new Usuario();
            if(u.tienePermisoId(idUsuario, 1)|u.tienePermisoId(idUsuario,2)){
            
            %>
            
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <div class="container">
                        <a class="navbar-brand"><% out.print(request.getSession().getAttribute("nombre"));%></a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="Productos.jsp" id="ProductosDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Productos
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="ProductosDropdown">                                        
                                        <a class="dropdown-item" href="Productos.jsp">Productos</a>
                                        <a class="dropdown-item" href="Marcas.jsp">Marcas</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="Ventas" id="VentasDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        Ventas
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="VentasDropdown">
                                        <a class="dropdown-item" href="Ventas.jsp">Ventas</a>
                                        <a class="dropdown-item" href="Clientes.jsp">Clientes</a>
                                        <div class="dropdown">
                                            <a class="dropdown-item" href="#">Empleados</a>
                                            <div class="dropdown-menu" arial-labelledby="VentasDropdown">
                                                <a class="dropdown-item" href="Empleados.jsp">Empleados</a>
                                                <a class="dropdown-item" href="Puestos.jsp">Puestos</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Acerca de</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Contacto</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
               <% out.println("<h2><a href='Productos.jsp'>Productos</a></h2>");
                out.println("<h2><a href='Marcas.jsp'>Marcas</a></h2>");
                out.println("<h2><a href='Ventas.jsp'>Ventas</a></h2>");
                out.println("<h2><a href='Clientes.jsp'>Clientes</a></h2>");
                out.println("<h2><a href='Empleados.jsp'>Empleados</a></h2>");
                out.println("<h2><a href='Puestos.jsp'>Puestos</a></h2>");
                out.println("<h2><a href='Compras.jsp'>Compras</a></h2>");
                out.println("<h2><a href='Proveedores.jsp'>Proveedores</a></h2>");
                out.println("<h2><a href='Reportes.jsp'>Reportes</a></h2>");
            }
        }else{
            out.println("<h1>Usted no tiene permisos.</h1>");
        }%>        
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
    
</html>
