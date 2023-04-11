package com.example.proj_pascoa;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
     /*
    Default env:
    DATABASE_HOST=localhost;DATABASE_PORT=5432;DATABASE_NAME=tarefadb;DATABASE_USERNAME=postgres;DATABASE_PASSWORD=postgres
    */
    public static Connection getConnection() throws SQLException, URISyntaxException {
        String dbUri = "localhost";
        String dbPort = "5432";
        String dbName = "b_d_carros";

        String username = "postgres";
        String password = "postegres";
        String dbUrl = "jdbc:postgresql://" + dbUri + ':' + dbPort + "/" + dbName + "?serverTimezone=UTC";

        return DriverManager.getConnection(dbUrl, username, password);
    }

}
