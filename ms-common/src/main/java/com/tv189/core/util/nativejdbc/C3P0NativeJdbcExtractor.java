/**
 *	
 * @author dingli02  
 * @date 2019/01/04 17:25
 */
package com.tv189.core.util.nativejdbc;

import java.lang.reflect.Method;
import java.sql.Connection;


import com.mchange.v2.c3p0.C3P0ProxyConnection;

/**
 *	<ul>获取本地jdbc连接</ul>
 *	<li>用途：如果连接池用的是c3p0，通过jdbcTemplate获取到的连接是包装后的连接，并不是原始的jdbc连接，
 *		该工具类可从包装后的连接获取到原始jdbc连接</li>
 *
 *	<li>Example：</br>
 *	Connection connection = jdbcTemplate.getDataSource().getConnection(); </br>
 *	C3P0NativeJdbcExtractor cp30NativeJdbcExtractor = new C3P0NativeJdbcExtractor(); </br>
 *	PgConnection pgConnection = (PgConnection) cp30NativeJdbcExtractor.getNativeConnection(connection); </br>
 *	</li>
 * @author dingli02  
 * @date 2019/01/04 17:25
 */
public class C3P0NativeJdbcExtractor {
	private final Method getRawConnectionMethod;

	public C3P0NativeJdbcExtractor() {
		try {
			this.getRawConnectionMethod = getClass().getMethod(
					"getRawConnection",  Connection.class);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(
					"Internal error in C3P0NativeJdbcExtractor ", e);
		}
	}

	public static Connection getRawConnection(Connection con) {
		return con;
	}

	public Connection getNativeConnection(Connection con) {
		if (con instanceof C3P0ProxyConnection) {
			C3P0ProxyConnection cpCon = (C3P0ProxyConnection) con;
			try {
				return (Connection) cpCon.rawConnectionOperation(
						this.getRawConnectionMethod, null,
						new Object[] { C3P0ProxyConnection.RAW_CONNECTION });
			} catch (Exception e) {
				throw new IllegalStateException(
						"Internal error in C3P0NativeJdbcExtractor ", e);
			}
		}
		return con;
	}
}
