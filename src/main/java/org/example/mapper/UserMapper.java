package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.entity.Account;
import org.example.entity.Student;

import java.util.List;


public interface UserMapper {
    @Select("select * from user where username=#{username}")
    public Account SelectUserByName(String username);

    @Select("select  * from student ")
    public List<Student> getStudentList();
}
