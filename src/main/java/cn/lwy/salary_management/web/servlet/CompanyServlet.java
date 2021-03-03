package cn.lwy.salary_management.web.servlet;

import cn.lwy.salary_management.domain.company;
import cn.lwy.salary_management.domain.count;
import cn.lwy.salary_management.service.CompanyService;
import cn.lwy.salary_management.service.impl.CompanyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/company/*")
public class CompanyServlet extends BaseServlet {
    private CompanyService companyService=new CompanyServiceImpl();
   public void selctCMembers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       company company = companyService.selectCMember();
       writeValue(company, response);
   }
    public void dcount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count dcount=companyService.dcount();
        writeValue(dcount, response);
    }
    public void jcount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count jcount=companyService.jcount();
        writeValue(jcount, response);
    }
    public void ttest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        count test=new count();
       List<String> list=new ArrayList<>();
        list.add("研发部");
        list.add("采购部");
        list.add("营销部");
        list.add("考虑部");
        test.setName(list);
        List<Integer> lsit = new ArrayList<>();
        lsit.add(16);
        lsit.add(30);
        lsit.add(20);
        lsit.add(25);
        test.setMembers(lsit);
        writeValue(test, response);
    }


}
