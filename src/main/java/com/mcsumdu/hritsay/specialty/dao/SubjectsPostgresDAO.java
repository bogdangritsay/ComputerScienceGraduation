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

    public void addSubject(String title) {
        connect();
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO SUBJECTS(TITLE)" +
                            "VALUES (?)"
            );
            statement.setString(1, title);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void deleteSubject(int id) {
        connect();
        try {
            statement = connection.prepareStatement("DELETE FROM SUBJECTS WHERE SUBJECT_ID = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
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
