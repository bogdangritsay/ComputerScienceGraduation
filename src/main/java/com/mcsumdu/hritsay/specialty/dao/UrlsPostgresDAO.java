package com.mcsumdu.hritsay.specialty.dao;


import com.mcsumdu.hritsay.specialty.models.UrlAddress;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class UrlsPostgresDAO extends PostgresDAOConnection {


    public boolean existsUrlById(int id) {
        connect();
        boolean isExists = false;
        try {
            statement = connection.prepareStatement(
                    "select * " +
                            "from urls " +
                            "where url_id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                isExists = true;
                break;
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
        return isExists;
    }

    public UrlAddress getUrlById(int id) {
        connect();
        UrlAddress urlAddress = new UrlAddress();
        try {
            statement = connection.prepareStatement("select *  from urls  where url_id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                urlAddress.setUrlId(resultSet.getInt("URL_ID"));
                urlAddress.setUrl(resultSet.getString("URL"));
                urlAddress.setUrlType(resultSet.getString("TYPE"));
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }

        return urlAddress;
    }

    public int getUrlIdByString(String url) {
        connect();
        int id = - 1;
        try {
            statement = connection.prepareStatement("select *  from urls  where url like ?");
            statement.setString(1, url);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                id = resultSet.getInt("URL_ID");
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }

        return id;
    }

    public void addNewUrl(String url, String type) {
        connect();
        try {
            statement = connection.prepareStatement("INSERT INTO URLS(URL, TYPE) VALUES(?, ?)");
            statement.setString(1, url);
            statement.setString(2, type);
            statement.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public boolean existsUrlByString(String url) {
        connect();
        boolean isExists = false;
        try {
            statement = connection.prepareStatement(
                    "select * " +
                            "from urls " +
                            "where url like ?");
            statement.setString(1, url);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                isExists = true;
                break;
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
        return isExists;
    }

    private UrlAddress parseUrl(ResultSet resultSet) {
        UrlAddress address = null;
        try {
            int id = resultSet.getInt("URL_ID");
            String url = resultSet.getString("URL");
            String type = resultSet.getString("TYPE");
            address = new UrlAddress(url, type);
        } catch (SQLException e) {
            /*
            LOGS
             */
        }
        return address;
    }


}
