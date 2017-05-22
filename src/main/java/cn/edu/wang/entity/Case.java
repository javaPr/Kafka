package cn.edu.wang.entity;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class Case {
    private String ajmc;
    private String ajlb;
    private String farq;

    public Case(String ajmc, String ajlb, String farq) {
        this.ajmc = ajmc;
        this.ajlb = ajlb;
        this.farq = farq;
    }

    public Case(){}

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getAjlb() {
        return ajlb;
    }

    public void setAjlb(String ajlb) {
        this.ajlb = ajlb;
    }

    public String getFarq() {
        return farq;
    }

    public void setFarq(String farq) {
        this.farq = farq;
    }
}
