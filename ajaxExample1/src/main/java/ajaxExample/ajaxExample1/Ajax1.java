package ajaxExample.ajaxExample1;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalDialog;
import org.apache.wicket.extensions.ajax.markup.html.modal.theme.DefaultTheme;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

@SuppressWarnings("serial")
public class Ajax1 extends Panel{
	//Component 
	public Ajax1(String id, IModel<User> model) {
		super(id, model);
		// TODO Auto-generated constructor stub
		User selected = model.getObject();
		final ModalDialog modal = new ModalDialog("modal");
        modal.add(new DefaultTheme());
        modal.closeOnClick();
        Label label1 = new Label( ModalDialog.CONTENT_ID,"User id=" + selected.getId());
        Label label2 = new Label(ModalDialog.CONTENT_ID,  "User Name=" + selected.getName() + "\n" + "User Email=" + selected.getEmail() + "\n" + "User Phone number=" + selected.getPhno() + "\n");
        modal.setContent(label1);
        modal.setContent(label2);
        add(modal);
		add(new AjaxLink<Void>("open") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                modal.open(target);
            	//System.out.println("User id=" + selected.getId() + "\n" + "User Name=" + selected.getName() + "\n" + "User Email=" + selected.getEmail() + "\n" + "User Phone number=" + selected.getPhno() + "\n");
            }
		});
	}

}
