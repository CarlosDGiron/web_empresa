<%-- 
    Document   : Proveedores
    Created on : 25 oct 2023, 6:59:24 a. m.
    Author     : cana0
--%>
<%@page import="modelo.Proveedor" %>
<%@page import="modelo.Usuario" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page import="java.util.HashMap" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proveedores</title>
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
                if (u.tienePermisoId(idUsuario, 1) | u.tienePermisoId(idUsuario, 11)) { %>
        <div class="container-fluid">
            <form action="sr_login" method="post" class="form-group">
                <nav class="navbar navbar-dark bg-primary">
                    <span class="navbar-text"><h5 style="color: white;"><% out.println(request.getSession().getAttribute("nombre"));%></h5></span>
                    <a class="navbar-brand mx-auto" href="Principal.jsp">Web Empresa</a>
                    <button class="btn btn-primary" id="accion" name="accion" value="Logout">Cerrar Sesión</button>
                </nav>
            </form>
        </div>
    <center><h1>Formulario Proveedores</h1></center>
    <div class="container">
        <form action="sr_proveedor" method="post" class="form-group" >
            <div class="row">
                <div class="col">
                    <label for="lbl_id" >ID:</label>
                    <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
                </div><div class="col">
                    <label for="lbl_proveedor" >Proveedor:</label>
                    <input type="text" name="txt_proveedor" id="txt_proveedor" class="form-control" placeholder="Ejemplo: Nombre" required>  
                </div><div class="col">
                    <label for="lbl_nit" >NIT:</label>
                    <input type="text" name="txt_nit" id="txt_nit" class="form-control" placeholder="Ejemplo: 1234567890" required>
                </div><div class="col">
                    <label for="lbl_direccion" >Direccion:</label>
                    <input type="text" name="txt_direccion" id="txt_direccion" class="form-control"  placeholder="# Calle  Avenida Zona Ciudad Departamento"required>
                </div><div class="col">
                    <label for="lbl_telefono" >Telefono:</label>
                    <input type="text" name="txt_telefono" id="txt_telefono" class="form-control" placeholder="Ejemplo: 12345678" required>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col">
                    <button name="accion" id="accion" value="Agregar" class="btn btn-primary btn-block">Agregar</button>
                </div><div class="col">
                    <button name="accion" id="accion" value="Modificar" class="btn btn-primary btn-block">Modificar</button>
                </div><div class="col">
                    <button name="accion" id="accion" value="Eliminar" class="btn btn-primary btn-block">Eliminar</button>
                </div><div class="col">
                    <button name="accion" id="accion" value="Compras" class="btn btn-primary btn-block" formnovalidate>Ir a Compras</button>
                </div>
            </div>
            <br>
        </form>
        <table class="table table-striped">
            <thead>
            <th>Proveedor</th>
            <th>NIT</th>
            <th>Dirección</th>
            <th>Telefono</th>
            </thead>
            <tbody id="tbl_proveedor">
                <%
                    Proveedor c = new Proveedor();
                    DefaultTableModel model = new DefaultTableModel();
                    model = c.mostrar();
                    for (int t = 0; t < model.getRowCount(); t++) {
                        out.println("<tr data-id=" + model.getValueAt(t, 0) + " >");
                        out.println("<td>" + model.getValueAt(t, 1) + "</td>");
                        out.println("<td>" + model.getValueAt(t, 2) + "</td>");
                        out.println("<td>" + model.getValueAt(t, 3) + "</td>");
                        out.println("<td>" + model.getValueAt(t, 4) + "</td>");
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
        $('#tbl_proveedor').on('click', 'tr td', function (evt) {
            var target, id, proveedor, nit, direccion, telefono;
            target = $(event.target);
            id = target.parent().data('id');
            proveedor = target.parent("tr").find("td").eq(0).html();
            nit = target.parent("tr").find("td").eq(1).html();
            direccion = target.parent("tr").find("td").eq(2).html();
            telefono = target.parent("tr").find("td").eq(3).html();

            $("#txt_id").val(id);
            $("#txt_proveedor").val(proveedor);
            $("#txt_nit").val(nit);
            $("#txt_telefono").val(telefono);
            $("#txt_direccion").val(direccion);
        });
    </script>
    <%} else {%>
    <h1>No tienes permiso para ver esta página.</h1>
    <%}
    } else {%>
    <h1>No tienes permiso para ver esta página.</h1>
    <%}%>
</body>
</html>
