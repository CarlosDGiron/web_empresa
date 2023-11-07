/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import modelo.Producto;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author cana0
 */
public class sr_reportes extends HttpServlet {

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
                   
                    if("Productos".equals(request.getParameter("btn_productos"))){
                        reporteProductos();
                    }else if("Modificar".equals(request.getParameter("btn_modificar"))){
                        
                    }else if("Eliminar".equals(request.getParameter("btn_eliminar"))){
                        
                    }            
                    out.println("<a href ='Reportes.jsp'>Regresar</a>");
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
    
    protected void reporteProductos(){
        Producto producto=new Producto();
        producto.jsonReporte();
        /* try {
            // Cargar la plantilla JRXML
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\cana0\\JaspersoftWorkspace\\MyReports\\Reporte_Productos.jasper");

            // Crear un JsonObject con tus datos
            String json = producto.jsonReporte();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("json", json);

            // Crear un JsonDataSource
            ByteArrayInputStream jsonData = new ByteArrayInputStream(json.getBytes("UTF-8"));
            JsonDataSource dataSource = new JsonDataSource(jsonData, "data");

            // Generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

            // Mostrar el informe en una ventana de visualización
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
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
