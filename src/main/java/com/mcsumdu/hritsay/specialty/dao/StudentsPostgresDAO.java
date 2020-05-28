package com.mcsumdu.hritsay.specialty.dao;

import com.mcsumdu.hritsay.specialty.models.Educator;
import com.mcsumdu.hritsay.specialty.models.Group;
import com.mcsumdu.hritsay.specialty.models.News;
import com.mcsumdu.hritsay.specialty.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentsPostgresDAO extends PostgresDAOConnection {
    @Autowired
    private GroupPostgresDAO groupPostgresDAO;

    public List<Student> getAllStudentsByGroup(Group group) {
        List<Student> students = new ArrayList<>();
        int groupId = group.getId();
        connect();
        try {
            statement = connection.prepareStatement(
                    "select * from students where group_id = ?"
            );
            statement.setInt(1, groupId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                students.add(parseStudent(resultSet));
            }
        } catch (SQLException e) {
            /* LOGS*/
        } finally {
            disconnect();
        }
        for(Student student : students) {
            int groupTmp = student.getGroup().getId();
            student.setGroup(groupPostgresDAO.getGroupById(groupTmp));
        }

        return students;
    }

    private Student parseStudent(ResultSet resultSet) {
        Student student = null;
        int groupId = -1;
        try {
            int id = resultSet.getInt("ID");
            String surname = resultSet.getString("SURNAME");
            String name = resultSet.getString("NAME");
            String patronymic = resultSet.getString("PATRONYMIC");

            groupId = resultSet.getInt("GROUP_ID");
            Group groupTmp = new Group();
            groupTmp.setId(groupId);

            student = new Student(id, surname, name, patronymic, groupTmp);
        } catch (SQLException e) {
            /*LOGS*/
        }
      /*  Group group = getGroupById(groupId);
        student.setGroup(group);*/
        return student;
    }



}
