package errorUtil;

public enum SystemError implements ErrorList {
	SYS900("SYS900");
	
	private String value;
	
	SystemError(String value){
		this.value = value;
	}

	@Override
	public String getValue() {
		return this.value;
	}
}
