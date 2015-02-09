/**
 * 
 */
package ua.store.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Sergey
 *
 */
public class ExceptionData {
	private List<String> dataList = new ArrayList<>();
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public void addData(String data) {
		dataList.add(data);
	}
	
	public int getSize() {
		return dataList.size();
	}

	/**
	 * @return the request
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
