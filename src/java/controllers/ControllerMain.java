package controllers;

import entity.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import process.BeanConnexionClient;
import process.GestionCatalogue;
import process.beanPanier;

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

        HttpSession session = request.getSession();
        String section = request.getParameter("section");
        String action = request.getParameter("action");
        String page = "/WEB-INF/home.jsp";
        /////// DO NOT MODIFY ABOVE THIS LINE ///////
        //------------------------------------------------------------------------------------//
        //                                      HOME                                          //
        //------------------------------------------------------------------------------------//
        if ("carousel-event".equals(section)) {
            page = "/WEB-INF/includes/carouselEvent.jsp";
        }
        //------------------------------------------------------------------------------------//
        //                               CONTROLEUR NAVIGATOR                                 //
        //------------------------------------------------------------------------------------//
        //                                 Bouton Home                                        //
        if ("home".equals(section)) {
            page = "/WEB-INF/home.jsp";
        }
        //                                 Bouton Book                                        //    
        if ("book".equals(section)) {
            page = "/WEB-INF/book.jsp";
        }

        //                                 Bouton Catalogue                                    //
        if ("catalog".equals(section)) {
            page = "/WEB-INF/catalog.jsp";
        }

        //                                 Bouton monCompte                                    //
        if ("monCompte".equals(section)) {
            page = "/WEB-INF/jspCustomerAccount.jsp";
        }
        //--------------------------------------------------------------------------------------//
        //                                      Gestion des pages compte client                 //
        //--------------------------------------------------------------------------------------//
        if ("createAccount".equals(section)) {
            page = "/WEB-INF/jspCreateAccount.jsp";
        }

        //--------------------------------------------------------------------------------------//
        //                                      Gestion du nav   catalogue                      //
        //--------------------------------------------------------------------------------------//
        if (getServletContext().getAttribute("gestionCatalogue") == null) {

            try {
                getServletContext().setAttribute("gestionCatalogue", new GestionCatalogue());
            } catch (NamingException ex) {
                System.out.println("erreur gestionCatalogue");
                ex.printStackTrace();
            }
        }

        if ("listCatalog".equals(section)) {

            page = "/WEB-INF/includes/menuCatalog.jsp";

        }
        //--------------------------------------------------------------------------------------//
        //                                      Gestion des pages catalogue                      //
        //--------------------------------------------------------------------------------------//
        GestionCatalogue gtCatalog = (GestionCatalogue) getServletContext().getAttribute("gestionCatalogue");

        if ("pageCatalog".equals(section)) {

            try {

                HashMap<String, List<Book>> listeBook = gtCatalog.findBook();
                List<String> keys = gtCatalog.getKeys();
                request.setAttribute("keys", keys);
                request.setAttribute("listeBook", listeBook);

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //--------------------------------------------------------------------------------------//
        //                                      Gestion des pages compte client                 //
        //--------------------------------------------------------------------------------------//
        //--------------------------------------------------------------------------------------//
        //                                      Gestion des pages compte client                 //
        //--------------------------------------------------------------------------------------//
        if ("createAccount".equals(section)) {
            page = "/WEB-INF/jspCreateAccount.jsp";

        }

        if (request.getParameter("connect") != null) {
            session.setAttribute("BeanConnexionClient", new BeanConnexionClient());
            BeanConnexionClient bLogin
                    = (BeanConnexionClient) session.getAttribute("BeanConnexionClient");
            boolean check = bLogin.checkLogin(request.getParameter("adresseMail"), request.getParameter("motDePasse"));
            if (check) {
                page = "/WEB-INF/jspMainAccount.jsp";
            } else {
                if ((request.getParameter("adresseMail").isEmpty()) || request.getParameter("motDePasse").isEmpty()) {

                }
            }
        }
        if ("valider".equals(section)) {
            page = "/WEB-INF/jspMainAccount.jsp";
        }
        if ("updateAccountCustomer".equals(section)) {
            page = "/WEB-INF/jspProfilCustomer.jsp";
        }
        if ("updateAddressCustomer".equals(section)) {
            page = "/WEB-INF/jspAddressCustomer.jsp";
        }
        if ("orderCustomer".equals(section)) {
            page = "/WEB-INF/jspOrderCustomer.jsp";
        }
        if ("return".equals(section)) {
            page = "/WEB-INF/jspMainAccount.jsp";
        }
        //--------------------------------------------------------------------------------------//
        //                                      Gestion de l'ajout au catalogue                 //
        //--------------------------------------------------------------------------------------//
        if (request.getParameter("action") != null) {
            beanPanier bPanier = (beanPanier) session.getAttribute("panier");
            if (bPanier == null) {
                bPanier = new beanPanier();
                session.setAttribute("panier", bPanier);
            }
            String item = request.getParameter("item");
            String actionType = request.getParameter("action");
           if (bPanier == null) {
                bPanier = new beanPanier();
                session.setAttribute("panier", bPanier);
            }
            if ("add".equals(actionType)) {
                bPanier.add(item);
            }
            if ("dec".equals(actionType)) {
                bPanier.dec(item);
            }
            if ("del".equals(actionType)) {
                bPanier.del(item);
            }
            if ("clear".equals(actionType)) {
                bPanier.clear();
            }
            
            request.setAttribute("cartitems", bPanier.getSize());

            if (request.getParameter("callback") != null) {
                page = "/WEB-INF/" + request.getParameter("callback") + ".jsp";
                //page = "/WEB-INF/catalog.jsp";
          }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        /////// DO NOT MODIFY BELOW THIS LINE ///////
        page = response.encodeURL(page);
        getServletContext().getRequestDispatcher(page).include(request, response);

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
