package DataAccess;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Aceasta clasa perminte accesul aplicatiei la baza de date cu care lucreaza 
 * continand metode de crearea, obtinere sau inchidere a conexiunilor 
 * @author andre
 *
 */
public class ConnectionFactory {
	
	//database constants
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse?autoReconnect=true&useSSL=false"; 
	private static final String USER = "root";
	private static final String PASS = "elev1Aa.";
	
	//use singleton design pattern
	private static ConnectionFactory singleInstance = new ConnectionFactory();
	
	/**
	 * Metoda obtine o instanta de obiect singleton 
	 * @return
	 */
	public static ConnectionFactory getInstance() {
		if(singleInstance==null) {
			singleInstance = new ConnectionFactory();
		}
		return singleInstance;
	}
	
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch(ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;
	}
	
	/**
	 * Aceasta clasa returneaza o conexiunea proaspat create
	 * @return o conexiune
	 */
	public static Connection getConnection() {
		Connection connection = singleInstance.createConnection();
		return connection;
	}

	/**
	 * Inchide conexiunea transmisa ca si parametru
	 * @param connection conexiunea pe care dorim sa o inchidem
	 */
	public static void close(Connection connection) {
		if(connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Inchide statement-ul transmis ca si parametru
	 * @param statement statement-ul pe care dorim sa-l inchidem
	 */
	public static void close(Statement statement) {
		if(statement!=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Inchide resultSet-ul transmis ca si parametru
	 * @param resultSet resultset-ul pe care dorim sa-l inchidem
	 */
	public static void close(ResultSet resultSet) {
		if(resultSet!=null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Inchidem conexiunea, statement-ul si resultSet-ul date ca si parametru
	 * @param connection conexiunea pe care dorim sa o inchidem
	 * @param statement statement-ul pe care  dorim sa-l inchidem
	 * @param resultSet resultSet-ul pe care dorim sa-l inchidem
	 */
	public static void closeAllConnections(Connection connection, Statement statement, ResultSet resultSet) {
		if(connection!=null && statement!=null && resultSet!=null) {
			try {
				connection.close();
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Inchidem toate conexiunea si statement-ul date ca si parametru
	 * @param connection conexiunea pe care dorim sa o inchidem
	 * @param statement statement-ul pe care  dorim sa-l inchidem
	 */
	public static void closeConnectionAndStatement(Connection connection, Statement statement) {
		if(connection!=null && statement!=null) {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
