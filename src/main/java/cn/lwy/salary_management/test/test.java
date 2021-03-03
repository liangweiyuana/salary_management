package cn.lwy.salary_management.test;

import cn.lwy.salary_management.domain.PageBean;
import cn.lwy.salary_management.domain.e_d_j;
import cn.lwy.salary_management.service.employeeService;
import cn.lwy.salary_management.service.impl.employeeServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    @Test
    public void test1(){
        employeeService employeeService=new employeeServiceImpl();
        PageBean<e_d_j> e_d_jPageBean = employeeService.pageQuery(1, 7, null);
        System.out.println(e_d_jPageBean);
    }
    @Test
    public void test2(){

            // 内容
            String value = "[\"111\",\"22\"]";
            // 匹配规则
        String[] split = value.split("\"");
        List <Integer> list=new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
            System.out.println(split[i]+":"+i);
            if (i%2!=0){
                list.add(Integer.valueOf(split[i]));
            }
        }
        System.out.println(list);

        String reg = "_\"(.*?)\"";
            Pattern pattern = Pattern.compile(reg);
            // 内容 与 匹配规则 的测试
            Matcher matcher = pattern.matcher(value);
            if( matcher.find() ){
                // 包含前后的两个字符
                System.out.println(matcher.group());
                // 不包含前后的两个字符
                System.out.println( matcher.group(1) );
            }else{
                System.out.println(" 没有匹配到内容....");
            }


    }
}
