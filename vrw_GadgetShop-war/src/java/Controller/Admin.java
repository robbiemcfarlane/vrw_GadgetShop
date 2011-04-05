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
import vrw.ejb.entity.Offer;
import vrw.ejb.session.ItemSessionRemote;
import vrw.ejb.session.OfferSessionRemote;

/**
 *
 * @author viktor
 */
public class Admin extends HttpServlet
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
            // View all items
            if (servletPath.equals("/admin/items"))
            {
                items(request, response);
            }else if(servletPath.equals("/admin/offers/all"))
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
        InitialContext context = new InitialContext();
        ItemSessionRemote itemSession = (ItemSessionRemote) context.lookup("vrw_GadgetShop/ItemSession/remote");

        Collection<Item> itemList = itemSession.findAllOrderBy("name", "ASC");

        // Postback
        if(request.getMethod().equalsIgnoreCase("post"))
        {
            for(Item item : itemList)
            {
                item.setInShopWindow(Boolean.parseBoolean(request.getParameter("in-shop-window_"+item.getId())));
                itemSession.update(item);
            }
            request.setAttribute("message","Your changes have been saved");
        }

        request.setAttribute("itemList", itemList);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/items.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Offer(combo) management
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws NamingException
     */
    protected void offers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        InitialContext context = new InitialContext();
        OfferSessionRemote offerSession = (OfferSessionRemote) context.lookup("vrw_GadgetShop/OfferSession/remote");        

        if(request.getMethod().equalsIgnoreCase("post"))
        {
            //ToDo: implement
        }else{
            Collection<Offer> offerList = offerSession.findAll();
            request.setAttribute("offerList", offerList);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/offers/all.jsp");
        dispatcher.forward(request, response);


        // Check for GET/POST
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
