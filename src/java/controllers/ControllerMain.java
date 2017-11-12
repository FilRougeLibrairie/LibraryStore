package controllers;

import entity.Address;
import entity.Book;
import entity.Customer;
import entity.Review;
import entity.ShippingCost;
import entity.SubTheme;
import entity.Theme;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import process.AddressCreationException;
import process.BeanConnexionClient;
import process.GestionAuthor;
import process.GestionCatalogue;
import process.GestionEvent;
import process.GestionLibrary;
import process.GestionLivre;
import process.GestionSearch;
import process.OutOfStockException;
import process.UserInputException;
import process.beanAddress;
import process.beanBank;
import process.beanOrder;
import process.beanPanier;
import process.beanReview;
import process.beanShipping;

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
            throws ServletException, IOException, SQLException, NamingException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String section = request.getParameter("section");
        String page = "/WEB-INF/home.jsp";
        //// DEBUT GUILLAUME
        ServletContext application = this.getServletContext();
        String action = request.getParameter("action");
        String item = request.getParameter("item");
        Float total = 6f;

        if (application.getAttribute("pagination") == null) {
            try {

            } catch (Exception e) {
            }
        }
        //// FIN GUILLAUME

        if (getServletContext().getAttribute("gestionLibrary") == null) {

            try {
                getServletContext().setAttribute("gestionLibrary", new GestionLibrary());
            } catch (NamingException ex) {
                System.out.println("erreur gestionLibrary");
                ex.printStackTrace();
            }
        }

        GestionLibrary gestionLibrary = (GestionLibrary) getServletContext().getAttribute("gestionLibrary");
        Collection listeLibrary;

        try {
            listeLibrary = gestionLibrary.findLibrary();
            request.setAttribute("listeLibrary", listeLibrary);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        page = "/WEB-INF/home.jsp";

        /////// DO NOT MODIFY ABOVE THIS LINE ///////
        //--------------------------------------------------------------------------------------//
        //   DEBUT GUILLAUME            AJOUT AU PANIER -> COMMANDE                             //
        //--------------------------------------------------------------------------------------//
        beanPanier bPanier = (beanPanier) session.getAttribute("beanPanier");
        if (bPanier == null) {
            bPanier = new beanPanier();
            session.setAttribute("beanPanier", bPanier);
        }
        if (action != null) {
            if (item != null) {
                bPanier = (beanPanier) session.getAttribute("beanPanier");
                if (bPanier == null) {
                    bPanier = new beanPanier();
                    session.setAttribute("beanPanier", bPanier);
                }
                if (action.equals("add")) {
                    bPanier.add(item);
                }
                if (action.equals("dec")) {
                    bPanier.dec(item);
                }
                if (action.equals("del")) {
                    bPanier.del(item);
                }
                if (action.equals("clear")) {
                    bPanier.clear();
                }
                session.setAttribute("itemscount", bPanier.getSize());
                session.setAttribute("totalItemsCount", bPanier.getTotalItemsCount());
            }
        }

        if ("displaycart".equals(section)) {
            page = "/WEB-INF/cart.jsp";
            bPanier = (beanPanier) session.getAttribute("beanPanier");
            if (bPanier == null) {
                bPanier = new beanPanier();
                session.setAttribute("beanPanier", bPanier);
            }
            beanShipping bShip = (beanShipping) application.getAttribute("beanShip");
            if (bShip == null) {
                bShip = new beanShipping();
                application.setAttribute("beanShip", bShip);
            }
            application.setAttribute("shipList", bShip.getList());
            session.setAttribute("itemscount", bPanier.getSize());

            /////// TODO: à plutôt prendre depuis le bean ?
            session.setAttribute("panierVide", bPanier.isEmpty());
            session.setAttribute("panier", bPanier.list());
            session.setAttribute("totalItemsCount", bPanier.getTotalItemsCount());
            session.setAttribute("totalTTC", bPanier.getTotalTTC());
            session.setAttribute("totalHT", bPanier.getTotalHT());
            session.setAttribute("totalTVA", bPanier.getTotalVAT());
        }

        if ("checkout".equals(section)) {

            if (session.getAttribute("c") == null) {
                Cookie c = new Cookie("section", "checkout");
                response.addCookie(c);
                response.sendRedirect("ControllerMain?section=monCompte");
            } else if (session.getAttribute("beanPanier") == null
                    || ((Collection) session.getAttribute("panier")).isEmpty()) {
                Cookie c = new Cookie("section", "");
                c.setMaxAge(0);
                response.addCookie(c);
                response.sendRedirect("ControllerMain?section=displaycart");
            } else {
                Cookie c = new Cookie("section", "");
                c.setMaxAge(0);
                response.addCookie(c);
                page = "/WEB-INF/checkout.jsp";

                bPanier = (beanPanier) session.getAttribute("beanPanier");
                beanOrder bOrder = (beanOrder) session.getAttribute("beanOrder");
                if (bOrder == null) {
                    bOrder = new beanOrder(bPanier.list(), (Customer) session.getAttribute("c"));
                    session.setAttribute("beanOrder", bOrder);
                }
                if (request.getParameter("step") == null || "shipcost".equals(request.getParameter("step"))) {
                    request.setAttribute("step", "shipcost");

                    if (request.getParameter("shipcostvalidate") != null) {
                        try {
                            int shippingCostId = Integer.valueOf(request.getParameter("shippingchoice"));
                            beanShipping bShip = (beanShipping) application.getAttribute("beanShip");
                            ShippingCost shipCost = bShip.getShipTypeById(shippingCostId);
                            bPanier.setShippingCost(shipCost.getShipCost());
                            session.setAttribute("totalTTCWithShipCost", bPanier.getTotalWithShipCost());
                            session.setAttribute("shippingchoiceid", shippingCostId);
                            session.setAttribute("shippingchoice", shipCost);

                            session.setAttribute("addressList", bOrder.getAddressList());

                            request.setAttribute("step", "address");
                        } catch (Exception ex) {
                            System.out.println("Erreur de type avec shippingCostId");
                        }
                    }

                    if (request.getParameter("submitnewadd") != null) {
                        beanAddress bAddress = (beanAddress) session.getAttribute("beanAddress");
                        if (bAddress == null) {
                            bAddress = new beanAddress();
                            session.setAttribute("beanAddress", bAddress);
                        }
                        try {
                            bAddress.createAddress(
                                    (Customer) session.getAttribute("c"),
                                    request.getParameter("label").trim(),
                                    request.getParameter("firstname").trim(),
                                    request.getParameter("lastname").trim(),
                                    request.getParameter("company").trim(),
                                    request.getParameter("streetnumber").trim(),
                                    request.getParameter("streettype").trim(),
                                    request.getParameter("streetname").trim(),
                                    request.getParameter("streetcomplement").trim(),
                                    request.getParameter("zipcode").trim(),
                                    request.getParameter("city").trim(),
                                    request.getParameter("securitycode").trim(),
                                    request.getParameter("phone").trim()
                            );
                        } catch (AddressCreationException ex) {
                            request.setAttribute("addresserror", ex.getMessage());
                            request.setAttribute("step", "address");
                        }
                        session.setAttribute("addressList", bOrder.getAddressList());
                        request.setAttribute("step", "address");
                    }

                    if (request.getParameter("addressvalidate") != null) {
                        if (request.getParameter("invoiceadd") != null && request.getParameter("shipadd") != null) {
                            int invoiceAddressId = Integer.valueOf(request.getParameter("invoiceadd"));
                            int shippingAddressId = Integer.valueOf(request.getParameter("shipadd"));
                            bOrder.setInvoiceAdressId(invoiceAddressId);
                            bOrder.setShippingAdressId(shippingAddressId);
                            session.setAttribute("invoiceaddid", invoiceAddressId);
                            session.setAttribute("shipaddid", shippingAddressId);
                            request.setAttribute("step", "payment");
                        } else {
                            request.setAttribute("error", "Veuillez choisir une adresse de facturation et de livraison");
                            session.setAttribute("invoiceaddid", request.getParameter("invoiceadd"));
                            session.setAttribute("shipaddid", request.getParameter("shipadd"));
                            request.setAttribute("step", "address");
                        }
                    }

                    if (request.getParameter("gotobank") != null) {
                        if (request.getParameter("cgu") == null) {
                            request.setAttribute("error", "Veuillez accepter les conditions d'utilisation");
                            request.setAttribute("step", "gotobank");

                        } else {
                            int invoiceAddressId = (Integer) session.getAttribute("invoiceaddid");
                            int shippingAddressId = (Integer) session.getAttribute("shipaddid");
                            int shippingCostId = (Integer) session.getAttribute("shippingchoiceid");
                            String ipaddress = (String) request.getParameter("ip");
                            Customer customer = (Customer) session.getAttribute("c");

                            int purchaseId = bOrder.createNewOrder(customer.getCusID(), shippingAddressId, invoiceAddressId, shippingCostId, ipaddress);
                            try {
                                bOrder.checkStocks(purchaseId);
                                bOrder.setOrderStatus(purchaseId, bOrder.ORDER_STATUS_PROCESS);
                                session.setAttribute("purchaseid", purchaseId);
                                request.setAttribute("totalTTC", session.getAttribute("totalTTCWithShipCost"));
                                request.setAttribute("bank", "payment");
                                page = "/WEB-INF/bank.jsp";
                            } catch (OutOfStockException ex) {
                                request.setAttribute("step", "outofstock");
                            }
                        }
                    }

                    if (request.getParameter("paymentvalidate") != null) {
                        page = "/WEB-INF/bank.jsp";
                        beanBank bBank = new beanBank();
                        try {
                            bBank.setNumeroCB(request.getParameter("numerocb"));
                            bBank.setMonth(request.getParameter("month"));
                            bBank.setYear(request.getParameter("year"));
                            bBank.setCvv(request.getParameter("cvv"));
                            request.setAttribute("bank", "validation");
                            request.setAttribute("amount", request.getParameter("totalTTC"));
                        } catch (UserInputException ex) {
                            request.setAttribute("inputerror", ex.getMessage());
                            request.setAttribute("bank", "payment");
                        }
                    }

                    if (request.getParameter("bankcallback") != null) {
                        page = "/WEB-INF/checkout.jsp";
                        if ("validate".equals(request.getParameter("bankcallback"))) {
                            try {
                                int purchaseId = (int) session.getAttribute("purchaseid");
                                bOrder.saveCart(purchaseId);
                                bOrder.setOrderStatus(purchaseId, bOrder.ORDER_STATUS_PAYED);
                                bOrder.setPaymentStatus(purchaseId, bOrder.PAYMENT_TYPE_CB);
                                request.setAttribute("orderid", purchaseId);
                                request.setAttribute("step", "recap");
                                session.setAttribute("panier", null);
                                session.setAttribute("beanPanier", null);
                                session.setAttribute("beanOrder", null);
                                session.setAttribute("panierVide", true);
                                session.setAttribute("panier", null);
                                session.setAttribute("totalItemsCount", null);
                                session.setAttribute("totalTTC", null);
                                session.setAttribute("totalHT", null);
                                session.setAttribute("totalTVA", null);
                                session.setAttribute("shippingchoice", null);
                            } catch (OutOfStockException ex) {
                                request.setAttribute("step", "outofstock");
                            }
                        }
                        if ("rejected".equals(request.getParameter("bankcallback"))) {
                            request.setAttribute("step", "payfail");
                        }
                    }
                }
            }

        }

        //// FIN GUILLAUME
        //------------------------------------------------------------------------------------//
        //                                      HOME                                //
        //------------------------------------------------------------------------------------//
        GestionCatalogue gtCatalog = (GestionCatalogue) getServletContext().getAttribute("gestionCatalogue");
        if ("carousel-event".equals(section)) {

            try {
                Collection listeBook = gtCatalog.findAllNewthreeBook();

                request.setAttribute("listeBook", listeBook);
                page = "/WEB-INF/includes/carouselEvent.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        if ("listCatalog".equals(section)) {
            Collection listeBook;
            try {
                listeBook = gtCatalog.findAllNewthreeBook();
                request.setAttribute("listeBook", listeBook);
                page = "/WEB-INF/menuCatalog.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //                                 Bouton monCompte                                    //
        if ("monCompte".equals(section)) {
            if (request.getAttribute("c") != null) {
                page = "/WEB-INF/jspMainAccount.jsp";
            }
            page = "/WEB-INF/jspCustomerAccount.jsp";
        }

        //                                 Bouton offer                                        //
        //review
        if (getServletContext().getAttribute("gestionEvent") == null) {

            try {
                getServletContext().setAttribute("gestionEvent", new GestionEvent());
            } catch (NamingException ex) {
                System.out.println("erreur gestionEvent");
                ex.printStackTrace();
            }
        }

        GestionEvent gestionEvent = (GestionEvent) getServletContext().getAttribute("gestionEvent");

        if ("offer".equals(section)) {

            try {
                Collection listeOffer = gestionEvent.findEvent();
                request.setAttribute("listeOffer", listeOffer);
                page = "/WEB-INF/offer.jsp";
                request.getAttribute(page);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //                                 Bouton author                                        // 
        if (getServletContext().getAttribute("gestionAuthor") == null) {

            try {
                getServletContext().setAttribute("gestionAuthor", new GestionAuthor());
            } catch (NamingException ex) {
                System.out.println("erreur gestionAuthor");
                ex.printStackTrace();
            }
        }

        GestionAuthor gestionAuthor = (GestionAuthor) getServletContext().getAttribute("gestionAuthor");

        if ("author".equals(section)) {
            try {
                Collection listeKeys = gestionAuthor.getClefs();
                request.setAttribute("listeKeys", listeKeys);
                Collection listeAuthor = gestionAuthor.findAuthor();
                request.setAttribute("listeAuthor", listeAuthor);
                page = "/WEB-INF/author.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //--------------------------------------------------------------------------------------//
        //                              Gestion des pages author                             //
        //--------------------------------------------------------------------------------------//
        if ("authorAscii".equals(section)) {

            String ascii = request.getParameter("ascii");
            try {
                Collection listeAuthor = gestionAuthor.findAuthorByKeys(ascii);
                request.setAttribute("listeAuthor", listeAuthor);
                Collection listeKeys = gestionAuthor.getClefs();
                request.setAttribute("listeKeys", listeKeys);
                page = "/WEB-INF/author.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("authorBook".equals(section)) {

            int authorId = Integer.valueOf(request.getParameter("authorId"));
            try {
                Collection listeAuthor = gestionAuthor.findAuthor(authorId);
                request.setAttribute("listeAuthor", listeAuthor);

                Collection listeBook = gtCatalog.findByAuthor(authorId);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("authorId", authorId);

                Collection listeKeys = gestionAuthor.getClefs();
                request.setAttribute("listeKeys", listeKeys);
                page = "/WEB-INF/authorBook.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("authorInfo".equals(section)) {

            int authorId = Integer.valueOf(request.getParameter("authorId"));
            try {
                Collection listeAuthor = gestionAuthor.findAuthor(authorId);
                request.setAttribute("listeAuthor", listeAuthor);

                Collection listeKeys = gestionAuthor.getClefs();
                request.setAttribute("listeKeys", listeKeys);
                page = "/WEB-INF/authorInfo.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("authorOuvrage".equals(section)) {

            int authorId = Integer.valueOf(request.getParameter("authorId"));
            Collection listeBook = gtCatalog.findByAuthor(authorId);
            request.setAttribute("listeBook", listeBook);
            request.setAttribute("authorId", authorId);
            page = "/WEB-INF/authorBook.jsp";

        }

        //--------------------------------------------------------------------------------------//
        //                              Gestion des pages offer                             //
        //--------------------------------------------------------------------------------------//
        if ("offerbook".equals(section)) {

            try {

                int valeur = Integer.valueOf(request.getParameter("offid"));
                Collection listeOffer = gestionEvent.findEvent();
                request.setAttribute("listeOffer", listeOffer);
                Collection listeBook = gestionEvent.findAllBookByOffer(valeur);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("offid", valeur);
                page = "/WEB-INF/offerBook.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //--------------------------------------------------------------------------------------//
        //                              Gestion des pages catalogue                             //
        //--------------------------------------------------------------------------------------//
        if (getServletContext().getAttribute("gestionCatalogue") == null) {

            try {
                getServletContext().setAttribute("gestionCatalogue", new GestionCatalogue());
            } catch (NamingException ex) {
                System.out.println("erreur gestionCatalogue");
                ex.printStackTrace();
            }
        }

        if ("pageCatalog".equals(section)) {

            try {

                Collection listeBookSize = gtCatalog.findBook();
                Collection nbrePages = new ArrayList();

                // fction pagination
                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                System.out.println(listeBookSize.size());
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;

                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPage(totalf, numero);

                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("pageNewBook".equals(section)) {

            try {

                Collection listeBookSize = gtCatalog.findBookNewBook();
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);
                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookNewBookByPage(totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("pagePriceInf".equals(section)) {

            Float min = 0f;
            Float max = 9.99f;

            try {
                Collection listeBookSize = gtCatalog.findBookPrice(min, max);
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPriceByBook(min, max, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("pagePriceInf2".equals(section)) {

            Float min = 10f;
            Float max = 19.99f;

            try {

                Collection listeBookSize = gtCatalog.findBookPrice(min, max);
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPriceByBook(min, max, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("pagePriceInf3".equals(section)) {

            Float min = 20f;
            Float max = 50000f;

            try {

                Collection listeBookSize = gtCatalog.findBookPrice(min, max);
                Collection nbrePages = new ArrayList();
                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPriceByBook(min, max, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("bookGeneral".equals(section)) {

            String id = null;
            try {
                if (request.getParameter("booId") == null) {
                    if (request.getParameter("item") != null) {
                        id = request.getParameter("item");
                    }
                } else {
                    id = request.getParameter("booId");
                }
                Collection book = gtCatalog.findBook(id);
                request.setAttribute("book", book);
                Collection review = gtCatalog.findReview(id);
                request.setAttribute("review", review);
                Collection authors = gtCatalog.findAuthors(id);
                request.setAttribute("authors", authors);

                if (request.getParameter("modifyReview") != null) {
                    beanReview br = new beanReview();
                    Customer cus = (Customer) session.getAttribute("c");
                    Review customerReview = br.getReview(cus.getCusID(), id);
                    request.setAttribute("customerReview", customerReview);
                    request.setAttribute("displayComment", ((Customer) session.getAttribute("c")).getCusID());
                }

                page = "/WEB-INF/bookGeneral.jsp";
            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

            /////////// CHAMPS TEXTE CREATION COMMENTAIRES - DEBUT GUILLAUME //////////////
            beanReview br = new beanReview();
            if (request.getParameter("submitreview") != null) {
                Customer customer = (Customer) session.getAttribute("c");
                if (br.isBookPayed(customer.getCusID(), id) && !br.isBookedReviewed(customer.getCusID(), id)) {
                    br.saveReview(request.getParameter("revnote"), request.getParameter("revcomment"), br.REVIEW_STATUS_PENDING, request.getParameter("ip"));
                } else {
                    System.out.println("PAS POSSIBLE DE COMMENTER GALOPIN !");
                }
                request.setAttribute("section", section);
                request.setAttribute("booId", section);
                request.setAttribute("sendrev", id);

            }

            if (request.getParameter("updatereview") != null) {
                Customer customer = (Customer) session.getAttribute("c");
                if (br.isBookedReviewed(customer.getCusID(), id)) {
                    br.updateReview(customer.getCusID(), id, request.getParameter("revnote"), request.getParameter("revcomment"), br.REVIEW_STATUS_PENDING, request.getParameter("ip"));
                } else {
                    System.out.println("PAS POSSIBLE DE COMMENTER GALOPIN !");
                }
                request.setAttribute("section", section);
                request.setAttribute("booId", section);
                request.setAttribute("sendrev", id);
            }

            if (session.getAttribute("c") != null && id != null) {
                Customer customer = (Customer) session.getAttribute("c");
                if (br.isBookPayed(customer.getCusID(), id) && !br.isBookedReviewed(customer.getCusID(), id)) {
                    request.setAttribute("displayComment", customer.getCusID());
                }
            }

            //////////////////// FIN GUILLAUME
        }

        //review
        if ("pageReview0".equals(section)) {

            try {

                int review = 0;

                Collection listeBookSize = gtCatalog.findBookReview0(review);
                Collection nbrePages = new ArrayList();
                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPageReviewPagi(review, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if ("pageReview1".equals(section)) {

            try {

                int review = 1;

                Collection listeBookSize = gtCatalog.findBookReview0(review);
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPageReviewPagi(review, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if ("pageReview2".equals(section)) {

            try {

                int review = 2;

                Collection listeBookSize = gtCatalog.findBookReview0(review);
                Collection nbrePages = new ArrayList();
                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPageReviewPagi(review, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if ("pageReview3".equals(section)) {

            try {

                int review = 3;

                Collection listeBookSize = gtCatalog.findBookReview0(review);
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }
                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPageReviewPagi(review, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if ("pageReview4".equals(section)) {

            try {

                int review = 4;

                Collection listeBookSize = gtCatalog.findBookReview0(review);
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPageReviewPagi(review, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if ("pageReview5".equals(section)) {

            try {

                int review = 5;

                Collection listeBookSize = gtCatalog.findBookReview0(review);
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }

                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPageReviewPagi(review, totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        ////////////////////////////////////////////////////////////////////////NEW
        ////////////////////////////////////////////////////////////////////////NEW
        if ("new".equals(section)) {

            try {

                Collection listeBookSize = gtCatalog.findAllNewBookYear();
                Collection nbrePages = new ArrayList();

                int totalf = Math.round(total);
                int taillelist = listeBookSize.size();
                int nbrePage = 0;
                if (taillelist % totalf == 0) {
                    nbrePage = (int) (taillelist / total);
                } else {
                    nbrePage = (int) Math.ceil(taillelist / total);
                }
                for (int i = 1; i <= nbrePage; i++) {
                    nbrePages.add(i);
                }
                request.setAttribute("nbrePages", nbrePages);

                String spageid = request.getParameter("page");
                int pageid = Integer.parseInt(spageid);

                int numero = pageid;
                if (pageid == 1) {
                    numero = 0;
                } else {
                    numero = (pageid * 6) - 6;
                }

                Collection listeBook = gtCatalog.findBookPageAllNewBookYear(totalf, numero);
                request.setAttribute("listeBook", listeBook);
                request.setAttribute("page", request.getParameter("page"));

                page = "/WEB-INF/catalog.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //--------------------------------------------------------------------------------------//
        //                                      Gestion des pages Book                 //
        //--------------------------------------------------------------------------------------//
        if (getServletContext().getAttribute("gestionLivre") == null) {
            try {
                getServletContext().setAttribute("gestionLivre", new GestionLivre());
            } catch (NamingException ex) {
                System.out.println("erreur gestionLivre");
                ex.printStackTrace();
            }
        }

        GestionLivre gestionLivre = (GestionLivre) getServletContext().getAttribute("gestionLivre");

        if ("book".equals(section)) {

            try {
                Collection<Book> listeLivre = gestionLivre.pageTousLivres();
                List<Theme> listeThemes = gestionLivre.rechercheParTheme();
                request.setAttribute("listeLivre", listeLivre);
                request.setAttribute("listeThemes", listeThemes);

                if (request.getParameter("recherche") != null) {
                    int themeId = Integer.valueOf(request.getParameter("listeIdTheme"));
                    List<SubTheme> listeSousThemes = gestionLivre.rechercheParSousTheme(themeId);
                    request.setAttribute("listeSousThemes", listeSousThemes);
                    page = "/WEB-INF/book.jsp";
                }
                if (request.getParameter("affichage") != null) {
                    int subId = Integer.valueOf(request.getParameter("listeIdSousTheme"));
                    Collection listeBook = gtCatalog.findbyBySubTheme(subId);
                    request.setAttribute("listeBook", listeBook);
                    request.setAttribute("listeIdSousTheme", subId);
                    page = "/WEB-INF/catalog.jsp";
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        //--------------------------------------------------------------------------------------//
        //                                      Gestion des pages compte client                 //
        //--------------------------------------------------------------------------------------//
        BeanConnexionClient bLogin
                = (BeanConnexionClient) session.getAttribute("BeanConnexionClient");

        if ("createAccount".equals(section)) {
            page = "/WEB-INF/jspCreateAccount.jsp";
        }

        if ("deconnexion".equals(section)) {
            session.setAttribute("BeanConnexionClient", new BeanConnexionClient());
            session.setAttribute("c", null);
            page = "/WEB-INF/jspCustomerAccount.jsp";
        }

        if (request.getParameter("connect") != null) {
            if (bLogin == null) {
                bLogin = new BeanConnexionClient();
                session.setAttribute("BeanConnexionClient", new BeanConnexionClient());
            }

            boolean check = bLogin.checkCryptLogin(request.getParameter("adresseMail"), request.getParameter("motDePasse"));

            if (check) {
                bLogin.findCustomer(request.getParameter("adresseMail"), request.getParameter("motDePasse"));
                session.setAttribute("c", bLogin.getMyCus());
                Collection p = bLogin.findAdressCustomer(bLogin.getMyCus().getCusID());
                session.setAttribute("listLabel", p);

                if (getCookie(request.getCookies(), "section") != null) {
                    Cookie c = getCookie(request.getCookies(), "section");
                    if (c.getValue().equals("checkout")) {
                        response.sendRedirect("ControllerMain?section=checkout");
                    }
                } else {
                    page = "/WEB-INF/jspMainAccount.jsp";
                }
            } else {
                page = "/WEB-INF/jspCustomerAccount.jsp";
            }
        }

        if ("detailAdresse".equals(section)) {
            System.out.println("RECUP ID COMBOADRESS : " + request.getParameter("myAddress"));
            if (request.getParameter("myAddress") != null) {
                int addressId = Integer.valueOf(request.getParameter("myAddress"));
                Customer cus = (Customer) request.getAttribute("c");
                System.out.println("customer Address " + cus);
                Collection<Address> listAddress = bLogin.findAdressCustomer(cus.getCusID());
                System.out.println("DETAIL ADDRESS : " + bLogin.getMyCusAddress().getAddId());
                for (Address adr : listAddress) {
                    if (adr.getAddId() == addressId) {
                        session.setAttribute("p", bLogin.getMyCusAddress().getAddId());
                        break;
                    }
                }
            }
            page = "/WEB-INF/jspAddressCustomer.jsp";
        }

        if ("validerCreateCus".equals(section)) {
            bLogin = (BeanConnexionClient) session.getAttribute("BeanConnexionClient");
            Customer currentCus = (Customer) session.getAttribute("c");
            if (bLogin == null) {
                // recup des infos client de la JSP
                bLogin = new BeanConnexionClient();
                session.setAttribute("BeanConnexionClient", bLogin);
                String gender = null;
                String lastName = null;
                String firstName = null;
                String org = null;
                String adress = null;
                String phone = null;
                String dateN = null;
                String password = null;
                String confirmPassword = null;
                String ip = null;

                if (request.getParameter("searchGender") != null) {
                    gender = request.getParameter("searchGender");
                }

                if (request.getParameter("lastName") != null) {
                    lastName = request.getParameter("lastName");
                }
                if (request.getParameter("firstName") != null) {
                    firstName = request.getParameter("firstName");
                }
                if (request.getParameter("organisationName") != null) {
                    org = request.getParameter("organisationName");
                }
                if (request.getParameter("adresseMail") != null) {
                    adress = request.getParameter("adresseMail");
                }
                if (request.getParameter("phoneNumber") != null) {
                    phone = request.getParameter("phoneNumber");
                }
                if (request.getParameter("dateN") != null) {
                    dateN = request.getParameter("dateN");
                }
                if (request.getParameter("password") != null) {
                    password = request.getParameter("password");
                }
                if (request.getParameter("confirmPassword") != null) {
                    confirmPassword = request.getParameter("confirmPassword");
                }
                if (request.getParameter("ip") != null) {
                    ip = request.getParameter("ip");
                }

                Customer cus = bLogin.createCustomer(gender, lastName,
                        firstName, org, adress,
                        phone, dateN, password, confirmPassword, ip);

                if (cus != null) {
                    session.setAttribute("c", cus);
                    page = "/WEB-INF/jspMainAccount.jsp";
                } else {
                    request.setAttribute("errorCreate", "");
                    page = "/WEB-INF/jspCreateAccount.jsp";
                }

            }

        }

        if ("createAddress".equals(section)) {
            Customer currentCus = (Customer) session.getAttribute("c");
//            System.out.println("ON EST DANS LE CONTROLLEUR CREATE ADRESS");
//            System.out.println("CUS ID = " + currentCus.getCusID());
//            System.out.println("ADDRESS LABEL = "+request.getParameter("addLabel"));

            Address addCus = bLogin.createAddress(currentCus, request.getParameter("addLabel"), request.getParameter("addLastName"), request.getParameter("addFirstName"), request.getParameter("addCompany"), request.getParameter("addNumber"), request.getParameter("addStreetType"),
                    request.getParameter("addStreetName"), request.getParameter("addComplement"), request.getParameter("addZipCode"), request.getParameter("addCity"), request.getParameter("addSecurityCode"), request.getParameter("addPhone"));
//            System.out.println("APRES CREATION addCus BLogin");
//            System.out.println("CONTROLLER CREATE ADRESSE SAVE = " + addCus);

            if (addCus != null) {
                session.setAttribute("p", addCus);
                page = "/WEB-INF/jspMainAccount.jsp";
            } else {
                request.setAttribute("errorCreate", "");
                page = "/WEB-INF/jspCreateAccount.jsp";
            }
        }

        if ("updateAccountCustomer".equals(section)) {
            page = "/WEB-INF/jspProfilCustomer.jsp";
        }

        if ("updateCustomer".equals(section)) {
            // recup des infos client de la JSP
            Customer currentCus = (Customer) session.getAttribute("c");
            Customer cus = bLogin.updateCustomer(currentCus, request.getParameter("Civilité"), request.getParameter("lastName"),
                    request.getParameter("firstName"), request.getParameter("organisationName"), request.getParameter("adresseMail"),
                    request.getParameter("phoneNumber"), request.getParameter("dateN"), request.getParameter("password"), request.getParameter("ip"));

            if (cus != null) {
                session.setAttribute("c", cus);
                page = "/WEB-INF/jspMainAccount.jsp";
            } else {
                request.setAttribute("errorCreate", "");
                page = "/WEB-INF/jspProfilCustomer.jsp";
            }

        }

        if ("updateAddressCustomer".equals(section)) {

            int cusId = Integer.valueOf(request.getParameter("cusId"));
            Collection listLabel = bLogin.findAdressCustomer(cusId);
            request.setAttribute("listLabel", listLabel);
            page = "/WEB-INF/jspAddressCustomer.jsp";
        }

        if ("orderCustomer".equals(section)) {
            Customer currentCus = (Customer) session.getAttribute("c");
            
            page = "/WEB-INF/jspOrderCustomer.jsp";
        }

        if ("return".equals(section)) {
            page = "/WEB-INF/jspMainAccount.jsp";
        }
        if ("returnCusAccount".equals(section)) {
             page = "/WEB-INF/jspCustomerAccount.jsp";
        }
        //------------------------------------------------------------------------------------//
        //                               CONTROLEUR SEARCH                                    //
        //                                                                             //
        //                               CONTROLEUR SEARCH                                    //
        //------------------------------------------------------------------------------------//

        if (getServletContext().getAttribute("gestionSearch") == null) {

            getServletContext().setAttribute("gestionSearch", new GestionSearch());
        }

        GestionSearch gestionSearch = (GestionSearch) getServletContext().getAttribute("gestionSearch");

        if ("search".equals(section)) {
            String value = "";
            String type = "";
            value = request.getParameter("searchBarre");
            type = request.getParameter("searchType");
            System.out.println("typppppppppppppppppppppppp" + type);
            System.out.println("VALEURRRRRRRRRRRRRRRRRRRRRRRRRRRRRR" + value);

//////////  --> CA COINCE
//            if (request.getParameter("searchButton").equalsIgnoreCase("OK")) {
            if ("auteur".equals(type)) {

                Collection listSearchAuthor = gestionSearch.findAuthor(value);
                request.setAttribute("listSearchAuthor", listSearchAuthor);
                if (listSearchAuthor.size() <= 0) {
                    String notFound = "notFound";
                    request.setAttribute("notFound", notFound);
                }
            }

            if ("livre".equals(type)) {
                Collection listSearchBook = gestionSearch.findBook(value);
                request.setAttribute("listSearchBook", listSearchBook);
                if (listSearchBook.size() <= 0) {
                    String notFound = "notFound";
                    request.setAttribute("notFound", notFound);
                }
            }

            if ("isbn".equals(type)) {
                Collection listSearchBook = gestionSearch.findBookIsbn(value);
                request.setAttribute("listSearchBook", listSearchBook);
                if (listSearchBook.size() <= 0) {
                    String notFound = "notFound";
                    request.setAttribute("notFound", notFound);
                }
            }

            if ("mots".equals(type)) {

                Collection listSearchBook = gestionSearch.findByKW(value);
                request.setAttribute("listSearchBook", listSearchBook);
                if (listSearchBook.size() <= 0) {
                    String notFound = "notFound";
                    request.setAttribute("notFound", notFound);
                }
            }

//            }
            request.setAttribute("searchBarre", value);
            request.setAttribute("searchType", type);

            page = "/WEB-INF/jspSearch.jsp";
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////
        /////// DO NOT MODIFY BELOW THIS LINE ///////
        //// DEBUT GUILLAUME
        request.setAttribute("currentSection", section);
        //// FIN GUILLAUME
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ControllerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
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
