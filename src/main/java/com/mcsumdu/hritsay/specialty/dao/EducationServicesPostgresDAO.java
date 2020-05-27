package com.mcsumdu.hritsay.specialty.dao;

import com.mcsumdu.hritsay.specialty.models.*;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EducationServicesPostgresDAO extends PostgresDAOConnection {

    public List<EducationService> getAllServices() {
        connect();
        List<EducationService> services = new ArrayList<>();
        try {
            statement = connection.prepareStatement(
                    "select s.service_id as SERVICE_ID,\n" +
                            "       s.title as SERVICE_TITLE,\n" +
                            "       s.description as SERVICE_DESC,\n" +
                            "       sb.subject_id as SUBJECT_ID,\n" +
                            "       sb.title as SUBJECT_TITLE,\n" +
                            "       u.url_id as URL_ID,\n" +
                            "       u.url as URL,\n" +
                            "       u.type as TYPE,\n" +
                            "       g.group_id as GROUP_ID,\n" +
                            "       g.title as GROUP_TITLE,\n" +
                            "       e.id_educator as EDUCATOR_ID,\n" +
                            "       e.name as EDUCATOR_NAME,\n" +
                            "       e.surname as EDUCATOR_SURNAME,\n" +
                            "       e.patronymic as EDUCATOR_PATRONYMIC\n" +
                            "from services s, subjects sb, urls u, groups g, educators_subject es, educators e\n" +
                            "where s.subject_id=sb.subject_id\n" +
                            "and s.group_id=g.group_id\n" +
                            "and s.url_service_id=u.url_id\n" +
                            "and es.educators_id_educator = e.id_educator\n" +
                            "and es.subjects_subject_id = sb.subject_id"
                    );
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                services.add(parseEducService(resultSet));
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */

        } finally {
            disconnect();
        }
        return services;
    }

    public List<EducationService> getServicesByGroupId(int groupId) {
        connect();
        List<EducationService> services = new ArrayList<>();
        try {
            statement = connection.prepareStatement(
                    "select s.service_id as SERVICE_ID,\n" +
                            "       s.title as SERVICE_TITLE,\n" +
                            "       s.description as SERVICE_DESC,\n" +
                            "       sb.subject_id as SUBJECT_ID,\n" +
                            "       sb.title as SUBJECT_TITLE,\n" +
                            "       u.url_id as URL_ID,\n" +
                            "       u.url as URL,\n" +
                            "       u.type as TYPE,\n" +
                            "       g.group_id as GROUP_ID,\n" +
                            "       g.title as GROUP_TITLE,\n" +
                            "       e.id_educator as EDUCATOR_ID,\n" +
                            "       e.name as EDUCATOR_NAME,\n" +
                            "       e.surname as EDUCATOR_SURNAME,\n" +
                            "       e.patronymic as EDUCATOR_PATRONYMIC\n" +
                            "from services s, subjects sb, urls u, groups g, educators_subject es, educators e\n" +
                            "where s.subject_id=sb.subject_id\n" +
                            "and s.group_id=g.group_id\n" +
                            "and s.url_service_id=u.url_id\n" +
                            "and es.educators_id_educator = e.id_educator\n" +
                            "and es.subjects_subject_id = sb.subject_id\n" +
                            "and g.group_id = ?"
            );
            statement.setInt(1, groupId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                services.add(parseEducService(resultSet));
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */

        } finally {
            disconnect();
        }
        return services;
    }


    private EducationService parseEducService(ResultSet resultSet) {
        EducationService service = null;
        Subject subject;
        UrlAddress urlAddress;
        Group group;
        Educator educator;
        try {
            int serviceId = resultSet.getInt("SERVICE_ID");
            String title = resultSet.getString("SERVICE_TITLE");
            String serviceDesc = resultSet.getString("SERVICE_DESC");

            int subjectId = resultSet.getInt("SUBJECT_ID");
            String subjectTitle = resultSet.getString("SUBJECT_TITLE");
            subject = new Subject(subjectId, subjectTitle);

            int urlId = resultSet.getInt("URL_ID");
            String url = resultSet.getString("URL");
            String type = resultSet.getString("TYPE");
            urlAddress = new UrlAddress(urlId, url, type);


            int groupId = resultSet.getInt("GROUP_ID");
            String groupTitle = resultSet.getString("GROUP_TITLE");
            group = new Group(groupId, groupTitle);

            int educatorId = resultSet.getInt("EDUCATOR_ID");
            String name = resultSet.getString("EDUCATOR_NAME");
            String surname = resultSet.getString("EDUCATOR_SURNAME");
            String patronymic = resultSet.getString("EDUCATOR_PATRONYMIC");
            educator = new Educator();
            educator.setEducatorId(educatorId);
            educator.setName(name);
            educator.setSurname(surname);
            educator.setPatronymic(patronymic);
        service = new EducationService(serviceId, title, serviceDesc, subject, urlAddress, group, educator);
        } catch (SQLException e) {
            /*
            LOGS
             */
        }
        return service;
    }
}
