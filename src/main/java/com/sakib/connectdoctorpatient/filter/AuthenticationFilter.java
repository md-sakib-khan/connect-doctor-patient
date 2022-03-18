package com.sakib.connectdoctorpatient.filter;

import com.sakib.connectdoctorpatient.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.*;
import static com.sakib.connectdoctorpatient.constant.URL.*;
import static com.sakib.connectdoctorpatient.controller.AuthenticationController.LOGGED_USER_CMD;

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
            response.sendRedirect(LOGIN_URL);
        }
    }

    private boolean authenticated(HttpServletRequest request) {
        User loggedUser = (User) request.getSession().getAttribute(LOGGED_USER_CMD);

        return nonNull(loggedUser);
    }

    private boolean onRegisterState(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String path = request.getRequestURI();

        return ("/patient".equals(path) || "/doctor".equals(path)) && isNull(user);
    }
}
