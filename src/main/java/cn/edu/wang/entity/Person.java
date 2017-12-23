package cn.edu.wang.entity;

import java.util.List;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class Person {
    private String name;
    private String mz;//名族
    private String zzmm;//政治身份
    private String csrq;//出生日期
    private String gmsfhm;
    private String whcd;//教育程度

    private GeneralNode generalNode;

    private String rel;

    private List<Car> hasCar;
    private List<Case> invovleCase;
    private List<Residence_act> actResience;


    public Person(String name, String mz, String zzmm, String csrq, String gmsfhm, String whcd) {
        this.name = name;
        this.mz = mz;
        this.zzmm = zzmm;
        this.csrq = csrq;
        this.gmsfhm = gmsfhm;
        this.whcd = whcd;
    }

    public Person(){}


    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getGmsfhm() {
        return gmsfhm;
    }

    public void setGmsfhm(String gmsfhm) {
        this.gmsfhm = gmsfhm;
    }

    public String getWhcd() {
        return whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }

    public List<Car> getHasCar() {
        return hasCar;
    }

    public void setHasCar(List<Car> hasCar) {
        this.hasCar = hasCar;
    }

    public List<Case> getInvovleCase() {
        return invovleCase;
    }

    public void setInvovleCase(List<Case> invovleCase) {
        this.invovleCase = invovleCase;
    }

    public List<Residence_act> getActResience() {
        return actResience;
    }

    public void setActResience(List<Residence_act> actResience) {
        this.actResience = actResience;
    }

    public GeneralNode getGeneralNode() {
        return generalNode;
    }

    public void setGeneralNode(GeneralNode generalNode) {
        this.generalNode = generalNode;
    }
}
