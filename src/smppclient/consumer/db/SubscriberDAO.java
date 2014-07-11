package smppclient.consumer.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;

import smppclient.consumer.model.Subscriber;

public class SubscriberDAO {
	private Logger log = Logger.getLogger(getClass().getName());
	
	protected DatabaseConnection db;
	
	public SubscriberDAO() {
		db = DatabaseConnection.getInstance();
	}
	
	public Subscriber getSubscriberByMsisdn(String msisdn) {
		Subscriber sub = null;
		ResultSet rs = null;
		String strSql = "SELECT * FROM subscriber_tab WHERE msisdn = ?";
		PreparedStatement  stmt = null;
		
		try {
			stmt = this.db.getConnection().prepareStatement(strSql);
			stmt.setString(1, msisdn.trim());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				sub = new Subscriber(rs.getString("msisdn"), rs.getInt("subtype"), rs.getInt("status"));
			}
		} catch (SQLException e) {
			log.error("getSubscriberByMsisdn failed: " + e.getMessage(), e);
		} finally {
			try {
				if(rs != null) rs.close();
				if (stmt != null) stmt.close();
			} catch (SQLException ex) {
				log.error("failed to close db resources: " + ex.getMessage(), ex);
			}
		}
		
		return sub;
	}
	
	public boolean addSubscriber(Subscriber sub) {
		PreparedStatement  stmt = null;
		String strSql;
		
		try {
			
			Subscriber existingSub = getSubscriberByMsisdn(sub.getMsisdn());
			if(existingSub != null) {
				if(existingSub.getStatus() != 1) {
					strSql = "UPDATE subscriber_tab set status = 0, last_updated_date = ? WHERE msisdn = ?";
					
					stmt = this.db.getConnection().prepareStatement(strSql);
					stmt.setTimestamp(1, new Timestamp(new Date().getTime()));
					stmt.setString(2, sub.getMsisdn());
					
				} else {
					return true;
				}
			} else {
				strSql = "INSERT INTO subscriber_tab(msisdn, subtype, status, subscribed_date, next_renewal_date, created_date, last_updated_date, last_successful_charging_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
				stmt = this.db.getConnection().prepareStatement(strSql);
				
				stmt.setString(1, sub.getMsisdn());
				stmt.setInt(2, sub.getSubscriberType());
				stmt.setInt(3, sub.getStatus());
				stmt.setTimestamp(4, new Timestamp(sub.getSubscriptionDate().getTime()));
				stmt.setTimestamp(5, new Timestamp(sub.getSubscriptionDate().getTime()));
				stmt.setTimestamp(6, new Timestamp(sub.getSubscriptionDate().getTime()));
				stmt.setTimestamp(7, new Timestamp(sub.getSubscriptionDate().getTime()));
				stmt.setTimestamp(8, new Timestamp(sub.getSubscriptionDate().getTime()));
			}
			
			if(stmt.executeUpdate() > 0)
				return true;
			
		} catch (SQLException e) {
			log.error("addSubscriber failed: " + e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException ex) {
				log.error("failed to close db resources: " + ex.getMessage(), ex);
			}
		}
		
		return false;
	}
	
	public boolean removeSubscriber(Subscriber sub) {
		PreparedStatement  stmt = null;
		
		try {
			String strSql = "UPDATE subscriber_tab set status = 0, last_updated_date = ? WHERE msisdn = ?";
			stmt = this.db.getConnection().prepareStatement(strSql);
			
			stmt.setTimestamp(1, new Timestamp(new Date().getTime()));
			stmt.setString(2, sub.getMsisdn());
			
			if(stmt.executeUpdate() > 0)
				return true;
			
		} catch (SQLException e) {
			log.error("removeSubscriber failed: " + e.getMessage(), e);
		} finally {
			try {
				if (stmt != null) stmt.close();
			} catch (SQLException ex) {
				log.error("failed to close db resources: " + ex.getMessage(), ex);
			}
		}
		
		return false;
	}
}