package com.nagarro.pos.model;

/**
 * POJO of {@link ResponseMessage} that to be sent on client side
 * 
 * @author nishantgarg
 *
 */
public class ResponseMessage {

	private String errorMessage;

	private String token;

	private int Status;

	private String successMessage;

	/**
	 * 
	 */
	public ResponseMessage() {
	}

	/**
	 * @param errorMessage
	 * @param status
	 * @param successMessage
	 */
	public ResponseMessage(String errorMessage, int status, String successMessage) {
		this.errorMessage = errorMessage;
		Status = status;
		this.successMessage = successMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return Status;
	}

	/**
	 * @return the successMessage
	 */
	public String getSuccessMessage() {
		return successMessage;
	}

	/**
	 * @param errorMessage
	 * @param token
	 * @param status
	 * @param successMessage
	 */
	public ResponseMessage(String errorMessage, String token, int status, String successMessage) {
		this.errorMessage = errorMessage;
		this.token = token;
		Status = status;
		this.successMessage = successMessage;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		Status = status;
	}

	/**
	 * @param successMessage
	 *            the successMessage to set
	 */
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

}
