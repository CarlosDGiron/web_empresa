/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;

/**
 *
 * @author cana0
 */
public class sr_empleado extends HttpServlet {

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
            out.println("<title>Servlet sr_empleado</title>");            
            out.println("</head>");
            out.println("<body>");
            Empleado e=new Empleado(request.getParameter("txt_codigo"),Integer.parseInt(request.getParameter("drop_puesto")),Integer.parseInt(request.getParameter("txt_id")),request.getParameter("txt_nombres"),request.getParameter("txt_apellidos"),request.getParameter("txt_direccion"),request.getParameter("txt_telefono"),request.getParameter("txt_nacimiento"));
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
            out.println("<a href ='index.jsp'>Regresar</a>");
            out.println("</body>");
            out.println("</html>");
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
