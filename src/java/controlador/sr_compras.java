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
import modelo.Compra;
import modelo.Compra_detalle;
import modelo.Usuario;

/**
 *
 * @author cana0
 */
public class sr_compras extends HttpServlet {

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
            if(request.getSession().getAttribute("idUsuario")!=null){
                idUsuario=(int)request.getSession().getAttribute("idUsuario");
            }
            if(idUsuario!=0){
                Usuario u=new Usuario();
                if(u.tienePermisoId(idUsuario, 1)||u.tienePermisoId(idUsuario, 9)||u.tienePermisoId(idUsuario, 10)){
                    //Permisos adecuados
                    int cantidad,idproducto,idcompra;
                    double precio;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
                    Date date = new Date();  
                    Compra e ;
                    Compra_detalle cd =new Compra_detalle();
                    String accion=request.getParameter("accion");
                    switch (accion) {
                        case "Agregar":
                            e = new Compra(0,Integer.parseInt(request.getParameter("txt_no_orden")),Integer.parseInt(request.getParameter("drop_proveedor")),request.getParameter("txt_fecha_orden"),formatter.format(date));
                            if (e.agregar()==1){
                                int contadordeinserts=0;
                                idcompra=e.getIdCompra();
                                for (int i=0;i<Integer.parseInt(request.getParameter("noproductos"));i++){
                                    idproducto=Integer.parseInt(request.getParameter("idProducto"+i));
                                    cantidad=Integer.parseInt(request.getParameter("cantidad"+i));
                                    precio=Double.parseDouble(request.getParameter("precio"+i));
                                    cd=new Compra_detalle(0,idcompra,idproducto,cantidad,precio);
                                    if(cd.agregar()>0){
                                        contadordeinserts++;
                                    }
                                }
                                out.println("<h1>Ingreso exitoso</h1>");
                                out.println("<p>Se agrego la compra con un total de "+contadordeinserts+" productos.</p>");
                            }else{
                                out.println("<h1>No se pudo ingresar el registro.</h1>");
                            }   out.println("<a href ='Compras.jsp'>Regresar</a>");
                            out.println("</body>");
                            out.println("</html>");
                            break;
                        case "Modificar":
                            idcompra=Integer.parseInt(request.getParameter("idCompra"));
                            //int idCompra, int no_orden_compra, int idProveedor, String fecha_orden, String fechaingreso
                            e=new Compra(idcompra,Integer.parseInt(request.getParameter("txt_no_orden"+String.valueOf(idcompra))),Integer.parseInt(request.getParameter("drop_proveedor"+String.valueOf(idcompra))),request.getParameter("txt_fecha_orden"+String.valueOf(idcompra)),formatter.format(date));
                            if (e.modificar()==1){
                                int contadordeinserts=0;
                                for (int i:cd.idDetallePorIdCompra(idcompra)){
                                    idproducto=Integer.parseInt(request.getParameter("drop_producto"+String.valueOf(i)));
                                    cantidad=Integer.parseInt(request.getParameter("cantidad"+String.valueOf(i)));
                                    precio=Double.parseDouble(request.getParameter("precio"+String.valueOf(i)));
                                    cd=new Compra_detalle(i,idcompra,idproducto,cantidad,precio);
                                    if(cd.modificar()>0){
                                        contadordeinserts++;
                                    }
                                }
                                out.println("<h1>Registro modificado.</h1>");
                            }else{
                                out.println("<h1>No se pudo modificar el registro.</h1>");
                            }   out.println("<a href ='Compras_Detalles.jsp'>Regresar</a>");
                            out.println("</body>");
                            out.println("</html>");
                            break;
                        case "Eliminar":
                            e = new Compra();
                            e.setIdCompra(Integer.parseInt(request.getParameter("idCompra")));
                            cd.setIdCompra(Integer.parseInt(request.getParameter("idCompra")));
                            if (cd.eliminar()){
                                if(e.eliminar()){
                                    out.println("<h1>Registro eliminado.</h1>");
                                }else{
                                    out.println("<h1>No se pudo eliminar el registro.</h1>");
                                }
                            }else{
                                out.println("<h1>No se pudo eliminar el registro.</h1>");
                            }   out.println("<a href ='Compras_Detalles.jsp'>Regresar</a>");
                            out.println("</body>");
                            out.println("</html>");
                            break;
                        case "Proveedores":
                            response.sendRedirect("Proveedores.jsp");
                        default:
                            break;
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
