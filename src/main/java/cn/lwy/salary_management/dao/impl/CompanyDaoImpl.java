package cn.lwy.salary_management.dao.impl;

import cn.lwy.salary_management.dao.CompanyDao;
import cn.lwy.salary_management.domain.company;
import cn.lwy.salary_management.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public company selectCMember() {
        String sql="select * from company3118004920liangweiyuan";
        company query = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<company>(company.class));

        return query;

    }

    @Override
    public Integer dcount(String dname) {
        String sql="SELECT COUNT(*)   FROM e_d_j3118004920liangweiyuan GROUP BY dname HAVING dname=?  ";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, dname);
        return integer;
    }

    @Override
    public Integer jcount(String jname) {
        String sql="SELECT COUNT(*)   FROM e_d_j3118004920liangweiyuan GROUP BY jname HAVING jname=?  ";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, jname);
        return integer;
    }
}
