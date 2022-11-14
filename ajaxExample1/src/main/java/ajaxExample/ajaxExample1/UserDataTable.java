package ajaxExample.ajaxExample1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterForm;
import org.apache.wicket.extensions.markup.html.repeater.data.table.filter.FilterToolbar;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.Component;

	
@SuppressWarnings({ "serial", "unused" })
public class UserDataTable extends WebPage {

	/**
	 * constructor
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UserDataTable() throws ClassNotFoundException, SQLException
	{	
		List<IColumn<User, String>> columns = new ArrayList<>();
		columns.add(new PropertyColumn<>(new Model<>("ID"), "id","id")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public String getCssClass()
			{
				return "numeric";
			}
		});
	
		columns.add(new PropertyColumn<>(new Model<>("Name"), "name", "name"));

		columns.add(new PropertyColumn<User, String>(new Model<>("Email"), "email"));
		columns.add(new PropertyColumn<>(new Model<>("Phone"), "phno"));
				
		UserDataProvider dataProvider = new UserDataProvider();
		columns = new ArrayList<>(columns);
		
		DataTable<User, String> tableWithFilterForm = new DataTable<>("tableWithFilterForm", columns,dataProvider, 5);
		
		tableWithFilterForm.setOutputMarkupId(true);
						
		FilterForm<UserFilter> filterForm = new FilterForm<>("filterForm", dataProvider);
		
		filterForm.add(new TextField<>("idFrom", PropertyModel.of(dataProvider, "filterState.idFrom")));
		filterForm.add(new TextField<>("idTo", PropertyModel.of(dataProvider, "filterState.idTo")));
		
		add(filterForm);
		
		FilterToolbar filterToolbar = new FilterToolbar(tableWithFilterForm, filterForm);
		
		tableWithFilterForm.addTopToolbar(filterToolbar);
		tableWithFilterForm.addTopToolbar(new NavigationToolbar(tableWithFilterForm));
		tableWithFilterForm.addTopToolbar(new HeadersToolbar<>(tableWithFilterForm, dataProvider));
		filterForm.add(tableWithFilterForm);
		//add(tableWithFilterForm);
	}
}
