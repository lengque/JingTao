package model;

import java.util.List;

import util.CommonUtil;
import errorUtil.FieldCode;

public class Response {
	
	private String statusCode = CommonUtil.STATU_NORMAL;
	
	private List<FieldCode> errorMsg;
	private List<Object> dtoList;
	
	public String getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public List<FieldCode> getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(List<FieldCode> errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public List<Object> getDtoList() {
		return dtoList;
	}
	
	public void setDtoList(List<Object> dtoList) {
		this.dtoList = dtoList;
	}
}
