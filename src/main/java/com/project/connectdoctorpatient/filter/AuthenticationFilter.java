package com.project.connectdoctorpatient.filter;

import com.project.connectdoctorpatient.constant.URL;
import com.project.connectdoctorpatient.controller.AuthenticationController;
import com.project.connectdoctorpatient.util.RequestUtil;
import com.project.connectdoctorpatient.util.SessionUtil;
import com.project.connectdoctorpatient.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.*;

/**
 * @author sakib.khan
 * @since 3/2/22
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (authenticated(request) || onRegisterState(request)) {
            chain.doFilter(req, res);
        } else {
            response.sendRedirect(URL.LOGIN_URL);
        }
    }

    private boolean authenticated(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute(AuthenticationController.LOGGED_USER_CMD);

        return nonNull(loggedUser);
    }

    private boolean onRegisterState(HttpServletRequest request) {
        User user = (User) SessionUtil.getAttribute("user");
        String path = RequestUtil.getRequestURI();

        return ("/patient".equals(path) || "/doctor".equals(path)) && isNull(user);
    }
}
