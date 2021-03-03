package cn.lwy.salary_management.service.impl;

import cn.lwy.salary_management.dao.employeeDao;
import cn.lwy.salary_management.dao.impl.employeeDaoImpl;
import cn.lwy.salary_management.domain.PageBean;
import cn.lwy.salary_management.domain.e_d_j;
import cn.lwy.salary_management.domain.employee;
import cn.lwy.salary_management.service.employeeService;

import java.util.List;

public class employeeServiceImpl implements employeeService {
    employeeDao employeeDao=new employeeDaoImpl();
    @Override //员工信息分页展示
    public PageBean<e_d_j> pageQuery(int currentPage, int pageSize, String ename) {
        PageBean<e_d_j> pageBean=new PageBean<>(); //分页数据存储javaBean
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        int totalCount=employeeDao.findTotalCount(ename); //查询总记录数
        pageBean.setTotalCount(totalCount);
        int start= (currentPage-1)*pageSize;
        List<e_d_j> byPage = employeeDao.findByPage(start, pageSize, ename);
        pageBean.setList(byPage);
        int TotalPage=totalCount%pageSize==0?totalCount/pageSize :(totalCount/pageSize)+1;
        pageBean.setTotalPage(TotalPage);  //计算总页数
        return pageBean;
    }
    @Override //删除选中
    public void deleteSelect(List<Integer> list) {
        //因为有外键,先删salary的数据
        for (int i = 0; i <list.size() ; i++) {
            Integer integer = list.get(i);
            int k = integer.intValue();
            employeeDao.deleteSalaryByeid(k); //删除工资信息
            employeeDao.deleteOne(k); // 删除员工信息
        }
    }
    @Override //删除单个
    public void deleteOne(int eid) {
        employeeDao.deleteSalaryByeid(eid);
        employeeDao.deleteOne(eid);
    }
    @Override //通过eid查找员工
    public employee findByEid(int eid) {

        return employeeDao.findByEid(eid);
    }
    @Override //添加员工信息
    public void addEmployee(employee employees) {
        employeeDao.addEmployee(employees);
    }
    @Override //查询所有员工
    public List<e_d_j> selectAll() {
        return employeeDao.selecltAll();
    }
    @Override //修改员工信息
    public void updateEmployee(employee employees) {
        employeeDao.update(employees);
    }
}
