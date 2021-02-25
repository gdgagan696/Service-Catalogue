package com.nagarro.servicecatalogue.dto;

public class ResponseDto {

	private String responseMessage;

	public ResponseDto(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
