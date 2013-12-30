package test;

import java.nio.file.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class TestDB2
{
	public static void main(String args[]) throws IOException
	{
		try
		{
			runTest();
		}
		catch (SQLException ex)
		{
			for (Throwable t : ex)
				t.printStackTrace();
		}
	}

	public static void runTest() throws SQLException, IOException
	{
		try (Connection conn = getConnection())
		{
			DatabaseMetaData md = conn.getMetaData();
			System.out.println("JDBC version " + md.getJDBCMajorVersion() + "." +md.getJDBCMinorVersion());
			System.out.println("Max Connections: " + md.getMaxConnections());
			System.out.println("Max Statment: " + md.getMaxStatements());
			System.out.println("isBatchUpdates supported : " + md.supportsBatchUpdates());
			
			Statement stat = conn.createStatement();

			stat.executeUpdate("CREATE TABLE Greetings (Message CHAR(20))");
			stat.executeUpdate("INSERT INTO Greetings VALUES ('Hello, World!')");
			stat.executeUpdate("INSERT INTO Greetings VALUES ('LiuLiu:)')");

			try (ResultSet result = stat.executeQuery("SELECT * FROM Greetings"))
			{
				ResultSetMetaData rsmd = result.getMetaData();
				System.out.println("nColumns: " + rsmd.getColumnCount());
				System.out.println(rsmd.getColumnDisplaySize(1));
				System.out.println("Name: " + rsmd.getColumnName(1) + " Lable: " + rsmd.getColumnLabel(1));
				while (result.next())
					System.out.println(result.getString(1));
			}
			stat.executeUpdate("DROP TABLE Greetings");
		}
	}

	public static Connection getConnection() throws SQLException, IOException
	{
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get("res/db.properties")))
		{
			props.load(in);
		}
		String drivers = props.getProperty("jdbc.drivers");
		if (drivers != null) System.setProperty("jdbc.drivers", drivers);
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		return DriverManager.getConnection(url, username, password);
	}
}
