<%-- 
    Document   : index
    Created on : 4 oct 2023, 10:56:30 p. m.
    Author     : cana0
--%>
<%@page import="modelo.Puesto" %>
<%@page import="modelo.Empleado" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
        <link rel="styleshet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <% 
            response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
            response.setHeader("Pragma","no-cache");
            response.setDateHeader("Expires",0);
        %>
    </head>
    <body>
    <center><h1>Formulario Empleados</h1></center>
        <div class="container">
            <form action="sr_empleado" method="post" class="form-group" >
                <label for="lbl_id" >ID:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                
                <label for="lbl_codigo_empleado" >Codigo:</label>
                <input type="text" name="txt_codigo" id="txt_codigo" class="form-control" placeholder="Ejemplo: E001" required>
                
                <label for="lbl_nombres" >Nombres:</label>
                <input type="text" name="txt_nombres" id="txt_nombres" class="form-control" placeholder="Ejemplo: Nombre1 Nombre2..." required>  
                
                <label for="lbl_apellidos" >Apellidos:</label>
                <input type="text" name="txt_apellidos" id="txt_apellidos" class="form-control" placeholder="Ejemplo: Apellido1 Apellido2" required>
                
                <label for="lbl_direccion" >Direccion:</label>
                <input type="text" name="txt_direccion" id="txt_direccion" class="form-control" placeholder="Ejemplo: # Casa Calle Zona Ciudad" required>
                
                <label for="lbl_telefono" >Telefono:</label>
                <input type="number" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 12345678" required>
                
                <label for="lbl_nacimiento" >Fecha de Nacimiento:</label>
                <input type="date" name="txt_nacimiento" id="txt_nacimiento" class="form-control" required>
                
                <label for="lbl_puesto" >Puesto:</label>
                <select name="drop_puesto" id="drop_puesto" class="form-control">
                    <%
                        Puesto p=new Puesto();
                        HashMap<String,String> drop=p.drop_puesto();
                        for (String i: drop.keySet()){
                        out.println("<option value='"+i+"'>"+drop.get(i)+"</option>");
                        }
                    %>  
                </select>
                <br>
                <button name="btn_agregar" id="btn_agregar" value="Agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="Modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="Eliminar" class="btn btn-primary">Eliminar</button>
                <br>
            </form>
                <table class="table table-striped">
                    <thead>
                    <th>Codigo</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>Direccion</th>
                    <th>Telefono</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Puesto</th>
                    </thead>
                    <tbody id="tbl_empleados">
                    <%
                        Empleado e=new Empleado();
                        DefaultTableModel model= new DefaultTableModel();
                        model=e.mostrar();
                        for(int t=0;t<model.getRowCount();t++){
                            out.println("<tr data-id=" + model.getValueAt(t,0) + " data-id_p=" + model.getValueAt(t,8) + ">");
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
            $('#tbl_empleados').on('click','tr td',function(evt){
                var target, id, id_p, codigo, nombres, apellidos, direccion, telefono, nacimiento;
                target = $(event.target);
                id=target.parent().data('id');
                console.log(id);
                id_p=target.parent().data('id_p');
                console.log(id_p);
                codigo=target.parent("tr").find("td").eq(0).html();
                console.log(codigo);
                nombres=target.parent("tr").find("td").eq(1).html();
                console.log(nombres);
                apellidos=target.parent("tr").find("td").eq(2).html();
                console.log(apellidos);
                direccion=target.parent("tr").find("td").eq(3).html();
                console.log(direccion);
                telefono=target.parent("tr").find("td").eq(4).html();
                console.log(telefono);
                nacimiento=target.parent("tr").find("td").eq(5).html();
                console.log(nacimiento);
                $("#txt_id").val(id);
                $("#txt_codigo").val(codigo);
                $("#txt_nombres").val(nombres);
                $("#txt_apellidos").val(apellidos);
                $("#txt_direccion").val(direccion);
                $("#txt_telefono").val(telefono);
                $("#txt_nacimiento").val(nacimiento);
                $("#drop_puesto").val(id_p);                            
            });
        </script>  
    </body>
</html>
