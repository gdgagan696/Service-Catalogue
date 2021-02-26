package com.nagarro.servicecatalogue.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nagarro.servicecatalogue.constants.CommonConstants;
import com.nagarro.servicecatalogue.constants.ExceptionMessageConstants;
import com.nagarro.servicecatalogue.dto.ServiceDescriptionDto;
import com.nagarro.servicecatalogue.dto.ServiceInfoDto;
import com.nagarro.servicecatalogue.enums.ServiceStockStatus;
import com.nagarro.servicecatalogue.enums.ServiceType;
import com.nagarro.servicecatalogue.exception.ProductCatalogueException;
import com.nagarro.servicecatalogue.service.ServiceMangementService;

@Service
public class ServiceManagementServiceImpl implements ServiceMangementService {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceManagementServiceImpl.class);

	private static List<ServiceInfoDto> allServices = new ArrayList<>();

	static {
		getProducerInfo();
	}

	private static void getProducerInfo() {

		LOG.debug("Inside getProducerInfo method");

		ServiceInfoDto electricianInfo = new ServiceInfoDto();
		electricianInfo.setServiceId(UUID.randomUUID().toString());
		electricianInfo.setServiceType(ServiceType.ELECTRICIAN);

		List<ServiceDescriptionDto> electricityServiceInfoDtos = new ArrayList<>();

		ServiceDescriptionDto acServiceInfoDto = new ServiceDescriptionDto();
		acServiceInfoDto.setServiceDescriptionId(UUID.randomUUID().toString());
		acServiceInfoDto.setServiceId(electricianInfo.getServiceId());
		acServiceInfoDto.setServiceDescription("AC SERVICE");
		acServiceInfoDto.setPrice(1500D);
		acServiceInfoDto.setServiceName("AC MAINTENANCE");
		acServiceInfoDto.setServiceStockStatus(ServiceStockStatus.IN_STOCK);
		acServiceInfoDto.setCurrency(CommonConstants.INR_CURRENCY);
		acServiceInfoDto.setServiceType(ServiceType.ELECTRICIAN);

		ServiceDescriptionDto electricityFitting = new ServiceDescriptionDto();
		electricityFitting.setServiceDescriptionId(UUID.randomUUID().toString());
		electricityFitting.setServiceId(electricianInfo.getServiceId());
		electricityFitting.setServiceDescription("Electricity fitting in home.");
		electricityFitting.setPrice(3000D);
		electricityFitting.setServiceName("ELECTRICITY FITTING");
		electricityFitting.setServiceStockStatus(ServiceStockStatus.IN_STOCK);
		electricityFitting.setCurrency(CommonConstants.INR_CURRENCY);
		electricityFitting.setServiceType(ServiceType.ELECTRICIAN);

		electricityServiceInfoDtos.add(acServiceInfoDto);
		electricityServiceInfoDtos.add(electricityFitting);
		electricianInfo.setServiceDescriptionDtos(electricityServiceInfoDtos);

		ServiceInfoDto yogaTrainerInfo = new ServiceInfoDto();
		yogaTrainerInfo.setServiceId(UUID.randomUUID().toString());
		yogaTrainerInfo.setServiceType(ServiceType.YOGA_TRAINER);

		List<ServiceDescriptionDto> yogaTrainerServiceInfoDtos = new ArrayList<>();

		ServiceDescriptionDto yogaTrainerServiceInfo = new ServiceDescriptionDto();
		yogaTrainerServiceInfo.setServiceDescriptionId(UUID.randomUUID().toString());
		yogaTrainerServiceInfo.setServiceId(yogaTrainerInfo.getServiceId());
		yogaTrainerServiceInfo.setServiceDescription("YOGA,ZUMBA");
		yogaTrainerServiceInfo.setPrice(2000D);
		yogaTrainerServiceInfo.setServiceName("YOGA TRAINING");
		yogaTrainerServiceInfo.setServiceStockStatus(ServiceStockStatus.IN_STOCK);
		yogaTrainerServiceInfo.setCurrency(CommonConstants.INR_CURRENCY);
		yogaTrainerInfo.setServiceType(ServiceType.YOGA_TRAINER);
		

		yogaTrainerServiceInfoDtos.add(yogaTrainerServiceInfo);

		yogaTrainerInfo.setServiceDescriptionDtos(yogaTrainerServiceInfoDtos);

		allServices.add(electricianInfo);
		allServices.add(yogaTrainerInfo);

		LOG.debug("Total services added:{}", allServices.size());
	}

	@Override
	public List<ServiceInfoDto> getAllServices() {
		LOG.debug("Inside getAllServices method");
		if (!allServices.isEmpty()) {
			LOG.debug("total services:{}", allServices);
			return allServices;
		} else {
			throw new ProductCatalogueException(ExceptionMessageConstants.NO_PRODUCER_AVAILABLE);
		}
	}

	@Override
	public List<ServiceInfoDto> getAllServicesByServiceType(String serviceType) {
		LOG.debug("Inside getAllServicesByServiceType method,serviceType:{}", serviceType);
		List<ServiceInfoDto> filteredService = allServices.stream()
				.filter(product -> product.getServiceType().name().equalsIgnoreCase(serviceType))
				.collect(Collectors.toList());
		if (filteredService.isEmpty()) {
			throw new ProductCatalogueException(ExceptionMessageConstants.NO_SERVICE_AVAILABLE);
		}
		return filteredService;

	}

	@Override
	public void addOrUpdateService(ServiceInfoDto serviceInfoDto) {
		LOG.debug("Inside addOrUpdateService method,serviceInfoDto:{}", serviceInfoDto);

		if (Objects.nonNull(serviceInfoDto)) {
			Optional<ServiceInfoDto> updatedService = allServices.stream()
					.filter(service -> service.getServiceId().equals(serviceInfoDto.getServiceId())).findFirst();
			LOG.debug("Filtered updatedService method,serviceInfoDto:{}", serviceInfoDto);
			String serviceId=updatedService.isPresent()?updatedService.get().getServiceId():UUID.randomUUID().toString();
			serviceInfoDto.getServiceDescriptionDtos().stream()
					.filter(serviceDesc -> Objects.isNull(serviceDesc.getServiceDescriptionId()))
					.forEach(serviceDesc -> {
						serviceDesc.setServiceDescriptionId(UUID.randomUUID().toString());
						serviceDesc.setServiceId(serviceId);
					});
			if (updatedService.isPresent()) {
				updatedService.get().setServiceType(serviceInfoDto.getServiceType());
				updatedService.get().setServiceDescriptionDtos(serviceInfoDto.getServiceDescriptionDtos());
			} else {
				serviceInfoDto.setServiceId(serviceId);
				allServices.add(serviceInfoDto);
			}
		}
	}

	@Override
	public ServiceDescriptionDto getServiceDescriptionInfo(String serviceType, String serviceDescriptionId) {

		LOG.debug("Inside getServiceDescriptionInfo method,serviceType:{},serviceDescriptionId:{}", serviceType,
				serviceDescriptionId);
		return allServices.stream().filter(service -> service.getServiceType().name().equalsIgnoreCase(serviceType))
				.map(filteredService -> {
					return filteredService.getServiceDescriptionDtos().stream()
							.filter(serviceDescription -> serviceDescription.getServiceDescriptionId()
									.equals(serviceDescriptionId))
							.findFirst().orElseThrow(()->new ProductCatalogueException(ExceptionMessageConstants.INVALID_SERVICE_DESCRIPTION_ID));
				}).findFirst().orElseThrow(()->new ProductCatalogueException(ExceptionMessageConstants.NO_SERVICE_AVAILABLE));
		
//		List<ServiceInfoDto>serviceInfoDtos= allServices.stream().filter(service -> service.getServiceType().name().equalsIgnoreCase(serviceType)).collect(Collectors.toList());
//
//		if(!serviceInfoDtos.isEmpty()) {
//			return serviceInfoDtos.stream().map(filteredService->{
//					return filteredService.getServiceDescriptionDtos().stream()
//							.filter(serviceDescription -> serviceDescription.getServiceDescriptionId()
//									.equals(serviceDescriptionId))
//							.findFirst().orElseThrow(()->new ProductCatalogueException(ExceptionMessageConstants.INVALID_SERVICE_DESCRIPTION_ID));
//				}).findFirst().get();
//		}
//		else {
//			throw new ProductCatalogueException(ExceptionMessageConstants.NO_SERVICE_AVAILABLE);
//		}
	}

}
