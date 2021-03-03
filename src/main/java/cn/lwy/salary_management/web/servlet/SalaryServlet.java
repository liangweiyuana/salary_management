package cn.lwy.salary_management.web.servlet;

import cn.lwy.salary_management.domain.PageBean;
import cn.lwy.salary_management.domain.e_d_j;
import cn.lwy.salary_management.domain.e_s;
import cn.lwy.salary_management.domain.salary;
import cn.lwy.salary_management.service.SalaryService;
import cn.lwy.salary_management.service.employeeService;
import cn.lwy.salary_management.service.impl.SalaryServiceImpl;
import cn.lwy.salary_management.service.impl.employeeServiceImpl;
import com.sun.org.apache.regexp.internal.RE;
import org.omg.PortableInterceptor.INACTIVE;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.lwy.salary_management.utils.ExcelUtils.listToExcel;

@WebServlet("/salary/*")
public class SalaryServlet extends BaseServlet {
    private SalaryService salaryService=new SalaryServiceImpl();

    //分页查询
   public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String currentPageStr = request.getParameter("currentPage");//获取当前页码
       String eidStr = request.getParameter("eid"); //获取员工id
       String pageSizeStr=request.getParameter("pageSize"); //获取页面大小
       String sdate = request.getParameter("sdate"); //获取日期
       if ("null".equals(sdate)){
           sdate="";
       }
       if ("null".equals(eidStr)){
           eidStr="";
       }
       int currentPage=0;
       if (currentPageStr!=null&&currentPageStr.length()>0){
           currentPage=Integer.parseInt(currentPageStr);
       }else{
           currentPage=1; //默认第一页
       }
       int pageSize=0;
       if (pageSizeStr!=null&&pageSizeStr.length()>0){
           pageSize = Integer.parseInt(pageSizeStr);
       }else {
           pageSize=8;  //默认显示8条信息
       }
       PageBean<e_s> e_sPageBean = salaryService.pageQuery(currentPage, pageSize,eidStr,sdate);//调用分页查询方法

       writeValue(e_sPageBean,response);//json信息写回
    }
//删除选中
    public void deleteSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String arry = request.getParameter("arry"); //获取删除数组
        String[] split = arry.split("\""); // 字符串分割,获取要删除的工资信息
        List<Integer> eidlist=new ArrayList();
        List<String> sdatelist=new ArrayList();
        for (int i = 0; i <split.length ; i++) {
            if (i%2!=0){
                String[] split1 = split[i].split(",");
                eidlist.add(Integer.parseInt(split1[0]));
                sdatelist.add(split1[1]);
            }
        }
        System.out.println(eidlist);
        System.out.println(sdatelist);
    salaryService.deleteSelect(eidlist,sdatelist);//调用删除选中方法

    };
//删除一个
    public void deleteOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eidStr = request.getParameter("eid");
        String sdate = request.getParameter("sdate");
        int eid = Integer.parseInt(eidStr);
        salaryService.deleteOne(eid,sdate); //调用删除方法
    }
    //添加工资信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取页面传递的信息
        String eidstr = request.getParameter("eid");
        String ename = request.getParameter("ename");
        String sdate = request.getParameter("sdate");
        String jidstr = request.getParameter("jid");
        String etag = request.getParameter("etag");
        String eworkyearstr = request.getParameter("eworkyear");
        String overtimestr = request.getParameter("overtime");
        int eid = Integer.parseInt(eidstr);
        int jid = Integer.parseInt(jidstr);
        int eworkyear = Integer.parseInt(eworkyearstr);
        int overtime = Integer.parseInt(overtimestr);
        salary sa=salaryService.findSalary(eid,sdate);
        String contextPath = request.getContextPath()+"/error.html";
        if (sdate==null||!(sdate.length()>0)||sa!=null){ //如果日期有错误,重定向到错误提示页面
           response.sendRedirect(contextPath);
        }
        //计算工资
        salary salarys=new salary();
        salarys.setEid(eid);
        salarys.setSdate(sdate);
        salarys.setOvertime(overtime);
        //加班工资计算
        int overtimepay=overtime*20;
        salarys.setOvertimepay(overtimepay);
        //基础工资计算
        int baseSalary = 0;
        if (jid==1){
            baseSalary=6000;
        }
        if (jid==2){
            baseSalary=7000;
        }
        if (jid==3){
            baseSalary=8000;
        }
        if (jid==4){
            baseSalary=9000;
        }
        salarys.setBasesalary(baseSalary);
        int welfare=eworkyear*50;
        if ("正式员工".equals(etag)){
            welfare=welfare+100;
        }
        salarys.setWelfare(welfare);
        //总工资计算
        salarys.setTotalsalary(welfare+baseSalary+overtimepay);

        salaryService.addSalary(salarys);//调用添加工资方法
        String contextPath2 = request.getContextPath()+"/salary_list.html?eid="+eid;
        response.sendRedirect(contextPath2); //重定向到显示改员工工资页面



    };
    //下载为报表
    public void downes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {  //为excel起列名
            List<e_s> e_s = salaryService.selectAll();
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
            fieldMap.put("eid", "员工号");
            fieldMap.put("ename", "姓名");
            fieldMap.put("sdate", "工资日期");
            fieldMap.put("basesalary", "基础工资");
            fieldMap.put("welfare", "福利");
            fieldMap.put("overtime", "加班(小时)");
            fieldMap.put("overtimepay", "加班工资");
            fieldMap.put("totalsalary", "总工资");
            listToExcel(e_s, fieldMap, "bbb", response); //调用下载为excel方法
        } catch (Exception e) {
            try { //异常处理
                throw new Exception("export error:" + e.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    };
    //员工单月工资信息显示,用于修改是信息回显
    public void findone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eidStr = request.getParameter("eid");
        String sdate = request.getParameter("sdate");
        int eid = Integer.parseInt(eidStr);
        salary salary = salaryService.findSalary(eid, sdate);
        writeValue(salary, response); //json信息写回
    }
//按月份查询
    public void selectBydate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sdate = request.getParameter("sdate");
        String eidstr = request.getParameter("eid");
        salary salary=new salary();
        if (!"null".equals(sdate)){
            salary.setSdate(sdate);
        }
        if (!"null".equals(eidstr)&&eidstr.length()>0){
            int eid= Integer.parseInt(eidstr);
            salary.setEid(eid);
        }
        writeValue(salary,response); //json信息写回

    };
//修改工资信息
    public void updatesalary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取修改信息
        String eidstr = request.getParameter("eid");
        String sdate = request.getParameter("sdate");
        String basesalarystr = request.getParameter("basesalary");
        String welfarestr = request.getParameter("welfare");
        String overtimestr = request.getParameter("overtime");
        int eid = Integer.parseInt(eidstr);
        int basesalary = Integer.parseInt(basesalarystr);
        int welfare = Integer.parseInt(welfarestr);
        int overtime = Integer.parseInt(overtimestr);
        salary sa=salaryService.findSalary(eid,sdate);
        //重新计算工资
        salary salarys=new salary();
        salarys.setEid(eid);
        salarys.setSdate(sdate);
        salarys.setOvertime(overtime);
        //加班工资计算
        int overtimepay=overtime*20;
        salarys.setOvertimepay(overtimepay);
        salarys.setBasesalary(basesalary);
        salarys.setWelfare(welfare);
        //总工资计算
        salarys.setTotalsalary(welfare+basesalary+overtimepay);
        salaryService.updatesalary(salarys);
        writeValue(salarys, response);//json信息写回

    };
    public void mod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    };

}
