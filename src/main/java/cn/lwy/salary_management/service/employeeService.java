package cn.lwy.salary_management.service;

import cn.lwy.salary_management.domain.PageBean;
import cn.lwy.salary_management.domain.e_d_j;
import cn.lwy.salary_management.domain.employee;

import java.util.List;

public interface employeeService {
    public PageBean<e_d_j> pageQuery(int currentPage,int pageSize,String ename);

    public void deleteSelect(List<Integer> list);

    public void deleteOne(int eid);

    public employee findByEid(int eid);

    public void addEmployee(employee employees);

    public List<e_d_j> selectAll();

    public void updateEmployee(employee employees);
}
