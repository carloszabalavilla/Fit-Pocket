package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.UserClient;
import edu.fpdual.webapplication.dto.User;
import edu.fpdual.webapplication.service.UserService;
import edu.fpdual.webapplication.servlet.dto.Session;
import edu.fpdual.webapplication.utilities.Password;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotFoundException;

import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String incorrectError = "Usuario o contraseña incorrectos";
        String notFoundError = "El usuario no existe";

        /*
         * Esta variable se añade porque no sirve la del globalInfo, ya que el caracter '/'
         * crea una ruta relativa en el dispatcher.
         * */
        String dispatcherURLLogin = "jsp/login/login.jsp";

        try {
            String userNameReceived = request.getParameter("userName").toLowerCase();
            String userPasswordReceived = new Password(request.getParameter("userPassword")).toString();
            Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);
            User user = new UserService(new UserClient()).getUserByName(userNameReceived);

            if (session != null) {
                response.sendRedirect(GlobalInfo.URL_JSP_HOME);
            } else if (!userPasswordReceived.equals(user.getUserPassword())) {
                request.setAttribute("error", incorrectError);
                request.getRequestDispatcher(dispatcherURLLogin).forward(request, response);
            } else {
                session = Session.builder().userId(user.getUserId())
                        .userName(userNameReceived)
                        .admin(user.isAdmin())
                        .build();
                request.getSession().setMaxInactiveInterval(500);
                request.getSession().setAttribute(GlobalInfo.session, session);
                response.sendRedirect(GlobalInfo.URL_JSP_HOME);
            }
        } catch (NotFoundException | NullPointerException e) {
            request.setAttribute("error", notFoundError);
            request.getRequestDispatcher(dispatcherURLLogin).forward(request, response);
        }
    }
}
