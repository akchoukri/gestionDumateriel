package com.ymagis.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Mail {

    @Email
    @NotNull
    private String to;
    private String subjet;
    private String msgText;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubjet() {
		return subjet;
	}
	public void setSubjet(String subjet) {
		this.subjet = subjet;
	}
	public String getMsgText() {
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
