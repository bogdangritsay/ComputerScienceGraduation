package com.mcsumdu.hritsay.specialty.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mcsumdu.hritsay.specialty.models.SiteDocument;
import com.mcsumdu.hritsay.specialty.models.UrlAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducDocumentsPostgresDAO extends PostgresDAOConnection {
    @Autowired
    private UrlsPostgresDAO urlsPostgresDAO;


    public List<SiteDocument> getAllMethodicalDocuments() {
        connect();
        List<SiteDocument> documents = new ArrayList<>();
        try {
            statement = connection.prepareStatement(
                    "select s.doc_id as DOC_ID, s.title as TITLE, s.description as DESCRIPTION, u.url_id as URL_ID, u.url as URL, u.type as TYPE " +
                            "from site_documents s, urls u " +
                            "where s.url_doc_id = u.url_id "
            );
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                 SiteDocument tmpDocument = parseEducSiteDocument(resultSet);
                documents.add(tmpDocument);
            }
        } catch (SQLException e) {
            System.out.println("Error in getting documents!");
            e.printStackTrace();

        } finally {
            disconnect();
        }
        return documents;
    }

    public void addDocument(String title, String description, String url) {
        connect();
        try {
            urlsPostgresDAO.addNewUrl(url, "doc");
            int urlId = urlsPostgresDAO.getUrlIdByString(url);

            statement = connection.prepareStatement(
                    "INSERT INTO SITE_DOCUMENTS(TITLE, DESCRIPTION, URL_DOC_ID)" +
                            "VALUES (?, ?, ?)"
            );
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setInt(3, urlId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void removeDocument(int id) {
        connect();
        try {
            statement = connection.prepareStatement("DELETE FROM SITE_DOCUMENTS WHERE DOC_ID = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error in deleting document!");
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private SiteDocument parseEducSiteDocument(ResultSet resultSet) {
        SiteDocument document = null;
        try {
            int docId = resultSet.getInt("DOC_ID");
            String title = resultSet.getString("TITLE");
            String description = resultSet.getString("DESCRIPTION");
            int urlId = resultSet.getInt("URL_ID");
            String url = resultSet.getString("URL");
            String type = resultSet.getString("TYPE");
            UrlAddress address = new UrlAddress(urlId, url, type);
            document = new SiteDocument(docId, title, description, address);
        } catch (SQLException e) {
            System.out.println("Error in parsing document!");
        }
        return document;
    }
}
