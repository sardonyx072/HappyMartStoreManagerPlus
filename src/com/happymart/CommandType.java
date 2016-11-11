package com.happymart;

import java.io.Serializable;

public enum CommandType implements Serializable {
	CHECK_STORE,		//send with is ignored
	UPDATE_STORE,		//send with StoreInformation
	GET_UNIQUE_UUID,	//ignore send with
	SAVE_SERVER_STATE,
	CREATE_EMPLOYEE,	//send with Name
	UPDATE_EMPLOYEE,	//send with Employee
	CHECK_CREDENTIALS_GENERIC,	//send with Credentials
	CHECK_CREDENTIALS_FOR_ME,	//send with Employee
	OPEN_SESSION,		//send with SessionInfo
	CLOSE_SESSION,		//send with SessionInfo
	LOG_ACTIVITY,		//send with Activity
	CHECK_INVENTORY,	//send with String(filter) or UUID
	ADD_QUANTITY,		//send with HashMap<ItemType,ItemQuantity>
	SUBTRACT_QUANTITY,	//send with HashMap<ItemType,ItemQuantity>
	CHANGE_PRICE,		//send with ItemType
}
