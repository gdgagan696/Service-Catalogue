package com.nagarro.servicecatalogue.service;

import java.util.List;

import com.nagarro.servicecatalogue.dto.ServiceDescriptionDto;
import com.nagarro.servicecatalogue.dto.ServiceInfoDto;

public interface ProductMangementService {

	List<ServiceInfoDto> getAllServices();

	List<ServiceInfoDto> getAllServicesByServiceType(String serviceType);

	void addOrUpdateService(ServiceInfoDto serviceInfoDto);
	
	ServiceDescriptionDto getServiceDescriptionInfo(String serviceType,String serviceDescriptionId);

}
