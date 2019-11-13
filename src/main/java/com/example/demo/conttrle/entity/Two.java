package com.example.demo.conttrle.entity;

import java.util.List;

public class Two {


    private String unitname;
    private int unitnum;
    private String success;
    private int id;
    private int belong;
    private int allgrade;
    private List<Problem> problems;
    public Two(String unitname1,int unitnum1,int id1,int belong1,int allgrade1,String success1) {
        unitname=unitname1;
        unitnum=unitnum1;
        id=id1;
        allgrade=allgrade1;
        belong=belong1;
        success=success1;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public int getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(int unitnum) {
        this.unitnum = unitnum;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBelong() {
        return belong;
    }

    public void setBelong(int belong) {
        this.belong = belong;
    }

    public int getAllgrade() {
        return allgrade;
    }

    public void setAllgrade(int allgrade) {
        this.allgrade = allgrade;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }
}