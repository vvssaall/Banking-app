package com.banking.utils;

public enum AccountStatusEnum {
	
	PENDING("AS01"), PROCESSING("AS02"), DORMANT("AS03"), APPROVED("AS04"), ACTIVE("AS05");

	private String code;

	AccountStatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
