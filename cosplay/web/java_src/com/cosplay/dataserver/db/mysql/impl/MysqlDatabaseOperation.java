package com.cosplay.dataserver.db.mysql.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/*******************************************************************************
 * �ײ���ݿ��CRUD�����Ľӿ�
 * 

 * @see
 * @since
 * @version
 */
public interface MysqlDatabaseOperation {

    /***************************************************************************
     * ɾ���¼
     */
    public boolean delete(Connection conn, String sqlString) throws Exception;

    /***************************************************************************
     * �õ���¼����
     */
    public int getCount(Connection conn, String sqlString) throws Exception;

    /***************************************************************************
     * ��ȡһ����¼�������鷽ʽ���ء�
     */
    public Object[] getOneRowAsArray(Connection conn, String sqlString) throws Exception;
    
    /***************************************************************************
     * ��ȡһ����¼����map����
     */
    public Map<Object, Object> getOneRowAsMap(Connection conn, String sqlString) throws Exception;

    /***************************************************************************
     * �����¼
     */
    public boolean insert(Connection conn, String sqlString) throws Exception;

    /***************************************************************************
     * ͨ�ò�ѯ����List[array1, array2...]��ʽ����
     */
    public List<Object[]> searchAsArrayList(Connection conn, String sqlString) throws Exception;

  
    public List<Map<Object, Object>> searchAsMapList(Connection conn,String sqlString) throws Exception;


    /***************************************************************************
     * ���±��¼�Ķ���ֶ�
     * 
     */
    public boolean update(Connection conn, String sqlString) throws Exception;
    
    /***************************************************************************
     * ִ��һ��sql���
     * 
     * @param conn
     *            ��ݿ�����
     * @param sql
     *            sql���
     * @throws Exception
     */
    public boolean execute(Connection conn, String sql) throws Exception;
}
