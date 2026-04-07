package cybersec.project.quizapp.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RateLimitFilter implements Filter {

    private Map<String, Long> lastRequestTime = new HashMap<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String ip = req.getRemoteAddr();
        long now = System.currentTimeMillis();

        if (lastRequestTime.containsKey(ip)) {
            long lastTime = lastRequestTime.get(ip);

            if (now - lastTime < 1000) { // меньше 1 секунды
                res.getWriter().write("Too many requests!");
                return;
            }
        }

        lastRequestTime.put(ip, now);

        chain.doFilter(request, response);
    }
}