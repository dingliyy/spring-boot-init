/**
 *	
 * @author dingli02  
 * @date 2019/04/04 10:23
 */
package com.tv189.core.util.lock.exception;

/**
 *	锁业务处理异常
 * @author dingli02  
 * @date 2019/04/04 10:23
 */
public class LockLogicHandleException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LockLogicHandleException() {
		super();
	}

	public LockLogicHandleException(String message) {
		super(message);
	}

	public LockLogicHandleException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockLogicHandleException(Throwable cause) {
		super(cause);
	}
}
