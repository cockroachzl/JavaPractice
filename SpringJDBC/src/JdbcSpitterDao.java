import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class JdbcSpitterDao implements SpitterDao {
	@Autowired
	private DataSource dataSource;

	private static final String SQL_INSERT_SPITTER = 
			"insert into spitter (username, password, fullname, email, update_by_email ) values (?, ?, ?, ?, ? )";

	private static final String SQL_UPDATE_SPITTER = 
			"update spitter set username = ?, password = ?, fullname = ?"
					+ "where id = ?";

	private static final String SQL_SELECT_SPITTER = 
			"select id, username, fullname from PUBLIC.SPITTER where id = ?";
	
	public Boolean isSpitterCreated() {
		try( Connection conn = dataSource.getConnection(); ) {
			DatabaseMetaData dbMetaData = conn.getMetaData();
			ResultSet tables = dbMetaData.getTables(null, null, "SPITTER", null);
			if(tables.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createSpitterTable() {

		try( Connection conn = dataSource.getConnection();
				Statement stat = conn.createStatement();) {
			stat.executeUpdate("create table spitter ( id identity, username varchar(25) not null," +
					"password varchar(25) not null,fullname varchar(100) not null," +
					"email varchar(50) not null,update_by_email boolean not null);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
			 
	}

	public void addSpitter(Spitter spitter) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection(); 

			stmt = conn.prepareStatement(SQL_INSERT_SPITTER); 

			stmt.setString(1, spitter.getUsername());
			stmt.setString(2, spitter.getPassword());
			stmt.setString(3, spitter.getFullName());
			stmt.setString(4, spitter.getUsername() + "@gmail.com");
			stmt.setBoolean(5, true);

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	public Spitter getSpitterById(long id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(SQL_SELECT_SPITTER);

			stmt.setLong(1, id);

			rs = stmt.executeQuery();

			Spitter spitter = null;
			if (rs.next()) {
				spitter = new Spitter();
				spitter.setId(rs.getLong("id"));
				spitter.setUsername(rs.getString("username"));
				spitter.setPassword(rs.getString("password"));
				spitter.setFullName(rs.getString("fullname"));
			}
			return spitter;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {}
			}

			if(stmt != null) {
				try {
					stmt.close();
				} catch(SQLException e) {}
			}

			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {}
			}
		}

		return null;
	}

	public void saveSpitter(Spitter spitter) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = dataSource.getConnection();

			stmt = conn.prepareStatement(SQL_UPDATE_SPITTER);

			stmt.setString(1, spitter.getUsername());
			stmt.setString(2, spitter.getPassword());
			stmt.setString(3, spitter.getFullName());
			stmt.setLong(4, spitter.getId());

			stmt.execute();
		} catch (SQLException e) {

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

			}
		}
	}



	public List<Spittle> getRecentSpittle() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveSpittle(Spittle spittle) {
		// TODO Auto-generated method stub

	}

	public List<Spittle> getSpittlesForSpitter(
			Spitter spitter) {
		// TODO Auto-generated method stub
		return null;
	}


	public Spitter getSpitterByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteSpittle(long id) {
		throw new UnsupportedOperationException();
	}

	public Spittle getSpittleById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Spitter> findAllSpitters() {
		ArrayList<Spitter> spitters = new ArrayList<Spitter>();
		try(Connection conn = dataSource.getConnection();
				Statement stmt = conn.createStatement();){
			final String SELECT_ALL_SPIITER = "SELECT * FROM SPITTER";
			try (ResultSet rs = stmt.executeQuery(SELECT_ALL_SPIITER);) {
				while (rs.next()) {
					Spitter spitter = new Spitter();
					spitter.setUsername(rs.getString(2));
					spitter.setPassword(rs.getString(3));
					spitter.setFullName(rs.getString(4));
					spitter.setEmail(rs.getString(5));
					spitter.setUpdateByEmail(rs.getBoolean(6));
					spitters.add(spitter);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spitters;
	}

}
