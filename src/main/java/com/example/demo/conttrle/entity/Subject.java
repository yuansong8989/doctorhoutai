package com.example.demo.conttrle.entity;

import lombok.Data;

import java.util.List;

@Data
public class Subject {
    private List<Classify> subject1;
    private List<Classify> subject2;
    private List<Classify> subject3;
    private List<Classify> subject4;
    private List<AllZhangjie> allZhangjies;
    private List<Problem> problems;
    private List<Vedio> vedios;
}
