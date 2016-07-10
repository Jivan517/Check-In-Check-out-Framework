/**
 * 
 */
package cs525.project.fujframework.core.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cs525.project.fujframework.utils.ConfigProperties;
import cs525.project.fujframework.utils.ConfigPropertiesImpl;

/**
 * @author Fish
 *
 */
public class DbActionImpl implements DbAction {
	SimpleConnectionPool connection = SimpleConnectionPool.getInstance();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.dataaccess.DbAction#Create(java.lang.
	 * String)
	 */
	@Override
	public int Create(String query) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		int countRecord = 0;
		try {

			con = connection.getConnection();
			ps = con.prepareStatement(query);

			countRecord = ps.executeUpdate();
		} catch (Exception e) {

		} finally {
			cleanupResources(ps, con);
		}
		return countRecord;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cs525.project.fujframework.core.dataaccess.DbAction#read(java.lang.
	 * String)
	 */
	@Override
	public Object read(String query) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			con = connection.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
		} catch (Exception e) {
			// false;
		} finally {
			cleanupResources(ps, con);
		}

		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.dataaccess.DbAction#delete(java.lang.
	 * String)
	 */
	@Override
	public int delete(String query) {
		PreparedStatement ps = null;
		Connection con = null;
		int rs;
		int countRecord = 0;
		try {

			con = connection.getConnection();
			ps = con.prepareStatement(query);

			rs = ps.executeUpdate();
		} catch (Exception e) {

		} finally {
			cleanupResources(ps, con);
		}
		return countRecord;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cs525.project.fujframework.core.dataaccess.DbAction#update(java.lang.
	 * String)
	 */
	@Override
	public int update(String query) {
		Connection con = null;
		PreparedStatement ps = null;
		int rs;
		int recordCounter = 0;
		try {

			con = connection.getConnection();
			ps = con.prepareStatement(query);

			recordCounter = ps.executeUpdate();
		} catch (Exception e) {

			return recordCounter;
		} finally {
			cleanupResources(ps, con);
		}
		return recordCounter;
	}

	private void cleanupResources(PreparedStatement ps, Connection con) {
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
