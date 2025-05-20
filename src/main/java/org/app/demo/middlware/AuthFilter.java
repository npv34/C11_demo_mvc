package org.app.demo.middlware;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        // cho request tiep tuc di tiep
        // kiem tra xem co session id or name cua user login khong
        // neu co thi cho di tiep
        // neu khong thi redirect ve trang login
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // loai bo request url: /auth/login -> khong filter session
        String path = httpRequest.getRequestURI();
        if (path.equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            httpResponse.sendRedirect("/auth/login");
            return;
        }
        String idUserLogin = (String) session.getAttribute("idUserLogin");
        if (idUserLogin != null){
            filterChain.doFilter(request, response);
            return;
        }

        httpResponse.sendRedirect("/auth/login");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
