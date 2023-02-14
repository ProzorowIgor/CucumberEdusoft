package DataBase;

import org.junit.Assert;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static stepDefinition.Steps.institutionId;

public class DbService {
    Connection conn;

    private Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            conn = DriverManager
                    .getConnection("jdbc:sqlserver://13.77.76.226;databaseName=EDMerge;encrypt=true;trustServerCertificate=true;", "davidm", "Ramot$82");//automation,Tamar2016$
            //jdbc:sqlserver://13.77.76.226;databaseName=EDMerge;
            //jdbc:sqlserver://azure2020:1433;databaseName=ApplicationEnvironments;
            //jdbc:sqlserver://DBProd:1433;databaseName=EDMerge;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    private void getStringFromQuerry(){


    }

    public List<String[]> getStringListFromQuery(String sql, boolean allowNull) throws Exception {
        List<String[]> list = new ArrayList<String[]>();
        ResultSet rs = null;
        Statement statement = null;
        String str = null;
        int elapsedTimeInSec = 0;

        try {
            conn = getConnection(); // getConnection();
            // report.report("connected");
            if (conn.isClosed() == true) {
                //report.report("connection is closed");
            }

            outerloop: while (elapsedTimeInSec < 60) {
                statement = conn.createStatement();
                rs = statement.executeQuery(sql);

                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();

                while (rs.next()) {

                    String[] strArr = new String[columns];
                    for (int i = 1; i <= columns; i++) {
                        strArr[i - 1] = rs.getString(i);
                    }
                    list.add(strArr);
                }
                if (list.size() == 0) {
                    elapsedTimeInSec++;
                    Thread.sleep(1000);
                    list = null;
                    break outerloop;

                } else {
                    break outerloop;
                }
            }

            conn.close();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (rs == null && allowNull == false) {
                    Assert.fail("Query result is null. Query was: " + sql);
                } else if (rs == null && allowNull == true) {
                    conn.close();
                    statement.close();
                    return null;
                }

            } catch (Exception e) {
            }
            if (statement != null) {
                statement.close();
            }
            conn.close();
        }
        return list;
    }



    public List<String[]> getUsers() throws Exception {
        //String sql = "select * from users where institutionid = "+institutionId;
        String sql = "select * from users where institutionid = "+institutionId+" and username like '%Mob%' and visible = 1";
        List<String[]> users = getStringListFromQuery(sql, false);
        return users;
/*
        String str = "";
        try {
            conn = getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where institutionid = 5270006");
            while (resultSet.next()) {
                str = resultSet.getString(1);
               // System.out.println(resultSet.getString("id"));
               // System.out.println(resultSet.getString("firstName"));
               // System.out.println(resultSet.getString("lastName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            conn.close();
        }
        return str;
    }*/
    }
}
