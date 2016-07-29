package utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 * 
 * Example: <br />
 * <pre>
* <b>//Connecting to mysql database</b>
* DBConnector conector = DBConnector.getInstance();
* conector.setUpConnection("jdbc:mysql://localhost:3306/phonebook", "root", "");
* Connection con = DBConnector.getInstance().connect();	  
* 
* <b>//Preaparing query</b>
* String sql = "SELECT * FROM users ORDER BY id ASC";
*	  
* <b>//Execute query and get result</b>
* List<Map<String, Object>> result = con.createQuery(sql)
*	 .executeAndFetchTable()
*	 .asList(); 
*</pre>
 */
public class DBConnector {	
	private String url;
	private String username;
	private String password;
	
	private Sql2o sql2o;	
	private static volatile DBConnector instance;
	
	private DBConnector(){}
	
	public static DBConnector getInstance(){
		if(instance == null){
			synchronized(DBConnector.class){
				if(instance == null){
					instance = new DBConnector();
				}
			}
		}
		return instance;
	}	
	
	public Connection connect() throws Exception{
		if(sql2o == null){
			sql2o = new Sql2o(url, username, password);
		}
		Class.forName("com.mysql.jdbc.Driver");
		return sql2o.open();
	}
	
	/** 
	 * Examples for <b>url</b> param:<br />
	 * <ul>
	 * <li>MySQL <b>jdbc:mysql://hostname/databaseName</b>	[com.mysql.jdbc.Driver]	 </li>
	 * <li>ORACLE <b>jdbc:oracle:thin:@hostname:port Number:databaseName</b>	[oracle.jdbc.driver.OracleDriver]	</li>
     * <li>DB2 <b>jdbc:db2:hostname:port Number/databaseName</b>		[com.ibm.db2.jdbc.net.DB2Driver]	</li>
     * <li>Sybase <b>jdbc:sybase:Tds:hostname: port Number/databaseName</b>	[com.sybase.jdbc.SybDriver]	</li>
     * </ul>
	 * @param url
	 * @param username
	 * @param password
	 * @return <a href="">org.sql2o.Connection</a> instance
	 */
	public DBConnector setUpConnection(
			String url, 
			String username, 
			String password){		
		
		this.url = url;
		this.username = username;
		this.password = password;
		return this;
	}
}
