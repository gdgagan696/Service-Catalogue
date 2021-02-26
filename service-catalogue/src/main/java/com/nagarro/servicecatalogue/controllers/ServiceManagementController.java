package com.nagarro.servicecatalogue.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.servicecatalogue.dto.ResponseDto;
import com.nagarro.servicecatalogue.dto.ServiceDescriptionDto;
import com.nagarro.servicecatalogue.dto.ServiceInfoDto;
import com.nagarro.servicecatalogue.service.ServiceMangementService;

@RestController
@RequestMapping("/services")
public class ServiceManagementController {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceManagementController.class);
	
	@Autowired
	private ServiceMangementService serviceMangementService;

	@GetMapping("/allServices")
	public ResponseEntity<List<ServiceInfoDto>> getAllServices() {
		LOG.debug("Inside getAllServices method");
		return new ResponseEntity<>(serviceMangementService.getAllServices(), HttpStatus.OK);
	}

	@GetMapping("/allServices/{serviceType}")
	public ResponseEntity<List<ServiceInfoDto>> getAllServicesByServiceType(@PathVariable String serviceType) {
		LOG.debug("Inside getAllServicesByServiceType method, serviceType:{}",serviceType);
		return new ResponseEntity<>(serviceMangementService.getAllServicesByServiceType(serviceType), HttpStatus.OK);
	}
	
	@GetMapping("/serviceDescInfo/{serviceType}/{serviceDescriptionId}")
	public ResponseEntity<ServiceDescriptionDto> getServiceDescriptionInfo(@PathVariable String serviceType,@PathVariable String serviceDescriptionId ) {
		LOG.debug("Inside getServiceDescriptionInfo method, serviceType:{},serviceDescriptionId:{}",serviceType,serviceDescriptionId);
		return new ResponseEntity<>(serviceMangementService.getServiceDescriptionInfo(serviceType,serviceDescriptionId), HttpStatus.OK);
	}

	@PostMapping("/addUpdateService")
	public ResponseEntity<ResponseDto> addOrUpdateService(@RequestBody ServiceInfoDto serviceInfoDto) {
		LOG.debug("Inside addOrUpdateProducer method, serviceInfoDto:{}",serviceInfoDto);
		serviceMangementService.addOrUpdateService(serviceInfoDto);
		return new ResponseEntity<>(new ResponseDto("Service Added/Updated Successfully"), HttpStatus.CREATED);
	}

}
