package com.happymart;

import java.io.Serializable;

public enum CommandType implements Serializable {
	CREATE_EMPLOYEE,
	UPDATE_EMPLOYEE,
	OPEN_SESSION,
	CLOSE_SESSION,
	LOG_ACTIVITY,
	
}
