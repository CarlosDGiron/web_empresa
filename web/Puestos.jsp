<%@page import="modelo.Puesto" %>
<%@page import="modelo.Usuario" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Puestos</title>
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
        <center><h1>Formulario Puestos</h1></center>
        <div class="container">
            <form action="sr_puestos" method="post" class="form-group" >
                <label for="lbl_id" >ID Puesto:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" readonly>

                <label for="lbl_puesto" >Puesto:</label>
                <input type="text" name="txt_puesto" id="txt_puesto" class="form-control">
                <br>
                <button name="btn_agregar" id="btn_agregar" value="Agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="Modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="Eliminar" class="btn btn-primary">Eliminar</button>
                <br>
            </form>
            <table class="table table-striped">
                <thead>
                    <th>ID Puesto</th>
                    <th>Puesto</th>
                </thead>
                <tbody id="tbl_puestos">
                    <%
                        Puesto p = new Puesto();
                        DefaultTableModel model= new DefaultTableModel();
                        model=p.mostrar();
                        for(int t=0;t<model.getRowCount();t++){
                            System.out.println(model.getValueAt(t,0)+" - "+ model.getValueAt(t,1));
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
            $('#tbl_puestos').on('click','tr td',function(evt){
                var target, id,puesto;
                target = $(event.target);
                id=target.parent("tr").find("td").eq(0).html();
                puesto=target.parent("tr").find("td").eq(1).html();
                $("#txt_id").val(id);
                $("#txt_puesto").val(puesto);
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
