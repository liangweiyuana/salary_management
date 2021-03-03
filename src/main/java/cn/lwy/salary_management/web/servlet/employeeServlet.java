package cn.lwy.salary_management.web.servlet;

import cn.lwy.salary_management.domain.PageBean;
import cn.lwy.salary_management.domain.ResultInfo;
import cn.lwy.salary_management.domain.e_d_j;
import cn.lwy.salary_management.domain.employee;
import cn.lwy.salary_management.service.employeeService;
import cn.lwy.salary_management.service.impl.employeeServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static cn.lwy.salary_management.utils.ExcelUtils.listToExcel;

@WebServlet("/employee/*")
public class employeeServlet extends BaseServlet {
    private employeeService  employeeService=new employeeServiceImpl();
    //分页查询
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");

        String pageSizeStr=request.getParameter("pageSize");

        String enameStr=request.getParameter("ename");


        if ("null".equals(enameStr)){
            enameStr="";
        }

        int currentPage=0;
        if (currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        int pageSize=0;
        if (pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize=6;
        }
        PageBean<e_d_j> e_d_jPageBean = employeeService.pageQuery(currentPage, pageSize, enameStr);

        writeValue(e_d_jPageBean,response);
    };
    //删除选中
    public void deleteSelect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String arry = request.getParameter("arry");
        String[] split = arry.split("\"");
        List <Integer> list=new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
            System.out.println(split[i]+":"+i);
            if (i%2!=0){
                list.add(Integer.valueOf(split[i]));
            }
        }
        employeeService.deleteSelect(list);


    };
//删除
    public void deleteOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eidStr = request.getParameter("eid");
        int eid = Integer.parseInt(eidStr);
        employeeService.deleteOne(eid);
    }
    //添加信息
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        employee employees=new employee();
        try {
            BeanUtils.populate(employees, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        employee byEid = employeeService.findByEid(employees.getEid());
        if (byEid==null){
            employeeService.addEmployee(employees);
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setFlag(true);
            resultInfo.setEname(employees.getEname());
            writeValue(resultInfo, response);
        }
        else{
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("已存在该员工id,请重新输入员工eid");
            writeValue(resultInfo, response);
        }
    };
    //查找一个
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eidStr = request.getParameter("eid");
        System.out.println(111);
        int eid = Integer.parseInt(eidStr);
        employee byEid = employeeService.findByEid(eid);
        writeValue(byEid, response);

    };
//下载
    public void downedj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<e_d_j> e_d_js = employeeService.selectAll();
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
            fieldMap.put("eid", "编号");
            fieldMap.put("ename", "姓名");
            fieldMap.put("dname", "部门");
            fieldMap.put("jname", "职位");
            fieldMap.put("eage", "年龄");
            fieldMap.put("esex", "性别");
            fieldMap.put("ephone", "电话");
            fieldMap.put("etag", "类型");
            fieldMap.put("eworkyear", "工龄");
            // 文件名默认设置为当前时间：年月日时分秒
            String fileName = DateFormatUtils.format(new Date(), "yyyyMMddhhmmss");
            // 设置response头信息

            listToExcel(e_d_js, fieldMap, "aaa", response);


            System.out.println("download success!");
        } catch (Exception e) {
            try {
                throw new Exception("export error:" + e.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    };
//修改
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        employee employees=new employee();
        try {
            BeanUtils.populate(employees, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

            employeeService.updateEmployee(employees);

            writeValue(employees, response);


    };
    public void mod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    };
}
