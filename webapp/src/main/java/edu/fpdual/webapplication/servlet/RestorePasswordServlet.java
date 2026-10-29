package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.client.ProfileClient;
import edu.fpdual.webapplication.client.UserClient;
import edu.fpdual.webapplication.dto.Profile;
import edu.fpdual.webapplication.dto.User;
import edu.fpdual.webapplication.service.ProfileService;
import edu.fpdual.webapplication.service.UserService;
import edu.fpdual.webapplication.utilities.Email;
import edu.fpdual.webapplication.utilities.InvalidEmailException;
import edu.fpdual.webapplication.utilities.Password;
import edu.fpdual.webapplication.utilities.emailsender.Sender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.io.IOException;

@WebServlet(name = "RestorePasswordServlet", urlPatterns = {"/restore-password-servlet"})
public class RestorePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String notFoundError = "Email no encontrado";
        String ok = "Se ha enviado un email con tu nueva contraseña";

        String dispatcherURLRestore = "jsp/login/restorePassword.jsp";

        try {
            ProfileService profileService = new ProfileService(new ProfileClient());
            Profile profile = new Profile();
            try {
                profile = profileService.getProfileByEmail(String.valueOf(new Email(request.getParameter("email"))));
            } catch (InvalidEmailException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher(dispatcherURLRestore).forward(request, response);
            }
            String code = Password.resetPassword();
            String bodyMesage = "Esta es tu nueva contraseña: " + code;

            new Sender().send(profile.getEmail(), "Restauración de contraseña", "<b>" + bodyMesage + "</b><br/>Link para restaurar contraseña: http://localhost:8080/ProyectoPrueba/jsp/login/updatePassword.jsp");
            UserService userService = new UserService(new UserClient());
            User user = userService.getUserById(profile.getUserId());
            user.setUserPassword(new Password(code).toString());
            userService.updateUser(user);
            request.setAttribute("ok", ok);
            request.getRequestDispatcher(dispatcherURLRestore).forward(request, response);

        } catch (NotFoundException | NullPointerException | BadRequestException e) {
            request.setAttribute("error", notFoundError);
            request.getRequestDispatcher(dispatcherURLRestore).forward(request, response);
        }
    }
}
