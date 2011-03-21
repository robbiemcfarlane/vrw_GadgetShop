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

/**
 *
 * @author viktor
 *
 * Controller for creating and managing accounts
 */
public class Account extends HttpServlet {


    private InitialContext context = null;
    private CustomerSessionRemote customerSessionRemote = null;
    private Customer customer = null;

    
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {


            // Create a new account
            if(request.getParameter("create-account") != null)
            {
                out.println(request.getParameter("create-account"));
                createAccount(request, response);
            }
            // Manage existing account
            else if(request.getParameter("manage-account") !=null)
            {

            }
            // Login
            else if(request.getParameter("login") != null)
            {
                authenticate(request, response);
            }

            
        }
        catch(Exception ex)
        {
            out.println(ex.getMessage());
        }
        finally {
            //ToDo: Remove this
            out.println("Finally hit.");
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
    private void createAccount(HttpServletRequest request, HttpServletResponse response)
    {
        // Account specific
        String nickname = null;
        String email = null;
        String emailConfirmation = null;
        String password = null;
        String passwordConfirmation = null;

        // Personal details, address
        String firstname = null;
        String lastname = null;
        String address1 = null;
        String address2 = null;
        String city = null;
        String county = null;
        String postcode = null;
        String country = null;

        try
        {

            //ToDo: checks for nulls and validations to be added          
            

            // Read account specific parameters from request
            nickname = request.getParameter("nickname").trim();
            email = request.getParameter("email").trim();
            emailConfirmation = request.getParameter("email-confirmation").trim();
            password = request.getParameter("password").trim();
            passwordConfirmation = request.getParameter("password-confirmation").trim();

            // Read personal details and address from request
            firstname = request.getParameter("first-name").trim();
            lastname = request.getParameter("last-name").trim();
            address1 = request.getParameter("address-1").trim();
            address2 = request.getParameter("address-2").trim();
            city = request.getParameter("city").trim();
            county = request.getParameter("county").trim();
            postcode = request.getParameter("postcode").trim();
            country = request.getParameter("country").trim();

            // Create a new customer
            customer = new Customer(nickname, firstname, lastname, password,
                    address1, address2, city, county, postcode, country, email);

            context = new InitialContext();
            customerSessionRemote = (CustomerSessionRemote) context.lookup(
                    "vrw_GadgetShop/CustomerSession/remote");

            customerSessionRemote.register(customer);

        }
        catch(Exception e)
        {
            
        }
    }

    /**
     * Manage existing customer account
     *
     * @param request
     * @param response
     */
    private void manageAccount(HttpServletRequest request, HttpServletResponse response)
    {

    }

    /**
     * Authenticates user.
     *
     * @param request
     * @param response
     */
    private void authenticate(HttpServletRequest request, HttpServletResponse response)
    {
        String nickname = null;
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
        catch(Exception e)
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
    throws ServletException, IOException {
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
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
