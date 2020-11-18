package edu.iastate.BackendApplication.network_manager.exception;

/**
 * An exception thrown by NodeApiClient.
 */
public class NodeApiClientException extends Exception {

	private NodeApiClientException(String message) {
		super(message);
	}

	private NodeApiClientException(Throwable cause) {
		super(cause);
	}

	public static NodeApiClientException withHttpStatusCode(int httpStatusCode) {
		String message = String.format("Received HTTP status code %d.", httpStatusCode);
		return new NodeApiClientException(message);
	}

	public static NodeApiClientException withMessage(String message) {
		return new NodeApiClientException(message);
	}

	public static NodeApiClientException from(Throwable cause) {
		return new NodeApiClientException(cause);
	}

}
