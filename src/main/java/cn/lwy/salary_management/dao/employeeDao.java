package cn.lwy.salary_management.dao;

import cn.lwy.salary_management.domain.e_d_j;
import cn.lwy.salary_management.domain.employee;

import java.util.List;

public interface employeeDao {
    public int findTotalCount(String ename);
    public List<e_d_j> findByPage(int start,int pageSize,String ename);

    public void deleteOne(int i);

    public employee findByEid(int eid);

    public void addEmployee(employee employees);

    public void deleteSalaryByeid(int eid);

    public List<e_d_j> selecltAll();

    public void update(employee employees);
}
