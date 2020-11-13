package edu.iastate.BackendApplication.network_manager.exception;

/**
 * An exception thrown by NmapClient.
 */
public class NmapClientException extends Exception {

	private NmapClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public static NmapClientException withMessage(String message, Throwable cause) {
		return new NmapClientException(message, cause);
	}

}
