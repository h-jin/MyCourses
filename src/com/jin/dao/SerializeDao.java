package com.jin.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class SerializeDao {
	
	static final String WRITE_OBJECT_SQL = "INSERT INTO java_objects(name, object_value) VALUES (?, ?)";


	  public static Connection getConnection() throws Exception {
	    String driver = "org.gjt.mm.mysql.Driver";
	    String url = "jdbc:mysql://localhost/databaseName";
	    String username = "root";
	    String password = "root";
	    Class.forName(driver);
	    Connection conn = DriverManager.getConnection(url, username, password);
	    return conn;
	  }

	  public static long writeJavaObject(Connection conn, Object object) throws Exception {
	    String className = object.getClass().getName();
	    PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL);

	    // set input parameters
	    pstmt.setString(1, className);
	    pstmt.setObject(2, object);
	    pstmt.executeUpdate();

	    // get the generated key for the id
	    ResultSet rs = pstmt.getGeneratedKeys();
	    int id = -1;
	    if (rs.next()) {
	      id = rs.getInt(1);
	    }

	    rs.close();
	    pstmt.close();
	    return id;
	  }

	  
	 

}
