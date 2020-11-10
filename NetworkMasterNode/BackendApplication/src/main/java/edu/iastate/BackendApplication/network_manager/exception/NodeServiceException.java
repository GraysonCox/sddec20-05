package edu.iastate.BackendApplication.network_manager.exception;

/**
 * An exception thrown by any implementation of NodeService.
 */
public class NodeServiceException extends Exception {

	private NodeServiceException(String message) {
		super(message);
	}

	private NodeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public static NodeServiceException withMessage(String message) {
		return new NodeServiceException(message);
	}

	public static NodeServiceException withMessage(String message, Throwable cause) {
		return new NodeServiceException(message, cause);
	}

}
