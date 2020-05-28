package com.mcsumdu.hritsay.specialty.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mcsumdu.hritsay.specialty.models.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectsPostgresDAO extends PostgresDAOConnection {

    public List<Subject> getAllSubjects() {
        connect();
        List<Subject> subjects = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from subjects");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                subjects.add(parseSubject(resultSet));
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */

        } finally {
            disconnect();
        }
        return subjects;
    }

    private Subject parseSubject(ResultSet resultSet) {
        Subject subject = null;
        try {
            int id = resultSet.getInt("SUBJECT_ID");
            String title = resultSet.getString("TITLE");
            subject = new Subject(id, title);
        } catch (SQLException e) {
            /*LOGS*/
        }
        return subject;
    }
}
