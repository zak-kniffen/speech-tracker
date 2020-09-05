package com.babyspeak.speechtracker;

import com.babyspeak.speechtracker.controllers.AuthenticationController;
import com.babyspeak.speechtracker.models.User;
import com.babyspeak.speechtracker.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css", "/home");



    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/login");
        return false;
    }

    private static boolean isWhitelisted(String path) {
        if (path.equals("/")){
            return true;
        }
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }

        }
        return false;
    }

}