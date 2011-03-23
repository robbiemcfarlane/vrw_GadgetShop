package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vrw.ejb.session.CustomerSessionRemote;


import vrw.ejb.entity.Customer;

import Utils.AccountForm;

/**
 *
 * @author viktor
 *
 * Controller for creating and managing accounts
 */
public class Account extends HttpServlet
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
            String servletPath = request.getServletPath();
           
            // Create a new account
            if (servletPath.equals("/account/register"))
            {
                register(request, response);
            }
            // Manage existing account
            else if (servletPath.equals("/account/manage"))
            {
                manageAccount(request, response);
            }
            // Login
            else if (servletPath.equals("/account/login"))
            {
                login(request, response);
            }
            
        }
        catch (Exception ex)
        {
            out.println(ex.getMessage());
        }
        finally
        {

            out.close();
        }
    }

    /**
     * Create a new customer account
     *
     * 
     * @param request
     * @param response
     */
    private void register(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Postback
        if (request.getMethod().toUpperCase().equals("POST"))
        {
            AccountForm accountForm = new AccountForm();
            try
            {       
                Customer customer = accountForm.registerCustomer(request.getParameterMap());

                // Success...
                InitialContext context = new InitialContext();
                CustomerSessionRemote customerSessionRemote = (CustomerSessionRemote) context.lookup("vrw_GadgetShop/CustomerSession/remote");

                customerSessionRemote.register(customer);

                //customerSessionRemote.login(request.getSession(), customer.getNickname(), customer.getPassword());

                //response.sendRedirect("/account/my-account");
            }
            catch (Utils.GadgetShopValidationException gsve)
            {
                request.setAttribute("errorMessages", accountForm.getMessages());
            }
        }

        request.getRequestDispatcher("/account/create.jsp").forward(request, response);
    }

    /**
     * Manage existing customer account
     *
     * @param request
     * @param response
     */
    private void manageAccount(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrintWriter out = response.getWriter();
        out.println("MANAGE ACCOUNT");
    }

    /**
     * Authenticates user.
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        PrintWriter out = response.getWriter();
        out.println("LOGIN");
        
        /*String nickname = null;
        String password = null;

        try
        {
            nickname = request.getParameter("nickname").trim();
            password = request.getParameter("password").trim();

             context = new InitialContext();
             customerSessionRemote = (CustomerSessionRemote) context.lookup(
                    "vrw_GadgetShop/CustomerSession/remote");

             if (customerSessionRemote.authenticate(nickname, password))
             {
                // Store nickname in the session
                request.getSession().setAttribute("nickname", nickname);
                response.sendRedirect("/items/");
             }
             else
             {
                 // ToDo:
             }
        }
        catch (Exception e)
        {
        }*/
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
