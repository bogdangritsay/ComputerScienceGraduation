package com.mcsumdu.hritsay.specialty.dao;

import com.mcsumdu.hritsay.specialty.models.Group;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GroupPostgresDAO extends PostgresDAOConnection {
    public List<Group> getAllGroups() {
        connect();
        List<Group> groups = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from groups");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                groups.add(parseGroup(resultSet));
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */

        } finally {
            disconnect();
        }
        return groups;
    }

    public Group getGroupById(int id) {
        connect();
        Group group = new Group();
        try {

            statement = connection.prepareStatement("select *  from groups  where group_id = ?");
            statement.setInt(1, id);
            ResultSet tmpResultSet = statement.executeQuery();
            while(tmpResultSet.next()) {
                group = parseGroup(tmpResultSet);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
        return group;
    }

    public Group getGroupByTitle(String title) {
        connect();
        Group group = new Group();
        try {

            statement = connection.prepareStatement("select *  from groups  where title like ?");
            statement.setString(1, title);
            ResultSet tmpResultSet = statement.executeQuery();
            while(tmpResultSet.next()) {
                group = parseGroup(tmpResultSet);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
        return group;
    }

    public Group parseGroup(ResultSet resultSet) {
        Group group = null;
        try {
            int id = resultSet.getInt("GROUP_ID");
            String title = resultSet.getString("TITLE");
            group = new Group(id, title);
        } catch (SQLException e) {
            /*LOGS*/
        }
        return group;
    }
}
