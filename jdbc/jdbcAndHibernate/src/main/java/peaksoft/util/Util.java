package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String url="jdbc:postgresql://localhost:5432/postgres";
    private final static String user_name="postgres";
    private final static String password="postgres";



    public static Connection getConnection (){
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(url,user_name,password);
            System.out.println("--- connected ---");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return connection;
    }


}
