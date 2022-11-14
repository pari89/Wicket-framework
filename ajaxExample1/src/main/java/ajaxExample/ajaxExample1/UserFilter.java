package ajaxExample.ajaxExample1;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserFilter implements Serializable
{
    private int idFrom;
    private int idTo;
	public int getIdFrom() {
		return idFrom;
	}
	public void setIdFrom(int idFrom) {
		this.idFrom = idFrom;
	}
	public int getIdTo() {
		return idTo;
	}
	public void setIdTo(int idTo) {
		this.idTo = idTo;
	}
    
    
}
