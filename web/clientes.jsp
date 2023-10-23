<%-- 
    Document   : clientes
    Created on : 22 oct 2023, 8:32:46 p. m.
    Author     : cana0
--%>
<%@page import="modelo.Cliente" %>
<%@page import="modelo.Usuario" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
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
            if(u.tienePermisoId(idUsuario, 1)){ %>
        <center><h1>Formulario Clientes</h1></center>
        <div class="container">
            <form action="sr_cliente" method="post" class="form-group" >
                <label for="lbl_id" >ID:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>

                <label for="lbl_nombres" >Nombres:</label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Nombre1 Nombre2..." required>  

                <label for="lbl_apellidos" >Apellidos:</label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Apellido1 Apellido2" required>

                <label for="lbl_nit" >NIT:</label>
                <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ejemplo: 10405261" required>

                <label for="lbl_genero" >Genero:</label>
                <select name="drop_genero" id="drop_genero" class="form-control">
                    <option value="F">F</option>
                    <option value="M">M</option>
                </select>

                <label for="lbl_telefono" >Telefono:</label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 12345678" required>

                <label for="lbl_correo_electronico" >Correo electrónico:</label>
                <input type="text" name="txt_correo_electronico" id="txt_correo_electronico" class="form-control"  placeholder="Ejemplo: correo@dominio.com"required>

                <br>
                <button name="btn_agregar" id="btn_agregar" value="Agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="Modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="Eliminar" class="btn btn-primary">Eliminar</button>
                <br>
            </form>
                <table class="table table-striped">
                    <thead>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>NIT</th>
                    <th>Genero</th>
                    <th>Telefono</th>
                    <th>Correo electrónico</th>
                    <th>Fecha de ingreso</th>
                    </thead>
                    <tbody id="tbl_clientes">
                    <%
                        Cliente c=new Cliente();
                        DefaultTableModel model= new DefaultTableModel();
                        model=c.mostrar();
                        for(int t=0;t<model.getRowCount();t++){
                            out.println("<tr data-id=" + model.getValueAt(t,0)+" >");
                            out.println("<td>"+model.getValueAt(t,1)+"</td>");
                            out.println("<td>"+model.getValueAt(t,2)+"</td>");
                            out.println("<td>"+model.getValueAt(t,3)+"</td>");
                            out.println("<td>"+model.getValueAt(t,4)+"</td>");
                            out.println("<td>"+model.getValueAt(t,5)+"</td>");
                            out.println("<td>"+model.getValueAt(t,6)+"</td>");
                            out.println("<td>"+model.getValueAt(t,7)+"</td>"); 
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
            $('#tbl_clientes').on('click','tr td',function(evt){
                var target, id, nombres, apellidos, telefono, nit ,genero, correo_electronico;
                target = $(event.target);
                id=target.parent().data('id');
                nombres=target.parent("tr").find("td").eq(0).html();
                apellidos=target.parent("tr").find("td").eq(1).html();
                nit=target.parent("tr").find("td").eq(2).html();
                genero=target.parent("tr").find("td").eq(3).html();
                telefono=target.parent("tr").find("td").eq(4).html();
                correo_electronico=target.parent("tr").find("td").eq(5).html();
                $("#txt_id").val(id);
                $("#txt_nombres").val(nombres);
                $("#txt_apellidos").val(apellidos);
                $("#txt_telefono").val(telefono);
                $("#txt_nit").val(nit);            
                $("#txt_correo_electronico").val(correo_electronico);
                $("#drop_genero").val(genero);      
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
