package cybersec.project.quizapp.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    private static final Map<String, Integer> attempts = new HashMap<>();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        org.springframework.security.core.AuthenticationException exception)
            throws IOException, ServletException {

        String username = request.getParameter("username");

        int count = attempts.getOrDefault(username, 0) + 1;
        attempts.put(username, count);

        if (count > 2) {
            response.getWriter().write("Too many attempts! Account blocked.");
            return;
        }

        response.sendRedirect("/sign-in?error");
    }
}