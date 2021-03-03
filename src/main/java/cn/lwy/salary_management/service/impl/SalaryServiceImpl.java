package cn.lwy.salary_management.service.impl;

import cn.lwy.salary_management.dao.SalaryDao;
import cn.lwy.salary_management.dao.impl.SalaryDaoImpl;
import cn.lwy.salary_management.domain.PageBean;
import cn.lwy.salary_management.domain.e_s;
import cn.lwy.salary_management.domain.salary;
import cn.lwy.salary_management.service.SalaryService;

import java.util.List;

public class SalaryServiceImpl implements SalaryService {
    private SalaryDao salaryDao=new SalaryDaoImpl();
    /**
     * 分页查询工资信息
     * @param currentPage
     * @param pageSize
     * @param eidStr
     * @return
     */
    @Override //分页查询
    public PageBean<e_s> pageQuery(int currentPage, int pageSize, String eidStr,String sdate) {
       PageBean<e_s> pageBean=new PageBean<>(); //分页数据存储javaBean
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);
        int totalCount = salaryDao.findTotalCount(eidStr,sdate); //查询总记录数
        pageBean.setTotalCount(totalCount);
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pageBean.setTotalPage(totalPage); //计算总页数
        int start=(currentPage-1)*pageSize;
        List<e_s> list=salaryDao.findBypage(start,pageSize,eidStr,sdate);
        pageBean.setList(list);
        return pageBean; //写回
    }
    @Override//删除工资信息
    public void deleteOne(int eid,String sdate) {
        salaryDao.deleteOne(eid,sdate);
    }
    @Override //删除选中工资信息
    public void deleteSelect(List<Integer> eidlist, List<String> sdatelist) {
        for (int i = 0; i <eidlist.size() ; i++) { //根据选中数据循环删除
            salaryDao.deleteOne(eidlist.get(i).intValue(),sdatelist.get(i));
        }
    }
    @Override //查询单条工资信息
    public salary findSalary(int eid, String sdate) {
        return salaryDao.findSalary(eid,sdate);
    }
    @Override //添加工资信息
    public void addSalary(salary salarys) {
        salaryDao.addSalary(salarys);
    }
    @Override //查询所有工资
    public List<e_s> selectAll() {
        return salaryDao.selectAll();
    }
    @Override //修改工资信息
    public void updatesalary(salary salarys) {
        salaryDao.update(salarys);
    }


}
