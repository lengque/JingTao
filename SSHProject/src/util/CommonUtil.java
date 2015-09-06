package util;

public class CommonUtil {
	//id card regx format
	public static String Id_Card_Format = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
	
	//telphone regx format
	public static String Telphone_Format = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	//email regx format
	public static String Email_Format = "^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$";
	
	//statusCode 
	public static String STATU_NORMAL = "0000";
	public static String STATU_ERROR = "0000";
}
