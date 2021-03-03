package cn.lwy.salary_management.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1获取请求路径
        String uri = request.getRequestURI();
        System.out.println("请求路径为:"+uri);
        //2获取请求方法名
        String MethodName = uri.substring(uri.lastIndexOf('/' )+1);
        //3获取方法对象

        /**暴力反射:如果构造方法、普通方法、字段 被private(私有)修饰，默认情况下，在该类的外部是不允许被访问的
         ，如果使用暴力反射，即使被private修饰也可以进行操作**/
        try {
            //暴力反射实现
            /**Method declaredMethod = this.getClass().getDeclaredMethod(MethodName, HttpServletRequest.class, HttpServletResponse.class);
               declaredMethod.setAccessible(true);**/
            //NoSuchMethodException异常,因为this的方法使用了protected,因改为public
            Method method = this.getClass().getMethod(MethodName, HttpServletRequest.class, HttpServletResponse.class);
            try {
                method.invoke(this,request,response);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");

        objectMapper.writeValue(response.getOutputStream(), obj);
    }
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


}
