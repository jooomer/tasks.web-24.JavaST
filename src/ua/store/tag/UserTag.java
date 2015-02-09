/**
 * 
 */
package ua.store.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
@SuppressWarnings("serial")
public class UserTag extends BodyTagSupport implements MyLogger {

	@Override
	public int doStartTag() throws JspException {

		if (logger.isDebugEnabled()) {
			logger.debug("UserTag - doStartTag() - begin");
		}

		// get user which is found
		User user = (User) pageContext.getSession().getAttribute("search_user");

		// send user to a browser
		String userRow = user.getUserId() + ": " 
				+ user.getUserName() + ", " 
				+ user.getUserType() + ", " 
				+ user.getFirstName() + ", "
				+ user.getLastName() + ", " 
				+ user.getEmail() + ", "
				+ user.getAddress() + ", " 
				+ user.getComments() + ", "
				+ user.isInBlackList();
		try {
			JspWriter out = pageContext.getOut();
			out.write("<hr/>");
			out.write("<br>");
			out.write("<h3>Search result for userName = \"" + user.getUserName() + "\" :</h3>");
			out.write("<br>");
			out.write(userRow);			
		} catch (IOException e) {
			logger.error("An error occurred while writing by JspWriter", e);
		}

		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
