package com.example.demo.Dao;
import com.example.demo.conttrle.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface Dao {
    @Insert("INSERT INTO doctor_problem (problemtitle,analysis,success,choicea,choiceb,choicec,choiced,belong,choice) VALUES(#{problemtitle},#{analysis},#{success},#{choicea},#{choiceb},#{choicec},#{choiced},#{belong},#{choice})")
    int insertsafeuser(@Param("problemtitle") String problemtitle,@Param("analysis") String analysis,@Param("success") String success,@Param("choicea") String choicea,@Param("choiceb") String choiceb,@Param("choicec") String choicec,@Param("choiced") String choiced,@Param("belong") int belong,@Param("choice") String choice);
    List<Classify> getAllClassify(@Param("id") int id);
    List<AllZhangjie> getzhangjie(@Param("id") int id);
    AllZhangjie getoneZhangjie(@Param("unitname") String unitname);
    List<Vedio> getVedio(@Param("id") int id);
    List<Problem> getAllProblem(@Param("id") int id);
    Classify getClassify(@Param("id") int id);
    List<Discuss> getDiscuss(@Param("id") int id);
    Problem getOneProblem(@Param("id") int id);

    int  login(@Param("appuser") AppUser appUser);

}
