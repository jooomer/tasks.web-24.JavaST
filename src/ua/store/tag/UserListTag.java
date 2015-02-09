/**
 * 
 */
package ua.store.tag;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import ua.store.model.dao.DAOFactory;
import ua.store.model.dao.UserDAO;
import ua.store.model.instances.users.User;
import ua.store.projectservice.MyLogger;

/**
 * @author Sergey
 *
 */
@SuppressWarnings("serial")
public class UserListTag extends BodyTagSupport implements MyLogger {

	@Override
	public int doStartTag() throws JspException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("UsersListTag - doStartTag() - begin");
		}
		
		// get list of all users
		UserDAO userDAO = DAOFactory.getUserDAO();
		TreeSet<User> userList = userDAO.getAllUsers();
		
		// sort list of users
//		Collections.sort(usersList);
		
		// send list of users to a browser
		for (User user : userList) {
			String userRow = "<hr/>" + user.getUserId() 
					+ ", " + user.getUserName()
					+ ", " + user.getUserType()
					+ ", " + user.getFirstName()
					+ ", " + user.getLastName()
					+ ", " + user.getEmail()
					+ ", " + user.getAddress()
					+ ", " + user.getComments()
					+ ", " + user.isInBlackList();
					
			try {
				JspWriter out = pageContext.getOut();
				out.write(userRow);
			} catch (IOException e) {
				logger.error("An error occurred while writing by JspWriter", e);
			}
		}
		
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
