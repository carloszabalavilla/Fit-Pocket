package edu.fpdual.webapplication.servlet;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.client.ProfileClient;
import edu.fpdual.webapplication.dto.Profile;
import edu.fpdual.webapplication.service.ProfileService;
import edu.fpdual.webapplication.servlet.dto.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile-servlet"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String ok = "Campos actualizados.";
        String error = "No se ha podido actualizar el perfil.";
        String dispatcherURLProfile = "jsp/common/profile.jsp";

        Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);
        Profile userProfile = new ProfileService(new ProfileClient()).getProfileById(session.getUserId());

        try {
            userProfile.setDescription(request.getParameter("description"));
            userProfile.setEmail(request.getParameter("email"));
            userProfile.setLink(request.getParameter("link"));
            userProfile.setLocation(request.getParameter("location"));
            userProfile.setPhone(Integer.parseInt(request.getParameter("phone")));
            if (new ProfileService(new ProfileClient()).updateProfile(userProfile)) {
                request.setAttribute("ok", ok);
                request.getRequestDispatcher(dispatcherURLProfile).forward(request, response);
            } else {
                request.setAttribute("error", error);
                request.getRequestDispatcher(dispatcherURLProfile).forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", error);
            request.getRequestDispatcher(dispatcherURLProfile).forward(request, response);
            e.printStackTrace();
        }

    }
}
