package ua.store.filters;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import ua.store.projectservice.MyLogger;

/**
 * Servlet Filter implementation class LanguageFilter
 */
//@WebFilter(
//		urlPatterns = { "/*" },
//		initParams = {
//				@WebInitParam(name = "language", value = "en", description = "Set language")
//		})
public class LanguageFilter implements Filter, MyLogger {

	private String language;
	private Locale locale;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		language = fConfig.getInitParameter("language");
//		locale = new Locale(language);

		logger.debug("Language = \"" + language + "\"");

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (request.getParameter("language") != null) {
			language = request.getParameter("language");
		}

		((HttpServletRequest) request).getSession().setAttribute("language", language);
		Locale.setDefault(new Locale(language));


		logger.debug("Language = \"" + language + "\"");

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		language = null;
	}


}
