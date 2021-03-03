package cn.lwy.salary_management.dao;

import cn.lwy.salary_management.domain.e_s;
import cn.lwy.salary_management.domain.salary;

import java.util.List;

public interface SalaryDao {
   public int findTotalCount(String eidStr,String sdate);

   List<e_s> findBypage(int currentPage, int pageSize, String eidStr,String sdate);

   public void deleteOne(int k,String  sdate);

    public salary findSalary(int eid, String sdate);

    public void addSalary(salary salarys);

    public List<e_s> selectAll();

    public void update(salary salarys);
}
