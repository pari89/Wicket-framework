package ajaxExample.ajaxExample1;

import java.sql.SQLException;

import org.apache.wicket.model.LoadableDetachableModel;

@SuppressWarnings("serial")
public class DetachableUserModel extends LoadableDetachableModel<User> 
{
	private int id;

	protected UserDataBase getUserDB() throws ClassNotFoundException, SQLException
	{
		UserDataBase ub = new UserDataBase();
		return ub;
	}
	
	public DetachableUserModel(User c)
	{
		this(c.getId());
	}

	/**
	 * @param id
	 */
	public DetachableUserModel(int id)
	{
		if (id == 0)
		{
			throw new IllegalArgumentException();
		}
		this.id = id;
	}
	public boolean equals(final Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		else if (obj == null)
		{
			return false;
		}
		else if (obj instanceof DetachableUserModel)
		{
			DetachableUserModel other = (DetachableUserModel)obj;
			return other.id == id;
		}
		return false;
	}
	@Override
	protected User load() {
		// TODO Auto-generated method stub
		try {
			return getUserDB().get(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
