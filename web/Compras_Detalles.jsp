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
        int idUsuario=0;
        Proveedor m;
        Compra_detalle cd;
        Producto p;
        HashMap<String,String> drop,dropp;
        DefaultTableModel modelo;
        if(request.getSession().getAttribute("idUsuario")!=null){
            idUsuario=(int)request.getSession().getAttribute("idUsuario");
        }
        if(idUsuario!=0){
            Usuario u=new Usuario();
            if(u.tienePermisoId(idUsuario, 1)){ %>
                <center><h1><br>Historial de Compras<br></h1></center>
                <div class="container"><br>
                    <form action="sr_compras" method="post" class="form-group" >    
                        <table class="table table-striped">
                            <thead>
                            <th>No. Orden</th>
                            <th>Proveedor</th>
                            <th>Fecha de la orden</th>
                            </thead>
                            <tbody id="tbl_compras">
                                <%
                                Compra c=new Compra();
                                DefaultTableModel model= new DefaultTableModel();
                                model=c.mostrar();
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
                        <input name="idCompra" id="idCompra" type="hidden" value="0">
                        <%cd= new Compra_detalle();
                        modelo= new DefaultTableModel();
                        for(int i=1;i<c.maxIdCompra()+1;i++){
                        if(!c.existe(i)){
                            continue;
                        }else{
                            modelo=cd.mostrarPorId(i);
                            //Imprimir como hiden todos los uinputs y varios modales que se desplieguen segun el id de cada fila de comrpas
                            %>
                            <!--modal dinamico-->
                            <div class="modal" tabindex="-1" id="modal<%out.print(i);%>" name="modal<%out.print(i);%>">
                                <div class="modal-dialog modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">No. de Orden <%out.print(c.ordenPorId(i));%></h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="row">
                                                <div class="col">
                                                    <label for="lbl_no_orden" >No. Orden:</label>
                                                    <input type="text" name="txt_no_orden<%out.print(i);%>" id="txt_no_orden<%out.print(i);%>" class="form-control"  value="<%out.print(model.getValueAt(i-1,1));%>" required>
                                                    </div><div class="col">
                                                    <label for="lbl_proveedor" >Proveedor:</label>
                                                    <select name="drop_proveedor<%out.print(i);%>" id="drop_proveedor<%out.print(i);%>" class="form-control">
                                                        <% m=new Proveedor();
                                                        drop=m.drop_proveedor();
                                                        for (String jk: drop.keySet()){
                                                            if(jk.equals(model.getValueAt(i-1,5))){
                                                                out.println("<option value='"+jk+"' selected>"+drop.get(jk)+"</option>");
                                                            }else{
                                                                out.println("<option value='"+jk+"'>"+drop.get(jk)+"</option>");
                                                            }
                                                        }%>
                                                    </select>
                                                </div><div class="col">
                                                    <label for="lbl_fecha_orden" >Fecha de la orden:</label>
                                                    <input type="date" name="txt_fecha_orden<%out.print(i);%>" id="txt_fecha_orden<%out.print(i);%>" class="form-control" value="<%out.print(model.getValueAt(i-1,3));%>" required>
                                                </div>
                                            </div>
                                                <br>
                                            <table class="table table-striped">
                                                <thead>
                                                    <th>Producto</th>
                                                    <th>Cantidad</th>
                                                    <th>Precio unitario</th>
                                                </thead>
                                                <tbody id="tbl_compra_detalle<%out.print(i);%>">
                                                <%
                                                for(int tt=0;tt<modelo.getRowCount();tt++){
                                                    out.println("<tr data-idproducto="+modelo.getValueAt(tt,5)+" data-idcompra="+modelo.getValueAt(tt,0)+" >"); 
                                                    out.println("<td><select name=\"drop_producto"+modelo.getValueAt(tt,0)+"\" id=\"drop_producto"+modelo.getValueAt(tt,0)+"\" class=\"form-control\" value=\""+modelo.getValueAt(tt,5)+"\">");
                                                    p=new Producto();
                                                    dropp=p.drop_producto();
                                                    for (String ks: dropp.keySet()){
                                                        if(ks.equals(modelo.getValueAt(tt,5))){
                                                            out.println("<option value='"+ks+"' selected>"+dropp.get(ks)+"</option>");
                                                        }else{
                                                           out.println("<option value='"+ks+"'>"+dropp.get(ks)+"</option>");
                                                        }
                                                    }                                                
                                                    out.println("</select></td>");                                                    
                                                    out.println("<td><input type=\"number\" step=\"1\" min=\"1\" name=\"cantidad"+modelo.getValueAt(tt,0)+"\" id=\"cantidad"+modelo.getValueAt(tt,0)+"\" value=\""+modelo.getValueAt(tt,3)+"\"></td>");
                                                    out.println("<td><input type=\"number\" step=\"0.01\" min=\"0.01\" name=\"precio"+modelo.getValueAt(tt,0)+"\" id=\"precio"+modelo.getValueAt(tt,0)+"\" value=\""+modelo.getValueAt(tt,4)+"\"></td>");
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
                    $('#tbl_compras').on('click','tr td',function(evt){
                        //Obtener datos de la tabla para abrir el modal
                        var targett, idcompra;
                        targett = $(event.target);
                        idcompra=targett.parent().data("idcompra");
                        var modalid= "modal"+parseInt(idcompra);
                        console.log(modalid);
                        $("#idCompra").val(idcompra);
                        $("#"+modalid).modal('show');
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
