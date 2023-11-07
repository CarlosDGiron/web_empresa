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
import modelo.Proveedor;
import modelo.Usuario;

/**
 *
 * @author cana0
 */
public class sr_proveedor extends HttpServlet {

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
            int idUsuario=0;
            if(request.getSession().getAttribute("idUsuario")!=null){
                idUsuario=(int)request.getSession().getAttribute("idUsuario");
            }
            if(idUsuario!=0){
                Usuario u=new Usuario();
                if(u.tienePermisoId(idUsuario, 1)){
                    //Permisos adecuados
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
                    Date date = new Date();  
                    System.out.println(formatter.format(date));  
                    Proveedor e = new Proveedor(Integer.parseInt(request.getParameter("txt_id")),request.getParameter("txt_proveedor"),request.getParameter("txt_nit"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"));
                    if("Agregar".equals(request.getParameter("btn_agregar"))){
                        if (e.agregar()==1){
                        out.println("<h1>Ingreso exitoso.</h1>");
                        }else{
                        out.println("<h1>No se pudo ingresar el registro.</h1>");
                        }
                    }else if("Modificar".equals(request.getParameter("btn_modificar"))){
                        if (e.modificar()==1){
                        out.println("<h1>Registro modificado.</h1>");
                        }else{
                        out.println("<h1>No se pudo modificar el registro.</h1>");
                        }                
                    }else if("Eliminar".equals(request.getParameter("btn_eliminar"))){
                        if (e.eliminar()==1){
                        out.println("<h1>Registro eliminado.</h1>");
                        }else{
                        out.println("<h1>No se pudo eliminar el registro.</h1>");
                        }               
                    }            
                    out.println("<a href ='Proveedores.jsp'>Regresar</a>");
                    out.println("</body>");
                    out.println("</html>");
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
