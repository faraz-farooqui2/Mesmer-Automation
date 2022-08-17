package com.mesmer

import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.KeywordUtil


public class MesmerLogUtils {
	private static String loggerPrefix = "[MESMER]: "

	public static void markError(String message){
		KeywordUtil.markError(loggerPrefix+message)
	}

	public static void markErrorAndStop(String message){
		KeywordUtil.markErrorAndStop(loggerPrefix+message)
	}

	public static void markPassed(String message){
		KeywordUtil.markPassed(loggerPrefix+message)
	}

	public static void markFailed(String message){
		KeywordUtil.markFailed(loggerPrefix+message)
	}

	public static void markFailedAndStop(String message){
		KeywordUtil.markErrorAndStop(loggerPrefix+message)
	}

	public static void markWarning(String message){
		KeywordUtil.markWarning(loggerPrefix+message)
	}

	public static void logInfo(String message){
		KeywordUtil.logInfo(loggerPrefix+message)
	}
}
