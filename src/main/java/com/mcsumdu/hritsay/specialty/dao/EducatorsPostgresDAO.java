package com.mcsumdu.hritsay.specialty.dao;

import com.mcsumdu.hritsay.specialty.models.Educator;
import com.mcsumdu.hritsay.specialty.models.Role;
import com.mcsumdu.hritsay.specialty.models.UrlAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EducatorsPostgresDAO extends PostgresDAOConnection {
    @Autowired
    private UrlsPostgresDAO urlsPostgresDAO;


    public List<Educator> getAllEducators() {
        connect();
        List<Educator> educators = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select * from educators");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Educator educator = parseEducator(resultSet);
                educators.add(educator);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */

        } finally {
            disconnect();
        }

        for(Educator educator : educators) {
            educator.setManager(getEducatorById(educator.getManager().getEducatorId()));
            educator.setRole(getRoleById(educator.getRole().getRoleId()));
            UrlAddress urlAddress = urlsPostgresDAO.getUrlById(educator.getUrlToImage().getUrlId());
            educator.setUrlToImage(urlAddress);
        }

        return educators;
    }


    public void addEducator(Educator educator) {
        connect();
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO EDUCATORS (NAME, SURNAME, PATRONYMIC, EDUC_DESC, IMG_URL_ID, MGR_ID, ROLE_ID)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, educator.getName());
            statement.setString(2, educator.getSurname());
            statement.setString(3, educator.getPatronymic());
            statement.setString(4, educator.getDescription());
            statement.setInt(5, educator.getUrlToImage().getUrlId());
            statement.setInt(6, educator.getManager().getEducatorId());
            statement.setInt(7, educator.getRole().getRoleId());


            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void deleteEducator(int id) {
        connect();
        try {
            statement = connection.prepareStatement("DELETE FROM EDUCATORS WHERE ID_EDUCATOR = ?");
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
    }

    public void updateEducator(int id) {

    }


    private Educator parseEducator(ResultSet resultSet) {
        Educator educator = new Educator();
        try {
            //UrlsPostgresDAO urlImg = new UrlsPostgresDAO();
            UrlAddress address = new UrlAddress();
            Role role = new Role();
            Educator manager = new Educator();

            educator.setEducatorId(resultSet.getInt("ID_EDUCATOR"));
            educator.setName(resultSet.getString("NAME"));
            educator.setSurname(resultSet.getString("SURNAME"));
            educator.setPatronymic(resultSet.getString("PATRONYMIC"));
            educator.setDescription(resultSet.getString("EDUC_DESC"));

            int imgUrlId = resultSet.getInt("IMG_URL_ID");
            address.setUrlId(imgUrlId);
            educator.setUrlToImage(address);

            int mgrId = resultSet.getInt("MGR_ID");
            manager.setEducatorId(mgrId);
            educator.setManager(manager);

            int roleId = resultSet.getInt("ROLE_ID");
            role.setRoleId(roleId);
            educator.setRole(role);

        } catch (SQLException e) {
            /*
            LOGS
             */
        }

        return educator;
    }

    public Educator getEducatorById(int id) {
        connect();
        Educator educator = new Educator();
        try {

            statement = connection.prepareStatement("select *  from educators  where id_educator = ?");
            statement.setInt(1, id);
            ResultSet tmpResultSet = statement.executeQuery();
            while(tmpResultSet.next()) {
                educator = parseEducator(tmpResultSet);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }

        return educator;
    }

    /* Work with entity "Roles" */
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




}
