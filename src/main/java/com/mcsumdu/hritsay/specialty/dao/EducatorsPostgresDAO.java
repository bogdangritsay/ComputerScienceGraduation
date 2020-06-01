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
    @Autowired
    private RolesPostgresDAO rolesPostgresDAO;


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
            educator.setRole(rolesPostgresDAO.getRoleById(educator.getRole().getRoleId()));
            UrlAddress urlAddress = urlsPostgresDAO.getUrlById(educator.getUrlToImage().getUrlId());
            educator.setUrlToImage(urlAddress);
        }

        return educators;
    }




    public void addEducator(String name, String surname, String patronymic, String description,
                            int imgUrlId, int mgrId, int roleId, List<Integer> subjectList) {
        connect();
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO EDUCATORS (NAME, SURNAME, PATRONYMIC, EDUC_DESC, IMG_URL_ID, MGR_ID, ROLE_ID)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, patronymic);
            statement.setString(4, description);
            statement.setInt(5, imgUrlId);
            statement.setInt(6, mgrId);
            statement.setInt(7, roleId);
            statement.execute();

            int educatorId = 0;
            //Adding relations for educator and subjects

            //Search educator by input data
            statement = connection.prepareStatement(
                    "SELECT ID_EDUCATOR FROM EDUCATORS WHERE " +
                            "NAME LIKE ? AND " +
                            "SURNAME LIKE ? AND " +
                            "PATRONYMIC LIKE ? AND " +
                            "EDUC_DESC LIKE ? AND " +
                            "IMG_URL_ID = ? AND  " +
                            "MGR_ID = ? AND  " +
                            "ROLE_ID  = ? "
            );

            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, patronymic);
            statement.setString(4, description);
            statement.setInt(5, imgUrlId);
            statement.setInt(6, mgrId);
            statement.setInt(7, roleId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
               educatorId=  resultSet.getInt("ID_EDUCATOR");
            }


            for (int subjectId : subjectList) {
                statement = connection.prepareStatement(
                        "INSERT INTO EDUCATORS_SUBJECT (EDUCATORS_ID_EDUCATOR, SUBJECTS_SUBJECT_ID)" +
                            "VALUES(?,?)"
                );
                statement.setInt(1, educatorId);
                statement.setInt(2, subjectId);
                statement.execute();

            }

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


        //??????????????????????????????????
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
}
