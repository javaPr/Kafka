package cn.edu.wang.entity;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class Car {
    private String cjh;
    private String sjly;
    private String fdjh;
    private String clrq;
    private String clpp;
    private String clys;

    public Car(){}

    public Car(String cjh, String sjly, String fdjh, String clrq, String clpp, String clys) {
        this.cjh = cjh;
        this.sjly = sjly;
        this.fdjh = fdjh;
        this.clrq = clrq;
        this.clpp = clpp;
        this.clys = clys;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getFdjh() {
        return fdjh;
    }

    public void setFdjh(String fdjh) {
        this.fdjh = fdjh;
    }

    public String getClrq() {
        return clrq;
    }

    public void setClrq(String clrq) {
        this.clrq = clrq;
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp;
    }

    public String getClys() {
        return clys;
    }

    public void setClys(String clys) {
        this.clys = clys;
    }
}
