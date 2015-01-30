/***
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

For more information, please refer to <http://unlicense.org/>
 */

package com.weidong.pageview.pageview;

import android.util.Log;


/**
 * @date 12.12.2014
 * @author WeiDong 日志管理类
 * */

public class DebugLog {

	static String className;
	static String methodName;
	static int lineNumber;

	private DebugLog() {
		/* Protect from instantiations */
	}

	public static boolean isDebuggable() {
		return true;
	}

	private static String createLog(String log) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append(methodName);
		buffer.append(":");
		buffer.append(lineNumber);
		buffer.append("]");
		buffer.append(log);

		return buffer.toString();
	}

	private static void getMethodNames(StackTraceElement[] sElements) {

		// 此方法返回一个包含由该堆栈跟踪元素所表示的执行点的源文件的名称。(类名)
		className = sElements[1].getFileName();

		// 此方法返回一个包含由该堆栈跟踪元素所表示的执行点的方法的名称。(方法名)
		methodName = sElements[1].getMethodName();

		// 此方法返回一个包含由该堆栈跟踪元素所表示的执行点源行的行号。
		lineNumber = sElements[1].getLineNumber();
	}

	/**
	 * 打印e
	 * 
	 * @param message
	 *            日志信息
	 */
	public static void e(String message) {
		if (!isDebuggable())
			return;

		// Throwable instance must be created before any methods
		getMethodNames(new Throwable().getStackTrace());
		Log.e(className, createLog(message));
	}

	public static void i(String message) {
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.i(className, createLog(message));
	}

	public static void d(String message) {
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.d(className, createLog(message));
	}

	public static void v(String message) {
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.v(className, createLog(message));
	}

	public static void w(String message) {
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.w(className, createLog(message));
	}

	public static void wtf(String message) {
		if (!isDebuggable())
			return;

		getMethodNames(new Throwable().getStackTrace());
		Log.wtf(className, createLog(message));
	}

}
