package ajaxExample.ajaxExample1;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.IFilterStateLocator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

@SuppressWarnings("serial")
public class UserDataProvider extends SortableDataProvider<User, String> implements IFilterStateLocator<UserFilter>{
	private UserFilter uf = new UserFilter();
	
	public UserDataProvider() throws ClassNotFoundException, SQLException
	{
		setSort("id", SortOrder.ASCENDING);
	}
	
	protected UserDataBase getUserDB() throws ClassNotFoundException, SQLException
	{
		UserDataBase ub = new UserDataBase();
		return ub;
	}
	@Override
	public Iterator<User> iterator(long first, long count)
	{
		List<User> contactsFound = null;
		try {
			contactsFound = getUserDB().getIndex(getSort());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filterUser(contactsFound).subList((int)first, (int)(first + count)).iterator();
	}

	private List<User> filterUser(List<User> contactsFound)
	{
	    ArrayList<User> result = new ArrayList<>();
	    int idFrom = uf.getIdFrom();
	    int idTo = uf.getIdTo();
	    if((idTo==0 && idFrom==0) || idTo<idFrom)
	    {
	    	for (User contact : contactsFound)
		    {
	    		result.add(contact);
		    }
	    }
	    else
	    {
	    	for (User contact : contactsFound)
		    {
		    	int id = contact.getId();
			
				if(id>=idFrom && id<=idTo && idFrom>=0 &&  idTo>=0)
				{
					result.add(contact);
				}
				
				
		    }
	    }
	    return result;
	}

	@Override
	public long size()
	{
		try {
			return filterUser(getUserDB().getIndex(getSort())).size();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public IModel<User> model(User object)
	{
		return new DetachableUserModel(object);
	}

	@Override
	public UserFilter getFilterState()
	{
	    return uf;
	}

	@Override
	public void setFilterState(UserFilter state)
	{
	    uf  = state;
	}

}
