/**
 *	
 * @author dingli02  
 * @date 2019/04/04 10:21
 */
package com.tv189.core.util.lock.exception;

/**
 * 锁占用
 * 
 * @author dingli02
 * @date 2019/04/04 10:21
 */
public class LockOccupancyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LockOccupancyException() {
		super();
	}

	public LockOccupancyException(String message) {
		super(message);
	}

	public LockOccupancyException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockOccupancyException(Throwable cause) {
		super(cause);
	}
}
