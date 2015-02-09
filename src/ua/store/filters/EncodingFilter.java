package ua.store.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.apache.commons.lang3.StringUtils;

import ua.store.projectservice.MyLogger;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(urlPatterns = { "/*" },
		initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding param")})
public class EncodingFilter implements Filter, MyLogger {

	private String code;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		logger.debug("Start of method");

		String codeRequest = request.getCharacterEncoding();

		if (StringUtils.isNotBlank(code) && !code.equalsIgnoreCase(codeRequest)) {
			request.setCharacterEncoding(code);
			response.setCharacterEncoding(code);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		code = fConfig.getInitParameter("encoding");
		logger.debug("characterEncoding = " + code);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		code = null;
	}


}
