package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.utilities.Email;
import edu.fpdual.webapplication.utilities.InvalidEmailException;
import edu.fpdual.webapplication.utilities.emailsender.Sender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ContactServlet", urlPatterns = {"/contact-servlet"})
public class ContactServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String ok = "Hemos recibido tu mensaje. Muchas gracias";

        String dispatcherURLRestore = "jsp/contactUs.jsp";

        String name = request.getParameter("nameContact");
        String bodyMesage = request.getParameter("contactMessage");
        String email = request.getParameter("emailContact");

        try {
            email = new Email(email.toLowerCase()).toString();
        } catch (InvalidEmailException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(dispatcherURLRestore).forward(request, response);
            return;
        }

        if (new Sender().send("fitpocketapp@gmail.com", "Nuevo mensaje de " + name, "<b>" + bodyMesage + "</b><br/>Contacto de " + name + ": <br/>" + email)) {
            request.setAttribute("ok", ok);
            request.getRequestDispatcher(dispatcherURLRestore).forward(request, response);
        } else {
            request.setAttribute("error", "Se ha producido un error");
            request.getRequestDispatcher(dispatcherURLRestore).forward(request, response);
        }
    }
}
