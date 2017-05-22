package cn.edu.wang.entity;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class Residence_act {
    private String sjly;
    private String xgsj;

    public Residence_act(String sjly, String xgsj) {
        this.sjly = sjly;
        this.xgsj = xgsj;
    }

    public Residence_act() {
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getXgsj() {
        return xgsj;
    }

    public void setXgsj(String xgsj) {
        this.xgsj = xgsj;
    }
}
