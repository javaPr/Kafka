package cn.edu.wang.entity;

/**
 * Created by wangdechang on 2017/5/22.
 */
public class GeneralNode {
    private long value;
    private String label;

    public GeneralNode(){}
    public GeneralNode(long value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
