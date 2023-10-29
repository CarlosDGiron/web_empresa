<%-- 
    Document   : Compras_Detalles
    Created on : 26 oct 2023, 6:30:31 p. m.
    Author     : cana0
--%>

<%@page import="modelo.Compra" %>
<%@page import="modelo.Compra_detalle" %>
<%@page import="modelo.Proveedor" %>
<%@page import="modelo.Producto" %>
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
            //Cambiar a 0
        int idUsuario=1;
        if(request.getSession().getAttribute("idUsuario")!=null){
            idUsuario=(int)request.getSession().getAttribute("idUsuario");
        }
        if(idUsuario!=0){
            Usuario u=new Usuario();
            if(u.tienePermisoId(idUsuario, 1)){ %>
        <center><h1>Formulario Compras</h1></center>
        <div class="container">
            <form action="sr_compras" method="post" class="form-group" >
                <label for="lbl_id" >ID:</label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>

                <label for="lbl_no_orden" >No. Orden:</label>
                <input type="text" name="txt_no_orden" id="txt_no_orden" class="form-control"  placeholder="Ejemplo: 20230101001">
                
                <label for="lbl_proveedor" >Proveedor:</label>
                <select name="drop_proveedor" id="drop_proveedor" class="form-control">
                    <%
                        Proveedor m=new Proveedor();
                        HashMap<String,String> drop=m.drop_proveedor();
                        for (String i: drop.keySet()){
                        out.println("<option value='"+i+"'>"+drop.get(i)+"</option>");
                        }
                    %>
                </select>
                
                <label for="lbl_fecha_orden" >Fecha de la orden:</label>
                <input type="date" name="txt_fecha_orden" id="txt_fecha_orden" class="form-control" required>  
                <br>
                <div class="container" style="border: 2px solid grey;">
                    <h3>Detalle de compra</h3>   
                    <div class="row">
                        <div class="col">
                            
                            <label for="lbl_producto" >Producto:</label>
                            <select name="drop_producto" id="drop_producto" class="form-control">
                            <%
                                Producto p=new Producto();
                                HashMap<String,String> dropp=p.drop_producto();
                                for (String i: dropp.keySet()){
                                out.println("<option value='"+i+"'>"+dropp.get(i)+"</option>");
                                }
                            %>
                            </select>
                            
                        </div>
                        <div class="col">

                            <label for="lbl_cantidad" >Cantidad:</label>
                            <input type="number" step="1" min="1" name="txt_cantidad" id="txt_cantidad" class="form-control" placeholder="1" required>
                        
                        </div>
                        <div class="col">
                            
                            <label for="lbl_precio" >Precio:</label>
                            <input type="number" step="0.01" min="0.01" name="txt_precio" id="txt_precio" class="form-control" placeholder="100.00" required>
                            <br>
                        </div>
                    </div>
                            <div class="row" id="btn_detalle">
                                <div class="col">
                                <button name="btn_agregar_detalle" id="btn_agregar_detalle" value="Agregar" class="btn btn-primary">Agregar</button>
                                </div><div class="col">
                                <button name="btn_modificar_detalle" id="btn_modificar_detalle" value="Modificar" class="btn btn-primary">Modificar</button>
                                </div><div class="col">
                                <button name="btn_eliminar_detalle" id="btn_eliminar_detalle" value="Eliminar" class="btn btn-primary">Eliminar</button>
                                </div>
                            </div>                            
                    <br>    
                    <div class="row">
                        <div class="col">
                        <table class="table table-striped">
                        <thead>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Precio unitario</th>
                        </thead>
                        <tbody id="tbl_compra_detalle">
                            <%
                            Compra_detalle c=new Compra_detalle();
                            DefaultTableModel modelo= new DefaultTableModel();
                            modelo=c.mostrar();
                            for(int tt=0;tt<modelo.getRowCount();tt++){
                                out.println("<tr data-idproducto="+modelo.getValueAt(tt,5)+">");
                                out.println("<td>"+modelo.getValueAt(tt,2)+"</td>");
                                out.println("<td>"+modelo.getValueAt(tt,3)+"</td>");
                                out.println("<td>"+modelo.getValueAt(tt,4)+"</td>");
                                out.println("</tr>");
                            }
                            %>
                        </tbody>
                    </table>                        
                    </div>
                </div>
                </div>     
                        <br>
                <div class="row" id="btn_detalle">
                    <div class="col">
                    <button name="btn_agregar" id="btn_agregar" value="Agregar" class="btn btn-primary">Agregar</button>
                    </div><div class="col">
                    <button name="btn_modificar" id="btn_modificar" value="Modificar" class="btn btn-primary">Modificar</button>
                    </div><div class="col">
                    <button name="btn_eliminar_" id="btn_eliminar" value="Eliminar" class="btn btn-primary">Eliminar</button>
                    </div>
                </div>
                <br>
            </form>
            <h3>Compras</h3>       
                <table class="table table-striped">
                    <thead>
                    <th>No. Orden</th>
                    <th>Proveedor</th>
                    <th>Fecha de la orden</th>
                    </thead>
                    <tbody id="tbl_compras">
                        <%
                        Compra cd=new Compra();
                        DefaultTableModel model= new DefaultTableModel();
                        model=cd.mostrar();
                        for(int t=0;t<model.getRowCount();t++){
                            out.println("<tr data-idcompra="+ model.getValueAt(t,0)+" data-idproveedor="+model.getValueAt(t,5)+">");                            
                            out.println("<td>"+model.getValueAt(t,1)+"</td>");
                            out.println("<td>"+model.getValueAt(t,2)+"</td>");
                            out.println("<td>"+model.getValueAt(t,3)+"</td>");
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
            $('#tbl_compras').on('click','tr td',function(evt){
                //Eventos de tabla inferior
                var targett, idcompra, no_orden, proveedor, fecha_orden;
                targett = $(event.target);
                idcompra=targett.parent().data('idcompra');
                proveedor=targett.parent().data('idproveedor');
                no_orden=targett.parent("tr").find("td").eq(0).html();
                fecha_orden=targett.parent("tr").find("td").eq(2).html();
                $("#txt_id").val(idcompra);
                $("#txt_no_orden").val(no_orden);
                $("#txt_fecha_orden").val(fecha_orden);
                $("#drop_proveedor").val(proveedor);
            });
        </script>
        <script type="text/javascript">
            $('#tbl_compra_detalle').on('click','tr td',function(evt){
                var target, idproducto, cantidad, precio;
                target = $(event.target);
                idproducto=target.parent().data('idproducto');
                cantidad=target.parent("tr").find("td").eq(1).html();
                precio=target.parent("tr").find("td").eq(2).html();
                $("#drop_producto").val(idproducto);
                $("#txt_cantidad").val(cantidad);            
                $("#txt_precio").val(precio);
                var 
            });
        </script>
        <<script type="text/javascript">
            function actualizardetalles(id,array){
                var idcompra=id;
                var datos=array;
                var tabladatos,filas="";
                for (var i = 0; i < datos.length; i++) {
                    filas=filas+"<tr data-idproducto="+datos[i][0]+"><td>"+datos[i][1]+"</td><td>"+datos[i][2]+"</td><td>"+datos[i][3]+"</td></tr>";
                }
            }
        </script>
        <%}else{%>
            <h1>No tienes permiso para ver esta página.</h1>
        <%}
        }else{%>
            <h1>No tienes permiso para ver esta página.</h1>
        <%}%>
    </body>
</html>
