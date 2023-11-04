<%-- 
    Document   : Ventas_Detalles
    Created on : 2 nov 2023, 8:41:38 p. m.
    Author     : cana0
--%>

<%@page import="modelo.Venta" %>
<%@page import="modelo.Venta_detalle" %>
<%@page import="modelo.Cliente" %>
<%@page import="modelo.Empleado" %>
<%@page import="modelo.Producto" %>
<%@page import="modelo.Usuario" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historico de Ventas</title>
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
        int idUsuario=0;
        Venta_detalle ventadetalle;
        Producto p;
        HashMap<String,String> drop,dropp;
        DefaultTableModel modelventadetalle;
        if(request.getSession().getAttribute("idUsuario")!=null){
            idUsuario=(int)request.getSession().getAttribute("idUsuario");
        }
        if(idUsuario!=0){
            Usuario u=new Usuario();
            if(u.tienePermisoId(idUsuario, 1)){ %>
                <center><h1><br>Historial de Ventas<br></h1></center>
                <div class="container"><br>
                    <form action="sr_ventas" method="post" class="form-group" >    
                        <table class="table table-striped">
                            <thead>
                            <th>No. Factura</th>
                            <th>Serie</th>
                            <th>Fecha de la factura</th>
                            <th>Cliente</th>
                            <th>Vendedor</th>
                            </thead>
                            <tbody id="tbl_ventas">
                                <%
                                Venta venta=new Venta();
                                DefaultTableModel modelventa= new DefaultTableModel();
                                modelventa=venta.mostrar();
                                for(int t=0;t<modelventa.getRowCount();t++){
                                    out.println("<tr data-idventa="+ modelventa.getValueAt(t,0)+">");
                                    out.println("<td>"+modelventa.getValueAt(t,1)+"</td>");
                                    out.println("<td>"+modelventa.getValueAt(t,2)+"</td>");
                                    out.println("<td>"+modelventa.getValueAt(t,3)+"</td>");
                                    out.println("<td>"+modelventa.getValueAt(t,4)+"</td>");
                                    out.println("<td>"+modelventa.getValueAt(t,5)+"</td>");
                                    out.println("</tr>");
                                }
                                %>
                            </tbody>
                        </table>
                        <input name="idVenta" id="idVenta" type="hidden" value="0">
                        <%ventadetalle= new Venta_detalle();
                        modelventadetalle= new DefaultTableModel();
                        for(int i=1;i<venta.maxIdVenta()+1;i++){
                            if(!venta.existe(i)){
                                continue;
                            }else{                            
                                modelventadetalle=ventadetalle.mostrarPorId(i);
                                //Imprimir como hiden todos los uinputs y varios modales que se desplieguen segun el id de cada fila de comrpas
                                %>
                                <!--modal dinamico-->
                                <div class="modal" tabindex="-1" id="modal<%out.print(i);%>" name="modal<%out.print(i);%>">
                                    <div class="modal-dialog modal-xl">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title">No. de Factura <%out.print(venta.facturaPorId(i));%></h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="row">
                                                    <div class="col">
                                                        <label for="lbl_no_orden" >No. Factura:</label>
                                                        <input type="number" name="txt_no_factura<%out.print(i);%>" id="txt_no_factura<%out.print(i);%>" class="form-control"  value="<%out.print(modelventa.getValueAt(i-1,1));%>" required>
                                                    </div><div class="col">
                                                        <label for="lbl_serie" >Serie:</label>
                                                        <input type="text" name="txt_serie<%out.print(i);%>" id="txt_serie<%out.print(i);%>" class="form-control"  oninput="limitarSerie(this)" value="<%out.print(modelventa.getValueAt(i-1,2));%>" required>
                                                    </div><div class="col">
                                                        <label for="lbl_fechafactura" >Fecha de la factura:</label>
                                                        <input type="date" name="txt_fechafactura<%out.print(i);%>" id="txt_fechafactura<%out.print(i);%>" class="form-control" value="<%out.print(modelventa.getValueAt(i-1,3));%>" required>
                                                    </div><div class="col">
                                                        <label for="lbl_cliente" >Cliente:</label>
                                                        <select name="drop_cliente<%out.print(i);%>" id="drop_cliente<%out.print(i);%>" class="form-control">
                                                            <% Cliente cliente=new Cliente();
                                                            drop=cliente.drop_cliente();
                                                            for (String jk: drop.keySet()){
                                                                if(jk.equals(modelventa.getValueAt(i-1,4))){
                                                                    out.println("<option value='"+jk+"' selected>"+drop.get(jk)+"</option>");
                                                                }else{
                                                                    out.println("<option value='"+jk+"'>"+drop.get(jk)+"</option>");
                                                                }
                                                            }%>
                                                        </select>
                                                    </div><div class="col">
                                                        <label for="lbl_empleado" >Vendedor:</label>
                                                        <select name="drop_empleado<%out.print(i);%>" id="drop_empleado<%out.print(i);%>" class="form-control">
                                                            <% Empleado empleado=new Empleado();
                                                            drop=empleado.drop_empleado();
                                                            for (String jk: drop.keySet()){
                                                                if(jk.equals(modelventa.getValueAt(i-1,5))){
                                                                    out.println("<option value='"+jk+"' selected>"+drop.get(jk)+"</option>");
                                                                }else{
                                                                    out.println("<option value='"+jk+"'>"+drop.get(jk)+"</option>");
                                                                }
                                                            }%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <br>
                                                <table class="table table-striped">
                                                    <thead>
                                                        <th>Producto</th>
                                                        <th>Cantidad</th>
                                                        <th>Precio unitario</th>
                                                    </thead>
                                                    <tbody id="tbl_venta_detalle<%out.print(i);%>">
                                                    <%
                                                    for(int tt=0;tt<modelventadetalle.getRowCount();tt++){
                                                        out.println("<tr data-idproducto="+modelventadetalle.getValueAt(tt,5)+" data-idventa="+modelventadetalle.getValueAt(tt,0)+" >"); 
                                                        out.println("<td><select name=\"drop_producto"+modelventadetalle.getValueAt(tt,0)+"\" id=\"drop_producto"+modelventadetalle.getValueAt(tt,0)+"\" class=\"form-control\" value=\""+modelventadetalle.getValueAt(tt,5)+"\">");
                                                        p=new Producto();
                                                        dropp=p.drop_producto();
                                                        for (String ks: dropp.keySet()){
                                                            if(ks.equals(modelventadetalle.getValueAt(tt,5))){
                                                                out.println("<option value='"+ks+"' selected>"+dropp.get(ks)+"</option>");
                                                            }else{
                                                               out.println("<option value='"+ks+"'>"+dropp.get(ks)+"</option>");
                                                            }
                                                        }                                                
                                                        out.println("</select></td>");                                                    
                                                        out.println("<td><input type=\"number\" step=\"1\" min=\"1\" name=\"cantidad"+modelventadetalle.getValueAt(tt,0)+"\" id=\"cantidad"+modelventadetalle.getValueAt(tt,0)+"\" value=\""+modelventadetalle.getValueAt(tt,3)+"\"></td>");
                                                        out.println("<td><input type=\"number\" step=\"0.01\" min=\"0.01\" name=\"precio"+modelventadetalle.getValueAt(tt,0)+"\" id=\"precio"+modelventadetalle.getValueAt(tt,0)+"\" value=\""+modelventadetalle.getValueAt(tt,4)+"\"></td>");
                                                        out.println("</tr>");
                                                    }%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-secondary" name="btn_eliminar" id="btn_eliminar" value="Eliminar">Eliminar orden de compra</button>
                                            <button class="btn btn-primary" name="btn_modificar" id="btn_modificar" value="Modificar">Modificar detalle</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <%}
                        }%>
                    </form>
                </div>
                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
                <script type="text/javascript">
                    $('#tbl_ventas').on('click','tr td',function(evt){
                        //Obtener datos de la tabla para abrir el modal
                        var targett, idventa;
                        targett = $(event.target);
                        idventa=targett.parent().data("idventa");
                        var modalid= "modal"+parseInt(idventa);
                        $("#idVenta").val(idventa);
                        $("#"+modalid).modal('show');
                    });
                </script>
                <script>
                    function limitarSerie(input) {
                        var valor = input.value;
                        valor = valor.replace(/[^a-zA-Z]/g, '');
                        if (valor.length > 1) {
                            valor = valor.charAt(0);
                        }
                        input.value = valor;
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
