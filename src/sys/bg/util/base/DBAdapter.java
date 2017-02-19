package sys.bg.util.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBAdapter
{
	static Logger logger = LoggerFactory.getLogger(DBAdapter.class);

    /**
     * 根据数据源名从数据库连接池中获得数据库连接
     * @param strDataSource 数据源名
     * @return 数据库连接
     * @throws JDBCException
     */
    public static Connection getConnection(String strDataSource) 
           
    {
        //数据库连接
        Connection m_Connection = null;
        //数据源
        DataSource m_Datasource = null;
        Context m_Context = null;
        Hashtable m_Hashtable = null;
        try
        {
            //设置JNDI的环境变量 svn://192.168.0.201/tydk
            m_Hashtable = new Hashtable();
            strDataSource="java:comp/env/"+strDataSource;
            
//          m_Hashtable.put(Context.INITIAL_CONTEXT_FACTORY,
//                          "com.ibm.Websphere.naming.WsnlnitialContextFactory");
//            m_Hashtable.put(Context.INITIAL_CONTEXT_FACTORY,
//                            "weblogic.jndi.WLInitialContextFactory");
//            m_Hashtable.put(Context.PROVIDER_URL, "t3://localhost:7001");
            m_Context = new InitialContext(m_Hashtable);

            //从JNDI中获得数据源
            m_Datasource = (javax.sql.DataSource) m_Context.lookup(strDataSource);
            //获得数据库连接
            m_Connection = m_Datasource.getConnection();

            return m_Connection;
        } catch (Exception e)
        {
        	logger.error("获得数据库连接出错", e);

            
        }
		return m_Connection;
    }

    /**
     * 关闭指定的ResultSet
     * @param rs 指定的ResultSet
     */
    public static void close(ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            } catch (Exception e)
            {
            }
        }
    }

    /**
     * 关闭指定的Statement
     * @param stmt 指定的Statement
     */
    public static void close(Statement stmt) 
    {
        if (stmt != null)
        {
            try
            {
                stmt.close();
            } catch (Exception e)
            {
            }
        }
    }

    /**
     * 关闭指定的数据库连接
     * @param conn 指定的数据库连接
     */
    public static void close(Connection conn)
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            } catch (Exception e)
            {
            }
        }
    }

}
