/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vrw.ejb.entity.Item;
import vrw.ejb.session.ItemSessionRemote;
import vrw.ejb.session.OfferSessionRemote;

/**
 *
 * @author Robbie
 */
public class ItemController extends HttpServlet
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
            if (servletPath.equals("/item/item"))
            {
                item(request, response);
            }
            // Manage existing account
            else if (servletPath.equals("/item/items"))
            {
                items(request, response);
            }
            // Login
            else if (servletPath.equals("/item/offers"))
            {
                offers(request, response);
            }
        }
        catch (NamingException e)
        {
            throw new ServletException(e);
        }
    }

    protected void items(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        String itemId = request.getParameter("item");

        InitialContext context = new InitialContext();

        ItemSessionRemote itemSession = (ItemSessionRemote) context.lookup("vrw_GadgetShop/ItemSession/remote");

        Collection<Item> itemList = itemSession.findAll();

        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("/item/items.jsp").forward(request, response);
    }

    protected void item(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try
        {
            String itemId = request.getParameter("item");
            if (itemId != null)
            {
                InitialContext context = new InitialContext();

                ItemSessionRemote itemSession = (ItemSessionRemote) context.lookup("vrw_GadgetShop/ItemSession/remote");

                Item item = itemSession.find(Integer.parseInt(itemId));

                request.setAttribute("item", item);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/item/item.jsp");
                dispatcher.forward(request, response);
            }
        }
        catch (NamingException ne)
        {
            ne.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }

    protected void offers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        InitialContext context = new InitialContext();
        OfferSessionRemote offersSession = (OfferSessionRemote) context.lookup("vrw_GadgetShop/OfferSession/remote");
        Collection offerList = offersSession.findAll();
        request.setAttribute("offerList", offerList);
        request.getRequestDispatcher("/item/offers.jsp").forward(request, response);
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
