package com.happymart;

import java.io.Serializable;

public enum CommandType implements Serializable {
	UPDATE_STORE,      //send with StoreInformation
	CREATE_EMPLOYEE,   //send with Name
	UPDATE_EMPLOYEE,   //send with Employee
	CHECK_CREDENTIALS, //send with Employee
	OPEN_SESSION,      //send with Employee
	CLOSE_SESSION,     //send with Employee
	LOG_ACTIVITY,      //send with Activity
	CHECK_INVENTORY,   //send with String(filter) or UUID
	MODIFY_QUANTITY,   //send with HashMp<ItemType,ItemQuantity> (where quantity can be + or -)
}
