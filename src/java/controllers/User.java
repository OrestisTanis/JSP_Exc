package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserImpl;

/**
 *
 * @author Walter
 */
public class User extends HttpServlet {
    UserImpl userService;
    
    public User(){
        userService = new UserImpl();
    }
    
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
        String pathInfo = request.getPathInfo();

        switch (pathInfo) {
            case "/insert":
                insert(request, response);
                break;
            case "/delete":
                delete(request, response);
                break;
            case "/update":
                update(request, response);
                break;
            case "/find":
                find(request, response);
                break;
            case "/findAll":
                findAll(request, response);
                break;
            default:
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

    private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        entities.User user = new entities.User(firstName, lastName, tel, email);
        boolean saved = userService.save(user);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update user</title>");
            out.println("</head>");
            out.println("<body>");
            if (saved){
                out.println("<h1> Successfully saved user" + user + "</h1>");
            } 
            else {
               out.println("<h1> User " + user + " could not be saved. </h1>"); 
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean deleted = userService.deleteById(id);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update user</title>");
            out.println("</head>");
            out.println("<body>");
            if (deleted){
                out.println("<h1> Successfully deleted user with id: " + id + "</h1>");
            } 
            else {
               out.println("<h1> The requested deletion could not be performed. </h1>"); 
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        entities.User user = new entities.User(firstName, lastName, tel, email);
        boolean updatedUser = userService.updateById(id, user);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Update user</title>");
            out.println("</head>");
            out.println("<body>");
            if (updatedUser){
                out.println("<h1> Updated " + user + "</h1>");
            } 
            else {
               out.println("<h1> The requested update could not be performed. </h1>"); 
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        entities.User user = userService.findById(id);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Find User</title>");
            out.println("</head>");
            out.println("<body>");
            if (user != null){
                out.println("<h1>" + user + "</h1>");
            }
            else {
                out.println("<h1> User with id " + id + " was not found. </h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<entities.User> users = userService.findAll();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Users</title>");
            out.println("</head>");
            out.println("<body>");
             out.println("<h1> List of Users </h1>");
            for (entities.User user: users){
                out.println("<p>" + user + "</p>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
}
