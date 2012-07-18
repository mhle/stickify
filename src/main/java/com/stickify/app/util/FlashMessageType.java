package com.stickify.app.util;

/**
 * Enum representing Flash message status, to be used by Twitter bootstrap as css class names
 * @author michael le
 *
 */
public enum FlashMessageType {
	
	SUCCESS("alert-success"),
	ALERT("alert-error"),
	INFO("alert-info");
	
	private String realName;
	
	private FlashMessageType(String realName) {
        this.realName = realName;
    }

    public String toString() {
        return realName;
    }
}
