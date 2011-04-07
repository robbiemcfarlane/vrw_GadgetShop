/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vrw.ejb.entity.Basket;
import vrw.ejb.entity.BasketItem;
import vrw.ejb.entity.Item;
import vrw.ejb.entity.Offer;
import vrw.ejb.entity.OfferItem;
import vrw.ejb.entity.Order;
import vrw.ejb.session.ItemSessionRemote;
import vrw.ejb.session.ShoppingBasketSessionRemote;
import vrw.ejb.entity.StockException;
import vrw.ejb.session.OfferSessionRemote;
import vrw.ejb.session.OrderSessionRemote;

/**
 *
 * @author Robbie
 */
public class ShoppingBasket extends HttpServlet
{

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String servletPath = request.getServletPath();

        try
        {
            // View the basket
            if (servletPath.equals("/basket/view"))
            {
                view(request, response);
            }
            // Add an item to the basket
            else if (servletPath.equals("/basket/add"))
            {
                addItem(request, response);
            }
            // Checkout
            else if (servletPath.equals("/basket/checkout"))
            {
                checkout(request, response);
            }
        }
        catch (NamingException e)
        {
            throw new ServletException(e);
        }
    }

    protected ShoppingBasketSessionRemote getBasketSession(HttpServletRequest request)
            throws NamingException
    {
        HttpSession session = request.getSession();
        ShoppingBasketSessionRemote shoppingBasketSession = (ShoppingBasketSessionRemote) session.getAttribute("shoppingBasket");
        if (shoppingBasketSession == null)
        {
            InitialContext context = new InitialContext();
            shoppingBasketSession = (ShoppingBasketSessionRemote) context.lookup("vrw_GadgetShop/ShoppingBasketSession/remote");
            session.setAttribute("shoppingBasket", shoppingBasketSession);
        }

        return shoppingBasketSession;
    }

    protected void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        request.setAttribute("basketItems", getBasketSession(request).getItems().values());
        request.getRequestDispatcher("/basket/basket.jsp").forward(request, response);
    }

    protected void addItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        try
        {
            InitialContext context = new InitialContext();
            ItemSessionRemote itemSession = (ItemSessionRemote) context.lookup("vrw_GadgetShop/ItemSession/remote");
            ShoppingBasketSessionRemote basketSession = getBasketSession(request);

            String[] itemIds = request.getParameterValues("item-id[]");
            String[] quantities = request.getParameterValues("quantity[]");

            for (int i = 0; i < itemIds.length; i++)
            {
                int itemId = Integer.parseInt(itemIds[i]);
                int quantity = Integer.parseInt(quantities[i]);

                // Add the item to the basket
                basketSession.addItem(itemSession.find(itemId), quantity);
            }



        }
        catch (StockException se)
        {
            // Add to Basket button should be greyed out if item is out of stock,
            // so landing in this catch block implies customer has added an item
            // in an unsupported way.
            // TODO: If we want to support adding multiple of an item in one "add
            // to basket", we would want this to redirect to previous page with
            // a stock error message.
            throw new ServletException(se);
        }

        response.sendRedirect("view");
        return;
    }

    protected void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        if (request.getMethod().equalsIgnoreCase("post"))
        {
            // Testing the checkout process
            InitialContext context = new InitialContext();
            OrderSessionRemote orderSession = (OrderSessionRemote) context.lookup("vrw_GadgetShop/OrderSession/remote");
            ShoppingBasketSessionRemote shoppingBasketSession = getBasketSession(request);
            Order order = orderSession.checkout(shoppingBasketSession);



            shoppingBasketSession.terminate();
            request.getSession().removeAttribute("shoppingBasket");

            request.setAttribute("order", order);
            request.getRequestDispatcher("/basket/order_confirmation.jsp").forward(request, response);
        }
        else
        {
            ArrayList<Offer> offers = calculateOffers(request);
            request.setAttribute("basketItems", getBasketSession(request).getItems().values());
            request.getRequestDispatcher("/basket/checkout.jsp").forward(request, response);
        }
    }

    public ArrayList<Offer> calculateOffers(HttpServletRequest request) throws IOException, ServletException
    {
        try
        {

            ArrayList<Offer> activeOffers = new ArrayList<Offer>();
            Collection<Offer> allOffers = new ArrayList<Offer>();

            ShoppingBasketSessionRemote shoppingBasketSession = getBasketSession(request);

            InitialContext context = new InitialContext();
            OfferSessionRemote offerSession = (OfferSessionRemote) context.lookup("vrw_GadgetShop/OfferSession/remote");

            // Get all offers in the system
            allOffers = offerSession.findAll();

            Collection<BasketItem> basketItems = shoppingBasketSession.getItems().values();

            offerCheck:
            for (Offer o : allOffers)
            {

                boolean offerCheck = false;
                
                // Iterate through all offer items
                oiCheck:
                for (OfferItem oi : o.getItems())
                {

                    boolean isOIInBasket = false;

                    // Iterate through all basket items
                    basketItemCheck:
                    for (BasketItem bi : basketItems)
                    {
                        // If item ids match
                        if (bi.getItem().getId() == oi.getItem().getId())
                        {
                            // Check that quantity mathces
                            if (bi.getQuantity() >= oi.getQuantity())
                            {
                                isOIInBasket = true;
                                break basketItemCheck;
                            }
                        }
                    }

                    // Order item id doesn't match with item id
                    if(!isOIInBasket)
                    {
                        offerCheck = false;
                        break oiCheck;
                    }else
                    {
                        offerCheck = true;
                    }
                }

                if(offerCheck){
                    activeOffers.add(o);
                }

            }

            return activeOffers;

        }
        catch (NamingException e)
        {
            throw new ServletException("Can't calculate active offers");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>
}
