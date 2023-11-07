/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Venta;
import modelo.Venta_detalle;
import modelo.Usuario;
import modelo.Producto;

/**
 *
 * @author cana0
 */
public class sr_ventas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            int idUsuario=1;
            boolean inventariovalido=true;
            Producto p=new Producto();
            if(request.getSession().getAttribute("idUsuario")!=null){
                idUsuario=(int)request.getSession().getAttribute("idUsuario");
            }
            if(idUsuario!=0){
                Usuario u=new Usuario();
                if(u.tienePermisoId(idUsuario, 1)){
                    //Permisos adecuados
                    int cantidad,idproducto,idventa;
                    double precio;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
                    Date date = new Date();  
                    Venta venta;
                    Venta_detalle ventadetalle =new Venta_detalle();
                    if("Agregar".equals(request.getParameter("btn_agregar"))){
                        //int idVenta, int nofactura, String serie, String fechafactura, int idCliente, int idEmpleado, String fechaingreso
                        
                        for (int i=0;i<Integer.parseInt(request.getParameter("noproductos"));i++){
                            cantidad=Integer.parseInt(request.getParameter("cantidad"+i));
                            idproducto=Integer.parseInt(request.getParameter("idProducto"+i));
                            if(cantidad>p.maxVenta(idproducto)){
                                inventariovalido=false;
                            }
                        }
                        venta = new Venta(0,Integer.parseInt(request.getParameter("txt_no_factura")),request.getParameter("txt_serie"),request.getParameter("txt_fechafactura"),Integer.parseInt(request.getParameter("drop_cliente")),Integer.parseInt(request.getParameter("drop_empleado")),formatter.format(date));
                        if (inventariovalido){
                            if (venta.agregar()==1){
                                int contadordeinserts=0;
                                idventa=venta.getIdVenta();
                                for (int i=0;i<Integer.parseInt(request.getParameter("noproductos"));i++){
                                    idproducto=Integer.parseInt(request.getParameter("idProducto"+i));
                                    cantidad=Integer.parseInt(request.getParameter("cantidad"+i));
                                    precio=Double.parseDouble(request.getParameter("precio"+i));
                                    ventadetalle=new Venta_detalle(0,idventa,idproducto,cantidad,precio);
                                    if(ventadetalle.agregar()>0){
                                        contadordeinserts++;
                                    }
                                }
                                out.println("<h1>Ingreso exitoso</h1>");
                                out.println("<p>Se agrego la compra con un total de "+contadordeinserts+" productos.</p>");
                            }else{
                                out.println("<h1>No se pudo ingresar el registro.</h1>");
                            }
                        }else{
                            out.println("<h1>Uno de los productos no cuenta con suficiente inventario para la venta.</h1>");
                        }
                        out.println("<a href ='NuevaVenta.jsp'>Regresar</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }else if("Modificar".equals(request.getParameter("btn_modificar"))){
                        idventa=Integer.parseInt(request.getParameter("idVenta"));
                        //int idVenta, int nofactura, String serie, String fechafactura, int idCliente, int idEmpleado, String fechaingreso
                        venta=new Venta(idventa,Integer.parseInt(request.getParameter("txt_no_factura"+String.valueOf(idventa))),request.getParameter("txt_serie"+String.valueOf(idventa)),request.getParameter("txt_fechafactura"+String.valueOf(idventa)),Integer.parseInt(request.getParameter("drop_cliente"+String.valueOf(idventa))),Integer.parseInt(request.getParameter("drop_empleado"+String.valueOf(idventa))),formatter.format(date));
                        if (venta.modificar()==1){
                            int contadordeinserts=0; 
                            for (int i:ventadetalle.idDetallePorIdVenta(idventa)){
                                System.out.println("IdVentaDetalle"+i);
                                idproducto=Integer.parseInt(request.getParameter("drop_producto"+String.valueOf(i)));
                                cantidad=Integer.parseInt(request.getParameter("cantidad"+String.valueOf(i)));
                                precio=Double.parseDouble(request.getParameter("precio"+String.valueOf(i)));
                                ventadetalle=new Venta_detalle(i,idventa,idproducto,cantidad,precio);
                                if(ventadetalle.modificar()>0){
                                    contadordeinserts++;
                                }
                            }
                            out.println("<h1>Registro modificado.</h1>");
                        }else{
                            out.println("<h1>No se pudo modificar el registro.</h1>");
                        }
                        out.println("<a href ='Ventas_Detalles.jsp'>Regresar</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }else if("Eliminar".equals(request.getParameter("btn_eliminar"))){
                        venta = new Venta();
                        idventa=Integer.parseInt(request.getParameter("idVenta"));
                        venta.setIdVenta(idventa);
                        ventadetalle.setIdVenta(idventa);
                        if (ventadetalle.eliminar()){
                            if(venta.eliminar()){
                                out.println("<h1>Registro eliminado.</h1>");
                            }else{
                                out.println("<h1>No se pudo eliminar el registro.</h1>");
                            }
                        }else{
                            out.println("<h1>No se pudo eliminar el registro.</h1>");
                        }
                        out.println("<a href ='Ventas_Detalles.jsp'>Regresar</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }                    
                }
            }else{
                    out.println("<h1>Usted no tiene permisos.</h1>");
                    out.println("</body>");
                    out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
