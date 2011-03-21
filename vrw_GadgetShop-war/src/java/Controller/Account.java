package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    CustomerSessionRemote customerSessionRemote = null;
    Customer customer = null;
   
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

            if(request.getParameter("create-account") != null)
            {
                
            }else if(request.getParameter("manage-account") !=null)
            {
            
            }

        } finally { 
            out.close();
        }
    }


    /**
     * Creates a new customer account
     *
     * 
     * @param request
     * @param response
     */
    private void createAccount(HttpServletRequest request, HttpServletResponse response)
    {
        // Account specific
        String username = null;
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
            // Read account specific parameters from request
            username = request.getParameter("username");
            email = request.getParameter("email");
            emailConfirmation = request.getParameter("email-confirmation");
            password = request.getParameter("password");
            passwordConfirmation = request.getParameter("password-confirmation");

            // Read personal details and address from request
            firstname = request.getParameter("first-name");
            lastname = request.getParameter("last-name");
            address1 = request.getParameter("address-1");
            address2 = request.getParameter("address-2");
            city = request.getParameter("city");
            county = request.getParameter("county");
            postcode = request.getParameter("postcode");
            country = request.getParameter("country");

            context = new InitialContext();
            customerSessionRemote = (CustomerSessionRemote) context.lookup(
                    "vrw_GadgetShop/CustomerSession/remote");

            

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