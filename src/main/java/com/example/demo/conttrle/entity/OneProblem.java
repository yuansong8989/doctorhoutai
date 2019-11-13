package com.example.demo.conttrle.entity;

import lombok.Data;


import java.util.List;
@Data
public class OneProblem {
    private Problem problem;
    private List<Discuss> discusses;
    public Poice poice;
    private  int discussnum;
}
