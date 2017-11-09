/**
 * 
 */
package com.ceitechs.dproz.servicemanagement.adapter.rest.resources;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author vowino
 *
 */
@Getter
@Setter
@ToString
public class ServiceManagementErrorResponse {
	private String title;
	private int status;
	private String detail;
	private long timeStamp;
	private String developerMessage;
	private List<String> errors = new ArrayList<>();

	/**
	 * 
	 * @param statusPhrase
	 * @param statusLabel
	 * @param statusCode
	 */
	public ServiceManagementErrorResponse(String statusPhrase, String statusLabel, int statusCode) {
		this.title = statusLabel;
		this.detail = statusPhrase;
		this.developerMessage = statusPhrase;
		this.status = statusCode;
		this.timeStamp = System.currentTimeMillis();
	}

	public void addErrorMessage(String error) {
		errors.add(error);
	}

	public void addErrorMessages(List<String> errors) {
		errors.forEach(e -> addErrorMessage(e));
	}
}
