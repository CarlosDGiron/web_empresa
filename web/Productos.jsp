<%-- 
    Document   : Productos
    Created on : 25 oct 2023, 5:44:06 p. m.
    Author     : cana0
--%>
<%@page import="modelo.Producto" %>
<%@page import="modelo.Marca" %>
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
        <center><h1>Formulario Productos</h1></center>
        <div class="container">
            <form action="sr_producto" method="post" class="form-group" >
                <label for="lbl_id" >ID:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                
                <label for="lbl_producto" >Producto:</label>
                <input type="text" name="txt_producto" id="txt_producto" class="form-control" placeholder="Ejemplo: Nombre" required>  
                
                <label for="lbl_marca" >Marca:</label>
                <select name="drop_marca" id="drop_marca" class="form-control">
                    <%
                        Marca m=new Marca();
                        HashMap<String,String> drop=m.drop_marca();
                        for (String i: drop.keySet()){
                        out.println("<option value='"+i+"'>"+drop.get(i)+"</option>");
                        }
                    %>
                </select>
                
                <label for="lbl_descripcion" >Descripción:</label>
                <input type="text" name="txt_descripcion" id="txt_descripcion" class="form-control" placeholder="Ejemplo:Producto descripción..." required>

                <label for="lbl_imagen" >Imagen:</label>
                <!--select imagen desde-->
                <input type="text" name="txt_imagen" id="txt_imagen" class="form-control" placeholder="Ejemplo: archivo.extensión" required>

                <label for="lbl_precio_costo" >Precio costo:</label>
                <input type="number" name="txt_precio_costo" id="txt_precio_costo" class="form-control" placeholder="Ejemplo: 12345678" required>

                <label for="lbl_precio_venta" >Precio venta:</label>
                <input type="number" name="txt_precio_venta" id="txt_precio_venta" class="form-control" placeholder="Ejemplo: 12345678" required>

                <label for="lbl_existencia" >Existencia:</label>
                <input type="number" name="txt_existencia" id="txt_existencia" class="form-control" placeholder="Ejemplo: 12345678" required>

                <br>
                <button name="btn_agregar" id="btn_agregar" value="Agregar" class="btn btn-primary">Agregar</button>
                <button name="btn_modificar" id="btn_modificar" value="Modificar" class="btn btn-primary">Modificar</button>
                <button name="btn_eliminar" id="btn_eliminar" value="Eliminar" class="btn btn-primary">Eliminar</button>
                <br>
            </form>
                <table class="table table-striped">
                    <thead>
                    <th>Producto</th>
                    <th>Marca</th>
                    <th>Descripcion</th>
                    <th>Imagen</th>
                    <th>Precio costo</th>
                    <th>Precio venta</th>
                    <th>Existencia</th>
                    <th>Fecha de ingreso</th>
                    </thead>
                    <tbody id="tbl_productos">
                        <%
                            Producto p=new Producto();
                            DefaultTableModel model= new DefaultTableModel();
                            model=p.mostrar();
                            for(int t=0;t<model.getRowCount();t++){
                                out.println("<tr data-id=" + model.getValueAt(t,0) + " data-idm=" + model.getValueAt(t,9) + ">");
                                out.println("<td>"+model.getValueAt(t,1)+"</td>");
                                out.println("<td>"+model.getValueAt(t,2)+"</td>");
                                out.println("<td>"+model.getValueAt(t,3)+"</td>");
                                out.println("<td>"+model.getValueAt(t,4)+"</td>");
                                out.println("<td>"+model.getValueAt(t,5)+"</td>");
                                out.println("<td>"+model.getValueAt(t,6)+"</td>");
                                out.println("<td>"+model.getValueAt(t,7)+"</td>"); 
                                out.println("<td>"+model.getValueAt(t,8)+"</td>"); 
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
            $('#tbl_productos').on('click','tr td',function(evt){
                var target, idproducto, producto, idmarca, descripcion, imagen, precio_costo, precio_venta, existencia, fechaingreso;
                target = $(event.target);
                idproducto=target.parent().data('id');
                idmarca=target.parent().data('idm');
                producto=target.parent("tr").find("td").eq(0).html();
                descripcion=target.parent("tr").find("td").eq(2).html();
                imagen=target.parent("tr").find("td").eq(3).html();
                precio_costo=target.parent("tr").find("td").eq(4).html();
                precio_venta=target.parent("tr").find("td").eq(5).html();
                existencia=target.parent("tr").find("td").eq(6).html();
                fechaingreso=target.parent("tr").find("td").eq(7).html();
                $("#txt_id").val(idproducto);
                $("#txt_producto").val(producto);
                $("#drop_marca").val(idmarca);
                $("#txt_descripcion").val(descripcion);
                $("#txt_imagen").val(imagen);            
                $("#txt_precio_costo").val(precio_costo);
                $("#txt_precio_venta").val(precio_venta);      
                $("#txt_existencia").val(existencia);      
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
