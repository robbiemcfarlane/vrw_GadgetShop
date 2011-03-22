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

import Utils.FormUtil;
import Utils.Form;
import Utils.AccountForm;

/**
 *
 * @author viktor
 *
 * Controller for creating and managing accounts
 */
public class Account extends HttpServlet {


    

    
   
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

            String path = request.getServletPath().split("/")[1];

            // Create a new account
            if(path.equals("create"))
            {
                createAccount(request, response);
            }
            // Manage existing account
            else if(path.equals("manage"))
            {

            }
            // Login
            else if(path.equals("login"))
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
        
        AccountForm accountForm = new AccountForm();
        Customer customer = null;

        try
        {
            customer = accountForm.registerCustomer(request);
            
            request.setAttribute("create-account", accountForm);
            request.setAttribute("customer", customer);            

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
//            nickname = request.getParameter("nickname").trim();
//            password = request.getParameter("password").trim();
//
//             context = new InitialContext();
//             customerSessionRemote = (CustomerSessionRemote) context.lookup(
//                    "vrw_GadgetShop/CustomerSession/remote");
//
//             if (customerSessionRemote.authenticate(nickname, password))
//             {
//                // Store nickname in the session
//                request.getSession().setAttribute("nickname", nickname);
//                response.sendRedirect("/items/");
//             }
//             else
//             {
//                 // ToDo:
//             }
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
