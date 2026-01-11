package app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import pool.MyDataSource;

public class App {

	public static void main(String[] args) {
		try(Connection conn = MyDataSource.getConnection())	{
			DatabaseMetaData metaData=conn.getMetaData();
			String[] types= {"TABLES"};
			ResultSet tables= metaData.getTables(null, null, null, types);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
