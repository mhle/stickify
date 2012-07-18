package com.stickify.web.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stickify.app.domain.Role;
import com.stickify.app.service.RoleService;

public class RolePropertyEditor extends PropertyEditorSupport {
	
	Logger logger = LoggerFactory.getLogger(RolePropertyEditor.class);
	
	private RoleService roleService;
	
	public RolePropertyEditor(RoleService roleService) {
		this.roleService = roleService;
	}
	
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	Long id = new Long(text);
        super.setValue(roleService.findById(id));
    }
    
    @Override
    public String getAsText() {
    	logger.error("getAsText: " + super.getValue());
        
    	return ((Role) super.getValue()).getName();
    }
    
}
