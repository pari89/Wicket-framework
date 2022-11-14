package ajaxExample.ajaxExample1;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalDialog;
import org.apache.wicket.extensions.ajax.markup.html.modal.theme.DefaultTheme;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	public HomePage(final PageParameters parameters) {
	      super(parameters);
	      final ModalDialog modal = new ModalDialog("modal");
          modal.add(new DefaultTheme());
          modal.closeOnClick();
          Label label = new Label(ModalDialog.CONTENT_ID, "I'm a modal dialog!");

          modal.setContent(label);

          add(modal);
          add(new AjaxLink<Void>("open") {
              public void onClick(AjaxRequestTarget target) {
                      modal.open(target);
              }
          });
//	      final ClickCounterLabel clickCounterLabel =
//	         new ClickCounterLabel("clickCounterLabel", "Click on me!");
//	      @SuppressWarnings("rawtypes")
//		final Label clickCounter =
//	         new Label("clickCounter", new PropertyModel(clickCounterLabel, "clickCounter"));
//
//
//	      clickCounterLabel.setOutputMarkupId(true);
//	      clickCounterLabel.add(new AjaxEventBehavior("click"){
//
//	         /**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//	         protected void onEvent(AjaxRequestTarget target) {
//	            clickCounterLabel.clickCounter++;
//	            target.add(clickCounter);
//	         }
//	      });
//
//	      add(clickCounterLabel);
//	      add(clickCounter.setOutputMarkupId(true));
	    }
	}

	class ClickCounterLabel extends Label{
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	public int clickCounter;

	   public ClickCounterLabel(String id) {
	      super(id);
	   }

	   public ClickCounterLabel(String id, IModel<?> model) {
	      super(id, model);
	   }

	   public ClickCounterLabel(String id, String label) {
	      super(id, label);
	   }
	}
