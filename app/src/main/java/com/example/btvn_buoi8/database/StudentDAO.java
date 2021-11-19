package com.example.btvn_buoi8.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btvn_buoi8.Student;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    void insertStudent(Student student);

    @Query("SELECT * FROM Student")
    List<Student> getListStudent();

    @Update
    void updateStudent(Student student);

    @Delete
    void deleteStudent(Student student);
}
