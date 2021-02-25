package com.nagarro.servicecatalogue.dto;

import java.util.ArrayList;
import java.util.List;

import com.nagarro.servicecatalogue.enums.ServiceType;

public class ServiceInfoDto {

	private String serviceId;

	private ServiceType serviceType;

	private List<ServiceDescriptionDto> serviceDescriptionDtos = new ArrayList<>();

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public List<ServiceDescriptionDto> getServiceDescriptionDtos() {
		return serviceDescriptionDtos;
	}

	public void setServiceDescriptionDtos(List<ServiceDescriptionDto> serviceDescriptionDtos) {
		this.serviceDescriptionDtos = serviceDescriptionDtos;
	}

	@Override
	public String toString() {
		return "ServiceInfoDto [serviceId=" + serviceId + ", serviceType=" + serviceType + ", serviceDescriptionDtos="
				+ serviceDescriptionDtos + "]";
	}
	

}
