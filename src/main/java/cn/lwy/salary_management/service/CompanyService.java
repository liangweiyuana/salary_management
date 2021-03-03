package cn.lwy.salary_management.service;

import cn.lwy.salary_management.domain.company;
import cn.lwy.salary_management.domain.count;

/**
 * @author Administrator
 */
public interface CompanyService {
    public company selectCMember();

    public count dcount();

    public count jcount();
}
