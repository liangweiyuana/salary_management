package cn.lwy.salary_management.service.impl;

import cn.lwy.salary_management.dao.CompanyDao;
import cn.lwy.salary_management.dao.impl.CompanyDaoImpl;
import cn.lwy.salary_management.domain.company;
import cn.lwy.salary_management.domain.count;
import cn.lwy.salary_management.service.CompanyService;

import java.util.ArrayList;
import java.util.List;

public class CompanyServiceImpl implements CompanyService {
    private CompanyDao companyDao=new CompanyDaoImpl();
    @Override
    public company selectCMember() {
        return companyDao.selectCMember();
    }

    @Override
    public count dcount() {
        count dcount=new count();
        List<String> list=new ArrayList<>();
        List<Integer> lsit = new ArrayList<>();
        list.add("研发部");
        lsit.add(companyDao.dcount("研发部"));
        list.add("营销部");
        lsit.add(companyDao.dcount("营销部"));
        list.add("财务部");
        lsit.add(companyDao.dcount("财务部"));
        list.add("采购部");
        lsit.add(companyDao.dcount("采购部"));
        dcount.setMembers(lsit);
        dcount.setName(list);
        return dcount;
    }

    @Override
    public count jcount() {
        count jcount=new count();
        List<String> list=new ArrayList<>();
        List<Integer> lsit = new ArrayList<>();
        list.add("普通职员");
        lsit.add(companyDao.jcount("普通职员"));
        list.add("组长");
        lsit.add(companyDao.jcount("组长"));
        list.add("高级专家");
        lsit.add(companyDao.jcount("高级专家"));
        list.add("经理");
        lsit.add(companyDao.jcount("经理"));
        jcount.setMembers(lsit);
        jcount.setName(list);
        return jcount;
    }
}
