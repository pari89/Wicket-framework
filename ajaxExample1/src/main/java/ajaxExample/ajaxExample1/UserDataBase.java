package ajaxExample.ajaxExample1;

import java.sql.*;
import java.util.*;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

public class UserDataBase {
	Map<Integer,User> data = new HashMap<>();
	List<User> idDes = new ArrayList<>();
	List<User> idAs = new ArrayList<>();
	List<User> nameDes = new ArrayList<>();
	List<User> nameAs = new ArrayList<>();
	public Connection connectDB() throws ClassNotFoundException, SQLException
	{
		String un = "root";
		String p = "$riNamu89";
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url= "jdbc:mysql://localhost:3306/userdb";
		Connection con = DriverManager.getConnection(url, un, p);
		return con;
	}
	
	public UserDataBase() throws ClassNotFoundException, SQLException
	{
		Statement st = connectDB().createStatement();
		ResultSet rs = st.executeQuery("select * from user");
		while(rs.next())
		{
			User u = new User();
			int id = rs.getInt(1);
			u.setId(id);
			u.setName(rs.getString(2));
			u.setEmail(rs.getString(3));
			u.setPhno(rs.getLong(4));
			data.put(id, u);
			idDes.add(u);
			idAs.add(u);
			nameDes.add(u);
			nameAs.add(u);
		}
		UpdateSort();
	}
	public User get(int id)
	{
		User c = data.get(id);
		if (c == null)
		{
			throw new RuntimeException("contact with id [" + id + "] not found in the database");
		}
		return c;
	}
	public int getCount()
	{
		return idDes.size();
	}
	public List<User> getIndex(SortParam<?> sort)
	{
		if (sort == null)
		{
			return idAs;
		}

		if (sort.getProperty().equals("id"))
		{
			return sort.isAscending() ?  idAs: idDes;
		}
		else if (sort.getProperty().equals("name"))
		{
			return sort.isAscending() ? nameAs : nameDes;
		}
		throw new RuntimeException("unknown sort option [" + sort +
			"]. valid fields: [firstName], [lastName]");
	}
	public void UpdateSort()
	{
		Collections.sort(idAs, (arg0, arg1) -> (arg0).getId()-(arg1).getId());
		Collections.sort(idDes, (arg0, arg1) -> (arg1).getId()-(arg0).getId());
		Collections.sort(nameDes, (arg0, arg1) -> (arg1).getName().compareTo((arg0).getName()));
		Collections.sort(nameAs, (arg0, arg1) -> (arg0).getName().compareTo((arg1).getName()));
	}
}
