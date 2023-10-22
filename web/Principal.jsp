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
        <title>JSP Page</title>
    </head>
    <body>
        <%
           int idUsuario=0;
           if(request.getSession().getAttribute("idUsuario")!=null){
            idUsuario=(int)request.getSession().getAttribute("idUsuario");
           }
            if(idUsuario!=0){
                Usuario u=new Usuario();
                if(u.tienePermisoId(idUsuario, 1)){
                    out.println("<h1>Permiso 1.</h1>");
                }
            }else{
                    out.println("<h1>Usted no tiene permisos.</h1>");
                }
        %>
    </body>
</html>
