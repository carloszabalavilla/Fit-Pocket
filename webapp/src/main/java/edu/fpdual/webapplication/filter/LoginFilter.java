package edu.fpdual.webapplication.filter;

import edu.fpdual.webapplication.GlobalInfo;
import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.servlet.dto.Session;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Model(type = "Filter", version = "1.0", date = "01/06/2023")
@WebFilter(filterName = "loginFilter", urlPatterns = {"/jsp/common/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws
            IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Session session = (Session) request.getSession().getAttribute(GlobalInfo.session);
        if (session == null) {
            String alertScript = "<script>alert('Su sesion ha expirado.');</script>";
            servletResponse.getWriter().write(alertScript);

            String redirectScript = "<script>window.location.href = '" + GlobalInfo.URL_JSP_LOGIN + "';</script>";
            servletResponse.getWriter().write(redirectScript);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
