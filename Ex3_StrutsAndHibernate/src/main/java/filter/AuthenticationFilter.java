package filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;
import model.User;


/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	private Map<String, List<Role>> permissionList;

	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		boolean isLoggedIn = (session != null) && (session.getAttribute("user") != null);
		boolean isLogInRequest = (req.getRequestURI().equals(req.getContextPath() + "/login.do")) 
										|| (req.getRequestURI().equals(req.getContextPath() + "/auth.do"));


		if (!isLoggedIn && isLogInRequest) { 
			chain.doFilter(request, response);
		} else if (isLoggedIn && !isLogInRequest) {
			List<Role> roleList = permissionList.get(req.getParameter("method"));
			Role userRole = ((User) session.getAttribute("user")).getRole();
			if (roleList == null || roleList.contains(userRole)) {
				chain.doFilter(request, response);
			} else {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		} else if (isLoggedIn && isLogInRequest) {
			res.sendRedirect("process.do");
			
		} else {
			res.sendRedirect("login.do");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		permissionList = new HashMap<String, List<Role>>();
		InputStream inp = null;
		Properties properties = new Properties();
		try {
			inp = fConfig.getServletContext().getResourceAsStream("/WEB-INF/permission.properties");
			properties.load(inp);
			for (Object key : properties.keySet()) {
				List<Role> roleList = new ArrayList<Role>();
				StringTokenizer stringTokenizer = new StringTokenizer(properties.getProperty((String) key), ",");
				while (stringTokenizer.hasMoreElements())
					roleList.add(Role.valueOf(stringTokenizer.nextToken()));
				permissionList.put((String) key, roleList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inp != null) {
				try {
					inp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
