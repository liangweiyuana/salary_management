package cn.lwy.salary_management.dao.impl;

import cn.lwy.salary_management.dao.employeeDao;
import cn.lwy.salary_management.domain.e_d_j;
import cn.lwy.salary_management.domain.employee;
import cn.lwy.salary_management.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class employeeDaoImpl implements employeeDao {
   private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource()); //获取jbdc连接池
    @Override //求总页数
    public int findTotalCount(String ename) {
        String sql=" select count(*) from e_d_j3118004920liangweiyuan where 1 = 1 ";
        StringBuilder strb=new StringBuilder(sql);
        List params = new ArrayList();
        if (ename!=null&&ename.length()>0){
            strb.append(" and ename like ? ");
            params.add("%"+ename+"%");  //根据关键字模糊查询
        }
        sql=strb.toString();
        return jdbcTemplate.queryForObject(sql,Integer.class,params.toArray());

    }

    @Override // 获取分页数据
    public List<e_d_j> findByPage(int start, int pageSize, String ename) {
        String sql=" select *from e_d_j3118004920liangweiyuan where 1 = 1 ";
        StringBuilder strb=new StringBuilder(sql);
        List params = new ArrayList();
        if (ename!=null&&ename.length()>0){
        strb.append(" and ename like ? ");
        params.add("%"+ename+"%");  //根据关键字模糊查询
        }
        strb.append(" limit ? ,  ? ");  //分页查询
        params.add(start);
        params.add(pageSize);
        sql=strb.toString();
        List<e_d_j> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<e_d_j>(e_d_j.class), params.toArray());
        return query;
    }

    @Override  //删除
    public void deleteOne(int i) {
        String sql="delete from employee3118004920liangweiyuan where eid = ? ";
        jdbcTemplate.update(sql, i);
    }
    @Override // 通过eid查询员工信息并返回
    public employee findByEid(int eid) {
        employee  employee1=null;
        try {
            String sql="select *from employee3118004920liangweiyuan where eid =? ";
            employee1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<employee>(employee.class), eid);
        } catch (Exception e) {
        }
        return employee1;
    }
    @Override //添加员工
    public void addEmployee(employee employees) {
        String sql="insert into employee3118004920liangweiyuan(eid,ename,eage,esex,ephone,etag,eworkyear,did,jid) values (?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                employees.getEid(),
                employees.getEname(),
                employees.getEage(),
                employees.getEsex(),
                employees.getEphone(),
                employees.getEtag(),
                employees.getEworkyear(),
                employees.getDid(),
                employees.getJid()
                );
    }
    @Override //删除员工
    public void deleteSalaryByeid(int eid) {
        String sql="delete from salary3118004920liangweiyuan where eid = ? ";
        jdbcTemplate.update(sql, eid);
    }
    @Override //查询所有员工
    public List<e_d_j> selecltAll() {
        String sql="select *from e_d_j3118004920liangweiyuan";
        List<e_d_j> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<e_d_j>(e_d_j.class));
        return query;
    }
    @Override  //修改员工信息
    public void update(employee employees) {
        String sql="update employee3118004920liangweiyuan set " +
                "ename = ? , " +
                "eage = ? , " +
                "esex = ? , " +
                "ephone = ? , " +
                "etag = ? , " +
                "eworkyear = ? ," +
                "did = ? ," +
                "jid = ? where eid= ?";
        jdbcTemplate.update(sql,
                employees.getEname(),
                employees.getEage(),
                employees.getEsex(),
                employees.getEphone(),
                employees.getEtag(),
                employees.getEworkyear(),
                employees.getDid(),
                employees.getJid(),
                employees.getEid());
    }

}
