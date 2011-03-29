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
            // Create a new account
            if (servletPath.equals("/basket/view"))
            {
                viewBasket(request, response);
            }
        }
        catch (NamingException e)
        {
            throw new ServletException(e);
        }
    }

    protected void viewBasket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        HttpSession session = request.getSession();
        ShoppingBasketSessionRemote shoppingBasketSession = (ShoppingBasketSessionRemote) session.getAttribute("shoppingBasket");
        if (shoppingBasketSession == null)
        {
            InitialContext context = new InitialContext();
            shoppingBasketSession = (ShoppingBasketSessionRemote) context.lookup("vrw_GadgetShop/ShoppingBasketSession/remote");
            session.setAttribute("shoppingBasket", shoppingBasketSession);
        }

        Item item1 = new Item("16GB USB Memory Stick", "A long description here...", "A short description here...", new BigDecimal(15.00), new BigDecimal(11.00), 99, true);
        item1.setId(1);

        try
        {
            shoppingBasketSession.addItem(item1, 1);
        }
        catch (StockException se)
        {
            se.printStackTrace();
        }
        request.setAttribute("basketItems", shoppingBasketSession.getItems().values());
        request.getRequestDispatcher("/basket/basket.jsp").forward(request, response);
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
