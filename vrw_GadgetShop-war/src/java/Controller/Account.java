package Controller;

import Utils.AccountCreateForm;
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

import Utils.AccountLoginForm;
import Utils.ValidationException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

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
        String servletPath = request.getServletPath();

        try
        {
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
            // Logout
            else if (servletPath.equals("/account/logout"))
            {   
                logout(request, response);
            }
        }
        catch (NamingException e)
        {
            throw new ServletException(e);
        }      
    }

    /**
     * Create a new customer account     *
     * 
     * @param request
     * @param response
     */
    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        String url = "/account/register.jsp";

        // Postback
        if (request.getMethod().equalsIgnoreCase("POST"))
        {
            AccountCreateForm accountCreateForm = new AccountCreateForm();
            try
            {
                Customer customer = accountCreateForm.registerCustomer(request.getParameterMap());

                InitialContext context = new InitialContext();
                CustomerSessionRemote customerSessionRemote = (CustomerSessionRemote) context.lookup(
                        "vrw_GadgetShop/CustomerSession/remote");

                // TODO: Check for case sensitivity when looking for existing nickname
                // Customer with that nickname doesn't already exist
                if (customerSessionRemote.find(customer.getNickname()) == null)
                {
                    customerSessionRemote.register(customer);

                    // Log the customer in
                    request.getSession().setAttribute("nickname", customer.getNickname());
                    response.sendRedirect("manage");
                    return;
                }
                // Nickname already exists
                else
                {
                    accountCreateForm.setMessage("nickname", "This nickname has already been taken");
                }
            }
            catch (ValidationException e)
            {
                // No action needed; error messages are added below
            }

            request.setAttribute("errorMessages", accountCreateForm.getMessages());
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Manage existing customer account
     *
     * @param request
     * @param response
     */
    private void manageAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        String url = "/account/manage.jsp";
        Customer customer = null;

        InitialContext context = new InitialContext();
        CustomerSessionRemote customerSessionRemote = (CustomerSessionRemote) context.lookup(
                "vrw_GadgetShop/CustomerSession/remote");

        // If client tries to access manageAccount without being logged in,
        // this will throw an IllegalArgumentException, which will be caught by
        // application error page
        // TODO: Changed our minds; we want this to redirect to login() if the
        // user isn't logged in.
        customer = customerSessionRemote.find(((Customer) request.getSession().getAttribute("customer")).getNickname());
        request.setAttribute("customer", customer);

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Authenticates user.
     *
     * @param request
     * @param response
     */
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NamingException
    {
        String url = "/account/login.jsp";

        // Postback
        if (request.getMethod().equalsIgnoreCase("POST"))
        {
            AccountLoginForm accountLoginForm = new AccountLoginForm();

            try
            {
                // Read nickname and password
                Customer customer = accountLoginForm.loginCustomer(request.getParameterMap());

                // Authenticate customer
                InitialContext context = new InitialContext();
                CustomerSessionRemote customerSessionRemote = (CustomerSessionRemote) context.lookup(
                        "vrw_GadgetShop/CustomerSession/remote");

                //If authentication succeeds than store customer nickname in the session
                if (customerSessionRemote.authenticate(customer.getNickname(), customer.getPassword()))
                {
                    //request.getSession().setAttribute("nickname", customer.getNickname());
                    request.getSession().setAttribute("customer", customerSessionRemote.find(customer.getNickname()));
                    response.sendRedirect("manage");
                    return;
                }
                //If authentication fails, than re-direct back to the login form with appropriate error message
                else
                {
                    //ToDo: Review text
                    accountLoginForm.setMessage("password", "Nickname and password combination is incorrect");
                }

            }
            catch (ValidationException e)
            {
                // No action needed; error messages are added below
            }

            request.setAttribute("errorMessages", accountLoginForm.getMessages());
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Log out a user.
     *
     * @param request
     * @param response
     */
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    { 
        request.getSession().removeAttribute("nickname");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        this.processRequest(request, response);
        /*
        String servletPath = request.getServletPath();
        String url = null;

        // Register
        if (servletPath.equals("/account/register"))
        {
        url = "/account/register.jsp";
        }
        else if (servletPath.equals("/account/manage"))
        {
        url = "/account/manage";
        }
        else if (servletPath.equals("/account/login"))
        {
        url = "/account/login";
        }

        request.getRequestDispatcher(url).forward(request, response);
         */
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
        this.processRequest(request, response);
        /*
        String servletPath = request.getServletPath();

        try
        {
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
        // Logout
        else if (servletPath.equals("/account/logout"))
        {
        logout(request, response);
        }
        }
        catch (NamingException e)
        {
        throw new ServletException(e);
        }
         */

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
