package cn.lwy.salary_management.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CharchaterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String method = request.getMethod();
        if (method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        };
        if (method.equalsIgnoreCase("get")){
            request.setCharacterEncoding("utf-8");
        };
        response.setContentType("text/html; charset=UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
