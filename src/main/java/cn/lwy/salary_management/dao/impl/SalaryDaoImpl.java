package cn.lwy.salary_management.dao.impl;

import cn.lwy.salary_management.dao.SalaryDao;
import cn.lwy.salary_management.domain.e_s;
import cn.lwy.salary_management.domain.salary;
import cn.lwy.salary_management.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class SalaryDaoImpl implements SalaryDao {

    private  JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource()); //获取数据库连接池
    @Override //查询总记录数
    public int findTotalCount(String eidStr,String sdate) {
        String sql=" select count(*) from e_s3118004920liangweiyuan where 1 = 1  "; //查询总数sql语句
        StringBuilder stringBuilder = new StringBuilder(sql);
        List params=new ArrayList();
        if (eidStr!=null&&eidStr.length()>0){ //员工eid非空,添加查询条件
            int eid=Integer.parseInt(eidStr);
            stringBuilder.append(" and eid = ? ");
            params.add(eid);
        }
        if (sdate!=null&&sdate.length()>0){ //日期非空,添加查询条件
             stringBuilder.append(" and sdate =? ");
            params.add(sdate);
        }
        sql=stringBuilder.toString();
        return jdbcTemplate.queryForObject(sql, Integer.class,params.toArray());
    }


    @Override  //分页查询
    public List<e_s> findBypage(int start, int pageSize, String eidStr,String sdate) {
        String sql=" select *from e_s3118004920liangweiyuan where 1 = 1  "; //sql查询语句
        StringBuilder stringBuilder = new StringBuilder(sql);
        List params=new ArrayList();
        if (eidStr!=null&&eidStr.length()>0){ //员工eid非空,添加查询条件
            int eid=Integer.parseInt(eidStr);
            stringBuilder.append(" and eid = ? ");
            params.add(eid);
        }
        if (sdate!=null&&sdate.length()>0){ //日期非空,添加查询条件
            stringBuilder.append(" and sdate =? ");
            params.add(sdate);
        }
        stringBuilder.append(" limit ? , ? "); //分页查询条件
        params.add(start);
        params.add(pageSize);

        sql=stringBuilder.toString();
        List<e_s> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<e_s>(e_s.class), params.toArray());
        return query;
    }

    @Override //根据eid和sdate删除工资信息
    public void deleteOne(int i,String sdate) {
        String sql="delete from salary3118004920liangweiyuan where eid = ? and sdate = ? " ;
        jdbcTemplate.update(sql, i,sdate);
    }

    @Override //查询员工某个日期的工资信息
    public salary findSalary(int eid, String sdate) {
        salary sa=null;
        try {
            String sql="select *from salary3118004920liangweiyuan where eid=? and sdate=?";
            sa=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<salary>(salary.class),eid,sdate );
        } catch (Exception e) {

        }
        return sa;
    }

    @Override //添加员工信息
    public void addSalary(salary salarys) {
        String sql="insert into salary3118004920liangweiyuan(eid,sdate,basesalary,welfare,overtime,overtimepay,totalsalary) values(?,?,?,?,?,?,?)";
        //参数的输入
        jdbcTemplate.update(sql,
                salarys.getEid(),
                salarys.getSdate(),
                salarys.getBasesalary(),
                salarys.getWelfare(),
                salarys.getOvertime(),
                salarys.getOvertimepay(),
                salarys.getTotalsalary()
                );
    }

    @Override //查询所有工资信息
    public List<e_s> selectAll() {
        String sql="select * from e_s3118004920liangweiyuan ";
        List<e_s> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<e_s>(e_s.class));
        return query;
    }

    @Override //修改工资信息
    public void update(salary salarys) {
        String sql="update salary3118004920liangweiyuan set " +
                "basesalary = ? , " +
                "welfare = ? , " +
                "overtime = ? , " +
                "overtimepay = ? , " +
                "totalsalary = ?  where eid = ?  and  sdate = ? ";
        jdbcTemplate.update(sql,
                salarys.getBasesalary(),
                salarys.getWelfare(),
                salarys.getOvertime(),
                salarys.getOvertimepay(),
                salarys.getTotalsalary(),
                salarys.getEid(),
                salarys.getSdate()
               );
    }
}
