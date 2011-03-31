/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vrw.ejb.entity.Item;
import vrw.ejb.session.ItemSessionRemote;
import vrw.ejb.session.ShoppingBasketSessionRemote;
import vrw.ejb.session.StockException;

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

    protected ShoppingBasketSessionRemote getBasket(HttpServletRequest request)
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
        request.setAttribute("basketItems", getBasket(request).getItems().values());
        request.getRequestDispatcher("/basket/basket.jsp").forward(request, response);
    }

    protected void addItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        try
        {
            int itemId = Integer.parseInt(request.getParameter("item-id"));

            InitialContext context = new InitialContext();
            ItemSessionRemote itemSession = (ItemSessionRemote) context.lookup("vrw_GadgetShop/ItemSession/remote");

            // Get the item
            Item item = itemSession.find(itemId);

            // Add the item to the basket
            getBasket(request).addItem(item, 1);
        }
        catch (StockException se)
        {
            se.printStackTrace();
        }

        response.sendRedirect("view");
        return;
    }

    protected void checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        request.setAttribute("basketItems", getBasket(request).getItems().values());
        request.getRequestDispatcher("/basket/checkout.jsp").forward(request, response);
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
