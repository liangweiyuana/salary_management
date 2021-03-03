package cn.lwy.salary_management.service;

import cn.lwy.salary_management.domain.PageBean;
import cn.lwy.salary_management.domain.e_s;
import cn.lwy.salary_management.domain.salary;

import java.util.List;

public interface SalaryService {
    public PageBean<e_s> pageQuery(int currentPage, int pageSize, String eidStr,String sdate);

    public void deleteOne(int eid,String sdate);

    public void deleteSelect(List<Integer> eidlist, List<String> sdatelist);

    public salary findSalary(int eid, String sdate);

    public void addSalary(salary salarys);

    public List<e_s> selectAll();

    public void updatesalary(salary salarys);
}
