<%-- 
    Document   : NuevaVenta
    Created on : 2 nov 2023, 6:24:39 p. m.
    Author     : cana0
--%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="modelo.Compra_detalle"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelo.Cliente"%>
<%@page import="modelo.Empleado"%>
<%@page import="modelo.Producto"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Venta</title>
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
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 4)) {%> 
        <div class="container-fluid">
            <form action="sr_login" method="post" class="form-group">
                <nav class="navbar navbar-dark bg-primary">
                    <span class="navbar-text"><h5 style="color: white;"><% out.println(request.getSession().getAttribute("nombre"));%></h5></span>
                    <a class="navbar-brand mx-auto" href="Principal.jsp">Web Empresa</a>
                    <button class="btn btn-primary" id="accion" name="accion" value="Logout">Cerrar Sesión</button>
                </nav>
            </form>
        </div>
    <center><h1><br>Formulario Venta<br></h1></center>
    <div class="container">                        
        <form id="form" action="sr_ventas" method="post" class="form-group" >
            <div class="row">
                <div class="col">
                    <label for="lbl_no_factura" >No. Factura</label>
                    <input type="number" name="txt_no_factura" id="txt_no_factura" class="form-control"  placeholder="Ejemplo: 20230101001" required>
                </div><div class="col">
                    <label for="lbl_serie" >Serie</label>
                    <input type="text" name="txt_serie" id="txt_serie" class="form-control"  placeholder="Ejemplo: A" oninput="limitarSerie(this)" required>
                </div><div class="col">
                    <label for="lbl_cliente" >Cliente:</label>
                    <select name="drop_cliente" id="drop_cliente" class="form-control">
                        <%Cliente cliente = new Cliente();
                            HashMap<String, String> dropcliente = cliente.drop_cliente();
                            for (String i : dropcliente.keySet()) {
                                out.println("<option value='" + i + "'>" + dropcliente.get(i) + "</option>");
                                            }%>
                    </select>
                </div><div class="col">
                    <label for="lbl_empleado" >Vendedor:</label>
                    <select name="drop_empleado" id="drop_empleado" class="form-control">
                        <%Empleado empleado = new Empleado();
                            HashMap<String, String> dropempleado = empleado.drop_empleado();
                            for (String i : dropempleado.keySet()) {
                                out.println("<option value='" + i + "'>" + dropempleado.get(i) + "</option>");
                                            }%>
                    </select>
                </div><div class="col">
                    <label for="lbl_fechafactura" >Fecha de la factura:</label>
                    <input type="date" name="txt_fechafactura" id="txt_fechafactura" class="form-control" required>
                </div>
            </div><br>
            <div class="container" style="border: 2px solid grey;">
                <h3>Detalle de venta</h3>   
                <div class="row">
                    <div class="col">
                        <label for="lbl_producto" >Producto:</label>
                        <select name="drop_producto" id="drop_producto" class="form-control"onchange="cambiarPrecio()">
                            <%Producto producto = new Producto();
                                HashMap<String, String> droproducto = producto.drop_producto();
                                for (String i : droproducto.keySet()) {
                                    out.println("<option value='" + i + "'>" + droproducto.get(i) + "</option>");
                                                }%>
                        </select>
                    </div>
                    <div class="col">
                        <label for="lbl_cantidad" >Cantidad:</label>
                        <input type="number" step="1" min="1" name="txt_cantidad" id="txt_cantidad" class="form-control" placeholder="1" required>
                    </div>
                    <div class="col">
                        <label for="lbl_precio" >Precio unitario:</label>
                        <input type="number" step="0.01" min="0.01" name="txt_precio" id="txt_precio" class="form-control" readonly>
                        <select name="drop_productoprecio" id="drop_productoprecio" class="form-control" style="display: none;">
                            <%
                                HashMap<String, String> droproductoprecio = producto.drop_productoprecio();
                                for (String i : droproductoprecio.keySet()) {
                                    out.println("<option value='" + i + "'>" + droproductoprecio.get(i) + "</option>");
                                                }%>
                        </select>
                    </div>
                    <div class="col">         
                        <br>
                        <button type="button" class="btn btn-primary btn-lg btn-block" id="btn_add">+</button>
                    </div>
                    <script>
                        document.addEventListener('DOMContentLoaded', function (event) {
                            document.getElementById('txt_cantidad').value = 1;
                            cambiarPrecio();
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
                            <tbody id="tbl_venta_detalle">
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
            var table = document.getElementById("tbl_venta_detalle");
            //agregamos las filas a la tabla
            var row = table.insertRow();
            row.setAttribute('data-idProducto', idproducto);
            var c1 = row.insertCell(0);
            var c2 = row.insertCell(1);
            var c3 = row.insertCell(2);
            c1.innerHTML = producto;
            c2.innerHTML = cantidad;
            c3.innerHTML = precio;
            combo.selectedIndex = 0;
            document.getElementById("txt_cantidad").value = 1;
            var productoprecio = document.getElementById("drop_productoprecio");
            var precioventa = document.getElementById("txt_precio");
            var precioproducto = productoprecio.options[combo.selectedIndex].text;
            precioventa.value = parseFloat(precioproducto);
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
    <script>
        function cambiarPrecio() {
            var producto = document.getElementById("drop_producto");
            var productoprecio = document.getElementById("drop_productoprecio");
            var precioventa = document.getElementById("txt_precio");
            var precioproducto = productoprecio.options[producto.selectedIndex].text;
            precioventa.value = parseFloat(precioproducto);
        }
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
</body>
</html>

