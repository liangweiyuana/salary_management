package cn.lwy.salary_management.dao;

import cn.lwy.salary_management.domain.company;

/**
 * @author Administrator
 */
public interface CompanyDao {
    public company selectCMember();

    public Integer dcount(String danme);

    Integer jcount(String jname);
}
