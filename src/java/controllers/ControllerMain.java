package controllers;

import com.sun.media.sound.InvalidFormatException;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerMain", urlPatterns = {"/ControllerMain"})
public class ControllerMain extends HttpServlet {

    private Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    // ok renvoit bien la home
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ServletContext application = this.getServletContext();
        HttpSession session = request.getSession();
        String pageId = request.getParameter("page");
        String actionId = request.getParameter("action");

        /////// DO NOT MODIFY ABOVE THIS LINE ///////
        if (pageId != null) {
            loadPage(request, response, pageId);
        } else if (actionId != null) {
            performAction(actionId);
            String pageToCallBackId = request.getParameter("callBack");
            if(pageToCallBackId != null){
                loadPage(request, response, pageToCallBackId);
            }
        } else {
            throw new ServletException("Can't specify action and page parameter at the same time.");
        }

    }

    private void loadPage(HttpServletRequest request, HttpServletResponse response, String pageId) throws ServletException, IOException {
        String pageUrl = "/WEB-INF/home.jsp";
        if (page != null) {
            switch (page) {
                case "home":
                    pageUrl = "/WEB-INF/home.jsp";
                    break;

                case "carousel-event":
                    pageUrl = "/WEB-INF/includes/carouselEvent.jsp";
                    break;

                case "catalog":
                    pageUrl = "/WEB-INF/catalog.jsp";
                    break;

                case "cart":
                    pageUrl = "/WEB-INF/cart.jsp";
                    break;
                default:
                    // TODO: FOR TEST ONLY. REMOVE FOR PRODUCTION
                    pageUrl = "/WEB-INF/" + page + ".jsp";
                    break;
            }
        }
        System.out.println("--------->>> page : " + pageUrl); // DEBUG : recursive calling if displayed twice
        pageUrl = response.encodeURL(pageUrl);
        getServletContext().getRequestDispatcher(pageUrl).include(request, response);
    }

    private void performAction(String actionId) {

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
