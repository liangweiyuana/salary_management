package cn.lwy.salary_management.domain;

public class company {
    private String cname ; //公司名字
    private int cmembers;   //公司总人数
    private String caddress; //公司地址

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCmembers() {
        return cmembers;
    }

    public void setCmembers(int cmembers) {
        this.cmembers = cmembers;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }
}
