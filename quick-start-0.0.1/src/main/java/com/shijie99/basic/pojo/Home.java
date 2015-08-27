package com.shijie99.basic.pojo;

public class Home {
    private Integer id;

    private String homelink;

    private String homedesc;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomelink() {
        return homelink;
    }

    public void setHomelink(String homelink) {
        this.homelink = homelink == null ? null : homelink.trim();
    }

    public String getHomedesc() {
        return homedesc;
    }

    public void setHomedesc(String homedesc) {
        this.homedesc = homedesc == null ? null : homedesc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}