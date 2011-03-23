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
import Utils.GadgetShopValidationException;
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
    }

    /**
     * Create a new customer account     *
     * 
     * @param request
     * @param response
     */
    private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        AccountCreateForm accountCreateForm = new AccountCreateForm();

        try
        {
            Customer customer = accountCreateForm.registerCustomer(request.getParameterMap());

            //ToDo: speak to Robbie/Will about refactoring this.
            InitialContext context = new InitialContext();
            CustomerSessionRemote customerSessionRemote = (CustomerSessionRemote) context.lookup(
                    "vrw_GadgetShop/CustomerSession/remote");

            customerSessionRemote.register(customer);

            // Log the customer in
            request.getSession().setAttribute("nickname", customer.getNickname());
            response.sendRedirect("/account/manage");
        }
        catch (GadgetShopValidationException e)
        {
            request.setAttribute("errorMessages", accountCreateForm.getMessages());
        }
        catch (Exception e)
        {
        }
        request.getRequestDispatcher("/account/register.jsp").forward(request, response);
    }

    /**
     * Manage existing customer account
     *
     * @param request
     * @param response
     */
    private void manageAccount(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
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
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            //Read nickname and password
            AccountLoginForm accountLoginForm = new AccountLoginForm();
            Customer customer = accountLoginForm.loginCustomer(request.getParameterMap());




            //To authenticate cutomer
            InitialContext context = new InitialContext();
            CustomerSessionRemote customerSessionRemote = (CustomerSessionRemote) context.lookup(
                    "vrw_GadgetShop/CustomerSession/remote");

            //If authentication succeeds than store customer nickname in the session
            if (customerSessionRemote.authenticate(customer.getNickname(), customer.getPassword()))
            {
                request.getSession().setAttribute("nickname", customer.getNickname());
                response.sendRedirect("/account/manage");

            }
            //If authentication fails, than re-direct back to the login form with appropriate error message
            else
            {
                request.setAttribute("errorMessages", accountLoginForm.getMessages());
                request.getRequestDispatcher("/account/login.jsp").forward(request, response);
            }






            //ToDo: Validation errors: empty user/password fields, username doesn't exist, password is incorrect
        }
        catch (GadgetShopValidationException e)
        {
        }
        catch (Exception e)
        {
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
        String servletPath = request.getServletPath();
        String url = null;    

        // Register
        if (servletPath.equals("/account/register"))
        {
            url = "/account/register.jsp";
        }
        else if(servletPath.equals("/account/manage"))
        {
            url = "/account/manage.jsp";
        }
        request.getRequestDispatcher(url).forward(request,response);
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
