/**
 *	
 * @author dingli02  
 * @date 2019/04/04 10:29
 */
package com.tv189.core.util.lock.exception;

/**
 *	锁参数为空异常
 * @author dingli02  
 * @date 2019/04/04 10:29
 */
public class LockParamNoneException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LockParamNoneException() {
		super();
	}

	public LockParamNoneException(String message) {
		super(message);
	}

	public LockParamNoneException(String message, Throwable cause) {
		super(message, cause);
	}

	public LockParamNoneException(Throwable cause) {
		super(cause);
	}
}
