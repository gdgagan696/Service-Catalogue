package com.nagarro.servicecatalogue.dto;

import com.nagarro.servicecatalogue.enums.ServiceStockStatus;
import com.nagarro.servicecatalogue.enums.ServiceType;

public class ServiceDescriptionDto {

	private String serviceDescriptionId;

	private String serviceName;

	private String serviceDescription;

	private ServiceStockStatus serviceStockStatus;

	private Double price;

	private String currency;

	private String serviceId;

	private ServiceType serviceType;

	public String getServiceDescriptionId() {
		return serviceDescriptionId;
	}

	public void setServiceDescriptionId(String serviceDescriptionId) {
		this.serviceDescriptionId = serviceDescriptionId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public ServiceStockStatus getServiceStockStatus() {
		return serviceStockStatus;
	}

	public void setServiceStockStatus(ServiceStockStatus serviceStockStatus) {
		this.serviceStockStatus = serviceStockStatus;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

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

	@Override
	public String toString() {
		return "ServiceDescriptionDto [serviceDescriptionId=" + serviceDescriptionId + ", serviceName=" + serviceName
				+ ", serviceDescription=" + serviceDescription + ", serviceStockStatus=" + serviceStockStatus
				+ ", price=" + price + ", currency=" + currency + ", serviceId=" + serviceId + ", serviceType="
				+ serviceType + "]";
	}
	

}
