package com.ehs.edm.edmLibraryManager.edmLibrary.service;

import java.util.List;

import com.ehs.edm.edmLibraryManager.edmLibrary.entity.EdmLibraryParameter;

public interface EdmLibraryParameterService {
	
	public void saveEdmLibraryParameter( List<EdmLibraryParameter> edmLibraryParameters, String code);
	
	public List<EdmLibraryParameter> getEdmLibraryParameter(String code);
}
