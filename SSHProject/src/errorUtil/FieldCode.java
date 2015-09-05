package errorUtil;

public class FieldCode {
	//error code
	private ErrorList errorCode;
	//error desc
	private ErrorDesc errorDesc;
	//error level
	private ErrorLevel errorLevel;
	
	public FieldCode(){};
	
	public FieldCode(ErrorList errorCode){
		this.errorCode = errorCode;
	}
	
	public FieldCode(ErrorList errorCode,ErrorDesc errorDesc){
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public FieldCode(ErrorList errorCode,ErrorDesc errorDesc,ErrorLevel errorLevel){
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.errorLevel = errorLevel;
	}
	
	
	public ErrorList getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(ErrorList errorCode) {
		this.errorCode = errorCode;
	}
	public ErrorDesc getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(ErrorDesc errorDesc) {
		this.errorDesc = errorDesc;
	}
	public ErrorLevel getErrorLevel() {
		return errorLevel;
	}
	public void setErrorLevel(ErrorLevel errorLevel) {
		this.errorLevel = errorLevel;
	}
}
