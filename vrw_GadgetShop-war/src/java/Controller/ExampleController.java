
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/* REQUIRED IMPORTS */
import javax.naming.InitialContext;
import javax.naming.NamingException;
import vrw.ejb.entity.Item;
import vrw.ejb.session.ItemSessionRemote;
import vrw.ejb.entity.Customer;
import vrw.ejb.session.CustomerSessionRemote;
import java.util.Collection;
import java.util.List;
import vrw.ejb.entity.Employee;
import vrw.ejb.entity.Offer;
import vrw.ejb.entity.OfferItem;
import vrw.ejb.session.EmployeeSessionRemote;
import vrw.ejb.session.OfferSessionRemote;

/**
 *
 * @author Robbie
 */
public class ExampleController extends HttpServlet
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {

            try
            {
                InitialContext context = new InitialContext();

                ItemSessionRemote itemSession = (ItemSessionRemote) context.lookup("vrw_GadgetShop/ItemSession/remote");
                CustomerSessionRemote customerSession = (CustomerSessionRemote) context.lookup("vrw_GadgetShop/CustomerSession/remote");
                EmployeeSessionRemote employeeSession = (EmployeeSessionRemote) context.lookup("vrw_GadgetShop/EmployeeSession/remote");
                OfferSessionRemote offerSession = (OfferSessionRemote) context.lookup("vrw_GadgetShop/OfferSession/remote");

                // Create 2 dummy items
                Item item1 = new Item("16GB USB Memory Stick", "A long description here...", "A short description here...", new BigDecimal(15.00), new BigDecimal(11.00), 3, true);
                Item item2 = new Item("32GB USB Memory Stick", "A long description here...", "A short description here...", new BigDecimal(25.00), new BigDecimal(21.00), 89, false);

                // Add the 2 items to the database (persist)
                item1 = itemSession.add(item1);
                item2 = itemSession.add(item2);

                // Create a new customer
                Customer cust1 = new Customer("admin", "admin", "admin", "pwd123", "Random Business Estate", "Unit 1", "Norwich", "Norfolk", "NR2 1AB", "England", "me@me.com");

                Employee viktor = new Employee("viktor","Viktor","Peacock","admin");
                Employee robbie = new Employee("robbie","Robbie","McFarlane","admin");
                Employee will = new Employee("will","Will","Atterson","admin");

                // Add examples of an offer
                Offer offer1 = new Offer("Buy One Get One Free", "Buy One Get One Free on 16 GB Memory Stick", new BigDecimal(15.00));

                OfferItem offerItem = new OfferItem(item1,2);
                offerItem.setOffer(offer1);

                offer1.addOfferItem(offerItem);

                // Add the customer to the database (persist)
                customerSession.register(cust1);

                employeeSession.register(viktor);
                employeeSession.register(will);
                employeeSession.register(robbie);

                offerSession.add(offer1);

                // Retrieve the items that are in the shop window
                Collection<Item> itemList = itemSession.findAllInShopWindow();

                request.setAttribute("itemList", itemList);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);                
            }
            catch (NamingException e)
            {
                throw new ServletException("JNDI problem", e);
            }

        }
        catch(Exception e)
        {
            throw new ServletException(e.getMessage());
        }
        finally
        {
            out.close();
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
