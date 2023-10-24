<%-- 
    Document   : Marcas
    Created on : 23 oct 2023, 8:25:21 a. m.
    Author     : cana0
--%>
<%@page import="modelo.Marca" %>
<%@page import="modelo.Usuario" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marcas</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <% 
            response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires",0);
        %>
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
        %>
        <center><h1>Formulario Marcas</h1></center>
        <div class="container">
            <form action="sr_marca" method="post" class="form-group" >
                <label for="lbl_id" >ID Puesto:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" readonly>

                <label for="lbl_marca" >Marca:</label>
                <input type="text" name="txt_marca" id="txt_marca" class="form-control">
                <br>
                <button name="btn_agregar" id="btn_agregar" value="Agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="Modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="Eliminar" class="btn btn-primary">Eliminar</button>
                <br>
            </form>
            <table class="table table-striped">
                <thead>
                    <th>ID Marca</th>
                    <th>Marca</th>
                </thead>
                <tbody id="tbl_marcas">
                    <%
                        Marca m = new Marca();
                        DefaultTableModel model= new DefaultTableModel();
                        model=m.mostrar();
                        for(int t=0;t<model.getRowCount();t++){
                            out.println("<tr data-id="+model.getValueAt(t,0)+">");
                            out.println("<td>"+model.getValueAt(t,0)+"</td>");
                            out.println("<td>"+model.getValueAt(t,1)+"</td>");
                            out.println("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </div>                
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
        <script type="text/javascript">
            $('#tbl_marcas').on('click','tr td',function(evt){
                var target, id, marca;
                target = $(event.target);
                id=target.parent("tr").find("td").eq(0).html();
                marca=target.parent("tr").find("td").eq(1).html();
                $("#txt_id").val(id);
                $("#txt_marca").val(marca);
            });
        </script>
        <%}else{%>
            <h1>No tienes permiso para ver esta página.</h1>
        <%}
        }else{%>
            <h1>No tienes permiso para ver esta página.</h1>
        <%}%>
    </body>
</html>
