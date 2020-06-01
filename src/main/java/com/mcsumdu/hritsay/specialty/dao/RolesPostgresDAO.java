package com.mcsumdu.hritsay.specialty.dao;

import com.mcsumdu.hritsay.specialty.models.Role;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RolesPostgresDAO extends PostgresDAOConnection {

    public Role getRoleById(int id) {
        connect();
        Role role = new Role();
        try {
            statement = connection.prepareStatement("select *  from roles  where role_id = ?");
            statement.setInt(1, id);
            ResultSet tmpResultSet = statement.executeQuery();
            while(tmpResultSet.next()) {
                int roleId = tmpResultSet.getInt("ROLE_ID");
                String title = tmpResultSet.getString("TITLE");
                int rank = tmpResultSet.getInt("RANK");
                role = new Role(roleId, title, rank);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
        return role;
    }

    public List<Role> getAllRoles() {
        connect();
        List<Role> roles = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select *  from roles ");
            ResultSet tmpResultSet = statement.executeQuery();
            while(tmpResultSet.next()) {
                int roleId = tmpResultSet.getInt("ROLE_ID");
                String title = tmpResultSet.getString("TITLE");
                int rank = tmpResultSet.getInt("RANK");
                Role role = new Role(roleId, title, rank);
                roles.add(role);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
        return roles;
    }

    public void addRole(String title, int rank) {
        connect();
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO ROLES(TITLE, RANK)" +
                            "VALUES (?, ?)"
            );

            statement.setString(1, title);
            statement.setInt(2, rank);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void deleteRole(int id) {
        connect();
        try {
            statement = connection.prepareStatement("DELETE FROM ROLES WHERE ROLE_ID = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
    }

}
