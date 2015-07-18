package com.cosplay.dataserver.db.mysql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cosplay.dataserver.db.mysql.impl.MysqlDatabaseOperation;
import com.cosplay.dataserver.exception.SQLNotOneCountException;

public class MysqlDao implements MysqlDatabaseOperation{

	@Override
	public boolean delete(Connection conn, String sqlString) throws SQLException {
		PreparedStatement pre = null;
		boolean result = false;
		try{
			pre = conn.prepareStatement(sqlString);
			result = pre.execute();
		}catch(SQLException e){
			throw e;
		}finally{
			pre.close();
		}
		return result;
	}

	@Override
	public int getCount(Connection conn, String sqlString) throws SQLException {
		PreparedStatement pre = null;
		ResultSet rs = null;
		try{
			pre=conn.prepareStatement(sqlString);
			rs = pre.executeQuery();
			if (!rs.next()) // �����
				return 0;
			return rs.getInt(1);
		}catch(SQLException e){
			throw e;
		}finally{
			rs.close();
			pre.close();
		}
	}

	@Override
	public Object[] getOneRowAsArray(Connection conn, String sqlString)throws SQLException {
		PreparedStatement pre = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd =null;
		Object[] resArray =null;
		try{
			pre = conn.prepareStatement(sqlString);
			rs = pre.executeQuery();
			rsmd = rs.getMetaData();
			resArray = new Object[rsmd.getColumnCount()];
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				resArray[i - 1] = getRsObj(i, rs, rsmd);
			}
			if (rs.next()) {
				String err = "����ҵ�����Ψһ��[" +sqlString+ "]"; 
				//log.error(err);
				throw new SQLNotOneCountException(err);
			}
		}catch(SQLException e){
			throw e;
		}finally{
			rs.close();
			pre.close();
		}
		return resArray;
	}

	@Override
	public Map<Object, Object> getOneRowAsMap(Connection conn, String sqlString)throws SQLException {
		Map<Object, Object> dataMap = new HashMap<Object, Object>();
		PreparedStatement pre = null;
		ResultSet rs =null;
		ResultSetMetaData rsmd = null;
		try{
			pre = conn.prepareStatement(sqlString);
			rs = pre.executeQuery();
			rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				dataMap.put(rsmd.getColumnName(i), getRsObj(i, rs, rsmd));
			}
		}catch(SQLException e){
			throw e;
		}finally{
			rs.close();
			pre.close();
		}
		return dataMap;
	}

	@Override
	public boolean insert(Connection conn, String sqlString) throws SQLException {
		PreparedStatement pre = null;
		boolean result = false;
		try{
			pre = conn.prepareStatement(sqlString);
			result = pre.execute();
		}catch(SQLException e){
			throw e;
		}finally{
			pre.close();
		}
		return result;
	}

	@Override
	public List<Object[]> searchAsArrayList(Connection conn, String sqlString)throws SQLException {
		PreparedStatement pre = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try{
			pre = conn.prepareStatement(sqlString);
			rs = pre.executeQuery();
			rsmd = rs.getMetaData();
			for (int i = 1;i<rsmd.getColumnCount();i++){
				
			}
		}catch(SQLException e){
			
		}finally{
			
		}
		return null;
	}

	@Override
	public List<Map<Object, Object>> searchAsMapList(Connection conn,String sqlString) throws SQLException {
		PreparedStatement pre = null;
		ResultSet rs =null;
		
		return null;
	}

	@Override
	public boolean update(Connection conn, String sqlString) throws SQLException {
		PreparedStatement pre = null;
		ResultSet rs =null;
		
		return false;
	}

	@Override
	public boolean execute(Connection conn, String sql) throws SQLException {
		PreparedStatement pre = null;
		ResultSet rs =null;
	
		return false;
	}
	
	
	
	/***************************************************************************
	 * ����ResultSet����е���ݣ�������ݿ������Զ�ת��Ϊjava����
	 * 
	 * @param index
	 * @param rs
	 * @param rsmd
	 * @return
	 * @throws SQLException
	 */
	public static final Object getRsObj(int index, ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		switch (rsmd.getColumnType(index)) {
		case Types.LONGVARBINARY:
		case Types.VARBINARY:
		case Types.BINARY:
		case Types.BLOB:
			byte[] data = rs.getBytes(index);
			if (data != null)
				return new String(data);
			else
				return null;
		case Types.SMALLINT:
		case Types.NUMERIC:
		case Types.BIGINT:
            long value = rs.getLong(index);
            if (value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE)
            {
                return new Integer(rs.getInt(index));
            }
            return new Long(value);
		case Types.DATE:
		case Types.TIMESTAMP:
			Timestamp time = rs.getTimestamp(index);
			// return time;
			if (time != null) {
				// time.getTime()����ֵΪlong
				// return time.toString();
				return new java.util.Date(time.getTime());
			} else {
				return null;
			}
		default:
			return rs.getObject(index);
		}
	}
}
