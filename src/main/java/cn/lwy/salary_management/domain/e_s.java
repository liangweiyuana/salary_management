package cn.lwy.salary_management.domain;

public class e_s {
    private String ename;
    private int eid;
    private String sdate;
    private double basesalary;
    private double welfare;
    private  double totalsalary;
    private  double overtime;
    private  double overtimepay;

    public double getOvertime() {
        return overtime;
    }

    public void setOvertime(double overtime) {
        this.overtime = overtime;
    }

    public double getOvertimepay() {
        return overtimepay;
    }

    public void setOvertimepay(double overtimepay) {
        this.overtimepay = overtimepay;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public double getBasesalary() {
        return basesalary;
    }

    public void setBasesalary(double basesalary) {
        this.basesalary = basesalary;
    }

    public double getWelfare() {
        return welfare;
    }

    public void setWelfare(double welfare) {
        this.welfare = welfare;
    }

    public double getTotalsalary() {
        return totalsalary;
    }

    public void setTotalsalary(double totalsalary) {
        this.totalsalary = totalsalary;
    }
}
