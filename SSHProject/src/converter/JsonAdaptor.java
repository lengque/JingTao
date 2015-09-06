package converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import errorUtil.FieldCode;
import model.Response;
import model.User;
import model.UserDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonAdaptor {
	
	public JSONObject RequestAdapt(String requestJson){
		JSONObject  request = new JSONObject();
		
		if(StringUtils.isNotBlank(requestJson)){
			request= JSONObject.fromObject(requestJson);
		}
		
		return request;
	}
	
	public JSONObject RequestAdapt(String requestJson,Class clazz){
		JSONObject  request = new JSONObject();
		
		if(StringUtils.isNotBlank(requestJson)){
			request= JSONObject.fromObject(requestJson);
		}
		
		return request;
	}
	
	public String ResponseAdapt(Response result){
		JSONObject  responseJson = new JSONObject();
		//fit header
		responseJson.put("header", HeaderAdapt(result));
		//fit body
		responseJson.put("body",BodyAdapt(result));
		
		return responseJson.toString();
	}
	
	private JSONObject HeaderAdapt(Response result){
		JSONObject headerJson = new JSONObject();
		
		//status code
		JSONObject statusJson = new JSONObject();
		headerJson.put("statusCode", result.getStatusCode());
		
		//error message
		JSONObject errorJson = new JSONObject();
		List<FieldCode> errorMsg= result.getErrorMsg();
		if(null != errorMsg && errorMsg.size()>0){
			JSONArray errorMsgJson = JSONArray.fromObject(errorMsg);
			headerJson.put("errorMsg", errorMsgJson);
		}
		
		return headerJson;
	}
	
	private JSONArray BodyAdapt(Response result){
		//用于返回的对象
		JSONArray bodyJson = new JSONArray();
		
		List<Object> dtoList = result.getDtoList();
		if(null != dtoList && dtoList.size()>0){
			for(Object o : dtoList){
				JSONObject jsonObject = new JSONObject();
				String dtoName = o.getClass().getSimpleName();
				jsonObject.put(dtoName, o);
				
				bodyJson.add(jsonObject);
			}
			return bodyJson;
		}
		
		return null;
	}
}
