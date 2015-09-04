package errorUtil;

public enum ErrorLevel {
	//critical error : this level error won't show to user. 
	CRITICAL_ERROR(90),

	ERROR(80),
	//show to user
	INFO(10);
	
	private Integer value;
	
	public Integer getValue(){
		return this.value;
	}
	ErrorLevel(Integer value){
		this.value = value;
	}
}
