package ajaxExample.ajaxExample1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.table.AjaxFallbackDefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class Ajax extends WebPage
{
	
	public Ajax() throws ClassNotFoundException, SQLException
	{
		List<IColumn<User, String>> columns = new ArrayList<>();

		columns.add(new AbstractColumn<User, String>(Model.of("Actions"))
		{
			@Override
			public void populateItem(Item<ICellPopulator<User>> cellItem, String componentId,IModel<User> model)
			{
				cellItem.add(new Ajax1(componentId,model));
			}
		});

		columns.add(new PropertyColumn<>(Model.of("ID"), "id","id"));
		columns.add(new PropertyColumn<>(Model.of("Name"), "name","name"));
		columns.add(new PropertyColumn<>(Model.of("Email"), "email"));
		columns.add(new PropertyColumn<>(Model.of("Phone"), "phno"));
		
		add(new AjaxFallbackDefaultDataTable<>("table", columns,new UserDataProvider(), 8));
	}

}
