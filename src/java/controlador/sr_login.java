/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author cana0
 */
@WebServlet(name = "sr_login", urlPatterns = {"/sr_login"})
public class sr_login extends HttpServlet {

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
        String accion = request.getParameter("accion");
        switch (accion) {
            case "Login":
                HttpSession hsesion = request.getSession();
                Usuario u = new Usuario();
                String usuario = request.getParameter("txt_usuario");
                String pass = request.getParameter("txt_password");
                if (u.esValido(usuario, pass)) {
                    u.setUsuario(usuario);
                    hsesion.setAttribute("nombre", u.getNombre(usuario));
                    System.out.println(hsesion.getAttribute("nombre"));
                    u.cargarIds();
                    hsesion.setAttribute("idUsuario", u.getIdUsuario());
                    hsesion.setAttribute("usuario", u.getUsuario());
                    hsesion.setAttribute("idEmpleado", u.getIdEmpleado());
                    response.sendRedirect("Principal.jsp");
                } else {
                    response.sendRedirect("index.jsp?login=error");
                }
                System.out.println("Login");
                break;
            case "Logout":
                request.getSession().invalidate();
                response.sendRedirect("index.jsp?login=logout");
                System.out.println("Logout");
                break;
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
