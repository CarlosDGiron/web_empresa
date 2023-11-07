<%-- 
    Document   : NuevaCompra
    Created on : 29 oct 2023, 6:27:20 p. m.
    Author     : cana0
--%>

<%@page import="modelo.Producto"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Compra_detalle"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.Proveedor"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Compra</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
        %>
    </head>
    <body>
        <%
            int idUsuario = 0;
            if (request.getSession().getAttribute("idUsuario") != null) {
                idUsuario = (int) request.getSession().getAttribute("idUsuario");
            }
            if (idUsuario != 0) {
                Usuario u = new Usuario();
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 9)) {%>
        <div class="container-fluid">
            <form action="sr_login" method="post" class="form-group">
                <nav class="navbar navbar-dark bg-primary">
                    <span class="navbar-text"><h5 style="color: white;"><% out.println(request.getSession().getAttribute("nombre"));%></h5></span>
                    <a class="navbar-brand mx-auto" href="Principal.jsp">Web Empresa</a>
                    <button class="btn btn-primary" id="accion" name="accion" value="Logout">Cerrar Sesión</button>
                </nav>
            </form>
        </div>                
    <center><h1><br>Formulario Compras<br></h1></center>
    <div class="container">                        
        <form id="form" action="sr_compras" method="post" class="form-group" >
            <div class="row">
                <div class="col">
                    <label for="lbl_no_orden" >No. Orden:</label>
                    <input type="number" name="txt_no_orden" id="txt_no_orden" class="form-control"  placeholder="Ejemplo: 20230101001" required>
                </div><div class="col">
                    <label for="lbl_proveedor" >Proveedor:</label>
                    <select name="drop_proveedor" id="drop_proveedor" class="form-control">
                        <%Proveedor m = new Proveedor();
                            HashMap<String, String> drop = m.drop_proveedor();
                            for (String i : drop.keySet()) {
                                out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                                            }%>
                    </select>
                </div><div class="col">
                    <label for="lbl_fecha_orden" >Fecha de la orden:</label>
                    <input type="date" name="txt_fecha_orden" id="txt_fecha_orden" class="form-control" required>
                </div>
            </div><br>
            <div class="container" style="border: 2px solid grey;">
                <h3>Detalle de compra</h3>   
                <div class="row">
                    <div class="col">
                        <label for="lbl_producto" >Producto:</label>
                        <select name="drop_producto" id="drop_producto" class="form-control">
                            <%Producto p = new Producto();
                                HashMap<String, String> dropp = p.drop_producto();
                                for (String i : dropp.keySet()) {
                                    out.println("<option value='" + i + "'>" + dropp.get(i) + "</option>");
                                                }%>
                        </select>
                    </div>
                    <div class="col">
                        <label for="lbl_cantidad" >Cantidad:</label>
                        <input type="number" step="1" min="1" max=""name="txt_cantidad" id="txt_cantidad" class="form-control" placeholder="1" required>
                    </div>
                    <div class="col">
                        <label for="lbl_precio" >Precio unitario:</label>
                        <input type="number" step="0.01" min="0.01" name="txt_precio" id="txt_precio" class="form-control" placeholder="100.00" required>
                    </div>
                    <div class="col">         
                        <br>
                        <button type="button" class="btn btn-primary btn-lg btn-block" id="btn_add">+</button>
                    </div>
                    <script>
                        document.addEventListener('DOMContentLoaded', function (event) {
                            document.getElementById('txt_cantidad').value = 1;
                            document.getElementById('txt_precio').value = 100;
                        });
                    </script>
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
                            </tbody>
                        </table>                        
                    </div>
                </div> 
            </div><br>
            <button name="btn_agregar" id="btn_agregar" value="Agregar" class="btn btn-primary btn-lg btn-block">Agregar</button>
            <input name="noproductos" id="noproductos" type="hidden" value="0">
        </form>
    </div>
    <% } else {%>
    <h1>Usted no tiene permisos.</h1>
    <%}
                    }%>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    <script>
        // Función para insertar datos en la tabla
        document.getElementById("btn_add").addEventListener("click", function () {
            //obtenemos los datos
            var combo = document.getElementById("drop_producto");
            var idproducto = combo.value;
            var producto = combo.options[idproducto - 1].text;
            var cantidad = document.getElementById("txt_cantidad").value;
            var precio = document.getElementById("txt_precio").value;
            var table = document.getElementById("tbl_compra_detalle");
            //agregamos las filas a la tabla
            var row = table.insertRow();
            row.setAttribute('data-idProducto', idproducto);
            var c1 = row.insertCell(0);
            var c2 = row.insertCell(1);
            var c3 = row.insertCell(2);
            c1.innerHTML = producto;
            c2.innerHTML = cantidad;
            c3.innerHTML = precio;
            document.getElementById("drop_producto").selectedIndex = 0;
            document.getElementById("txt_cantidad").value = 1;
            document.getElementById("txt_precio").value = 100;
            //agregarmos hidden input para exportar los datos al servlet
            var noproductos = document.getElementById("noproductos");
            var idp = document.createElement("input");
            var cant = document.createElement("input");
            var prec = document.createElement("input");
            idp.type = "hidden";
            cant.type = "hidden";
            prec.type = "hidden";
            //settamos nombres y id unicos segun el numero de fila, iniciando desde 0
            idp.id = "idProducto" + noproductos.value;
            cant.id = "cantidad" + noproductos.value;
            prec.id = "precio" + noproductos.value;
            idp.name = "idProducto" + noproductos.value;
            cant.name = "cantidad" + noproductos.value;
            prec.name = "precio" + noproductos.value;
            idp.value = idproducto;
            cant.value = cantidad;
            prec.value = precio;
            var fom = document.getElementById("form");
            //agregarmos los inputs
            fom.appendChild(idp);
            fom.appendChild(cant);
            fom.appendChild(prec);
            //sumamos al contado de detalle de productos
            noproductos.value = parseInt(noproductos.value) + 1;
        });
    </script>
</body>
</html>
