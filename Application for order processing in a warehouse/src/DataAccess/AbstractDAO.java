package DataAccess;

import java.beans.*;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author andre
 * Aceasta clasa descrie operatiile care pot fi exectate la nivelul unei tabele din baza de date
 * utilizand genericele (parametrul T) si implicit reflexia in acest sens. 
 * @param <T>
 */

public class AbstractDAO<T>{
		protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
		
		private final Class<T> type;
		
		@SuppressWarnings("unchecked")
		public AbstractDAO() {
			this.type = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		
		private String createFindQuerry(String field) {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM " + type.getSimpleName() + " WHERE " + field + "=?;");
			return sb.toString();
		}
		
		private String createAddQuerry(T object) throws IllegalArgumentException, IllegalAccessException {
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO " + type.getSimpleName() + " VALUES (");
			for(Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				sb.append("'" + field.get(object) + "',");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append(");");
			return sb.toString();
		}
		
		private String createViewAllQuerry() {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * FROM " + type.getSimpleName() + ";");
			return sb.toString();
		}
		
		private String createDeleteQuerry(String field) {
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM " + type.getSimpleName() + " WHERE " + field + "=?");
			return sb.toString();
		}
		
		private String createUpdateQuerry(String field, T object) throws IllegalArgumentException, IllegalAccessException {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE " + type.getSimpleName() + " SET ");
			for(Field fields : object.getClass().getDeclaredFields()) {
				if(!(fields.getName().equals(field))) {			
					fields.setAccessible(true);
					if(fields.get(object).toString().equals("")==false) {
						sb.append(fields.getName() + " = '" + fields.get(object) + "', ");
					}
				}
			}
			sb.delete(sb.length()-2, sb.length());
			sb.append(" WHERE " + field + " = ?;");
			return sb.toString();
		}
		
		/**
		 * Deschide conexiunea cu baza de date, adauga in tabela o noua linie uxecutand query-ul generat 
		 * in metoda privata createAddQuerry() in final inchizand conexiunile. 
		 * Argumentul T specifica un obiect de tipul tabelei.
		 * @param object un obiect de tipul tabelei 
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public void add(T object) throws IllegalArgumentException, IllegalAccessException {
			Connection connection = null;
			PreparedStatement  statement = null;
			String query = createAddQuerry(object);
			try {
				connection = ConnectionFactory.getConnection();
				statement = (PreparedStatement) connection.prepareStatement(query);
				statement.executeUpdate();
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:add " + e.getMessage());
			} finally {
				ConnectionFactory.closeConnectionAndStatement(connection, statement);
			}
		}
		
		
		/**
		 * Deschide conexiunea cu baza da date, sterge din tabela linia corespunzatoare 
		 * coloanei @param field cu valoarea @param id si in final inchide toate conexiunile
		 * pe care le-a realizat
		 * @param field coloana care va descrie conditia operatiei de stergere 
		 * @param id valoarea care va descrie conditia operatiei de stergere 
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public void delete(String field, int id) throws IllegalArgumentException, IllegalAccessException {
			Connection connection = null;
			PreparedStatement statement = null;
			String query = createDeleteQuerry(field);
			try {
				connection = ConnectionFactory.getConnection();
				statement = (PreparedStatement) connection.prepareStatement(query);
 				statement.setInt(1, id);
				statement.executeUpdate();
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
			} finally {
				ConnectionFactory.closeConnectionAndStatement(connection, statement);
			}
		}
		
		
		/**
		 * Deschide conexiunea cu baza de date, actualizeaza o linie din tabela cu valorile 
		 * atributelor obiectului generic "object" si in final inchide toate conexiunile pe
		 * care le-a creat
		 * @param id valoarea care va descrie conditia operatiei de update
		 * @param object noile valori care vor fi asignate liniei determinate de @param id
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public void edit(int id, T object) throws IllegalArgumentException, IllegalAccessException {
			Connection connection = null;
			PreparedStatement statement = null;
			String query = createUpdateQuerry("id", object);
			try {
				connection = ConnectionFactory.getConnection();
				statement = (PreparedStatement) connection.prepareStatement(query);
				statement.setInt(1, id);
				statement.executeUpdate();
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:edit " + e.getMessage());
			} finally {
				ConnectionFactory.closeConnectionAndStatement(connection, statement);
			}
		}
		
		
		/**
		 * Deschide conexiunea cu baza de date, creeaza o lista cu obiecte de tip T care vor
		 * defini o tabela si in final inchied toate conexiunile pe care le-a creat
		 * @return o lista de obiecte de tip T care definesc tabela
		 */
		public List<T> viewAll() {
			Connection connection = null;
			PreparedStatement  statement = null;
			ResultSet resultSet = null;
			String query = createViewAllQuerry();
			try {
				connection = ConnectionFactory.getConnection();
				statement = (PreparedStatement) connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				return createObjects(resultSet);
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:viewAll " + e.getMessage());
			} finally {
				ConnectionFactory.closeAllConnections(connection, statement, resultSet);
			}
			return null;
		}
		
		/**
		 * Metoada primeste ca parametru o lista de obiecte si creeaza o tabela prin intermediul
		 * acesteia. Intai este creat antetul tebelului(numele coloanelor), dupa aceeea urmand a 
		 * fi populata si tabela.
		 * @param objects
		 * @return Jtabel-ul descris de lista de obiecte din tip T din parametrul metodei
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 */
		public JTable createTable(List<T> objects) throws IllegalArgumentException, IllegalAccessException {
			ArrayList<String> collumnNamesArrayList = new ArrayList<String>(); 
			for(Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				collumnNamesArrayList.add(field.getName());
			}
			String[] columnNames = new String[collumnNamesArrayList.size()];
			columnNames = collumnNamesArrayList.toArray(columnNames);
			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
			for(Iterator<T> i = objects.iterator(); i.hasNext();) {
				T object = i.next();
				ArrayList<Object> columnDataAsArrayList = new ArrayList<Object>();
				for(Field field : object.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					columnDataAsArrayList.add(field.get(object));
				}
				Object[] columnDataAsArray = new Object[columnDataAsArrayList.size()];
				columnDataAsArray = columnDataAsArrayList.toArray(columnDataAsArray);
				tableModel.addRow(columnDataAsArray);
				}
			return new JTable(tableModel);
		}

		private List<T> createObjects(ResultSet resultSet){
			List<T> list = new ArrayList<T>();
			try {
				while(resultSet.next()) {
					T instance = type.newInstance();
					for(Field field: type.getDeclaredFields()) {
						Object value =  resultSet.getObject(field.getName());
						PropertyDescriptor propertyDescriptor = new  PropertyDescriptor(field.getName(), type);
						Method method = propertyDescriptor.getWriteMethod();
						method.invoke(instance, value);
					}
					list.add(instance);
				}
			} catch (InstantiationException | IllegalAccessException | SecurityException| IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
				e.printStackTrace();
			}
			return list;
		}

		
		/**
		 * Metoda deschide conexiunea cu baza de date, extrage din aceasta liniile sugerate de
		 * variabila query dupa care inchide toate conexiunile pe care le-a creat
		 * @param field coloana care va descrie conditia operatiei de cautare
		 * @param value valoarea de tip int care va descrie conditia operatiei de cautare
		 * @return o lista de obiecte de tip T care va reprezenta elementele tabelei
		 */
		public List<T> findByIntegerFieldValue(String field, int value) {
			Connection connection = null;
			PreparedStatement  statement = null;
			ResultSet resultSet = null;
			String query = createFindQuerry(field);
			try {
				connection = ConnectionFactory.getConnection();
				statement = (PreparedStatement) connection.prepareStatement(query);
				statement.setInt(1, value);
				resultSet = statement.executeQuery();
				return createObjects(resultSet);
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
			} finally {
				ConnectionFactory.closeAllConnections(connection, statement, resultSet);
			}
			return null;
		}
		
		
		/**
		 * Metoda deschide conexiunea cu baza de date, extrage din aceasta liniile sugerate de
		 * variabila query dupa care inchide toate conexiunile pe care le-a creat
		 * @param field coloana care va descrie conditia operatiei de cautare
		 * @param value valoarea de tip String care va descrie conditia operatiei de cautare
		 * @return o lista de obiecte de tip T care va reprezenta elementele tabelei
		 */
		public List<T> findByStringFieldValue(String field, String value) {
			Connection connection = null;
			PreparedStatement  statement = null;
			ResultSet resultSet = null;
			String query = createFindQuerry(field);
			try {
				connection = ConnectionFactory.getConnection();
				statement = (PreparedStatement) connection.prepareStatement(query);
				statement.setString(1, value);
				resultSet = statement.executeQuery();
				return createObjects(resultSet);
			} catch(SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
			} finally {
				ConnectionFactory.closeAllConnections(connection, statement, resultSet);
			}
			return null;
		}
}