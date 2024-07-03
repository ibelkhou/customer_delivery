package com.carrefour.customer.delivery.dto;


import com.carrefour.customer.delivery.enums.MsgDeliveryResponse;
import org.springframework.http.HttpStatus;

public class DeliveryResponse<T> {

	private HttpStatus responseCode;
	private MsgDeliveryResponse responseMessage;
	private T responseObject;

	public DeliveryResponse() {}
	public DeliveryResponse(HttpStatus code, MsgDeliveryResponse msg) {
		this.responseCode = code;
		this.responseMessage = msg;
	}
	public DeliveryResponse(HttpStatus code, MsgDeliveryResponse msg, T responseObject) {
		this.responseCode = code;
		this.responseMessage = msg;
		this.responseObject = responseObject;
	}
	public HttpStatus getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}
	public MsgDeliveryResponse getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(MsgDeliveryResponse responseMessage) {
		this.responseMessage = responseMessage;
	}
	public T getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}

	public void setSuccessResponse() {
		this.setResponseCode(HttpStatus.OK);
		this.setResponseMessage(MsgDeliveryResponse.OK);
	}

	public void setInternalServerErrorResponse() {
		this.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR);
		this.setResponseMessage(MsgDeliveryResponse.TRANSACTION_PROBLEM);
	}
}
