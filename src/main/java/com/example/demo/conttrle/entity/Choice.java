package com.example.demo.conttrle.entity;

import lombok.Data;

@Data
public class Choice {

    private String xuhao;
    private String xuanxiang;
    public  Choice(){

    }
    public  Choice(String xuhao1,String xuanxiang1){
        xuhao=xuhao1;
    xuanxiang=xuanxiang1;
    }
}
