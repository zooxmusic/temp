package com.aeg.interceptor;

/*
 * (C) 2007 Mark Menard & Vita Rara, Inc.
 *
 * Mark Menard and Vita Rara, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


//import javax.servlet.http.HttpServletRequest;

public class LoginInterceptor /* extends AbstractInterceptor implements StrutsStatics*/ {

	/*private SecurityManager securityManager;

	private static final String RELOAD = "login";
	private static final String SUCCESS = "login-success";
	private static final String FAIL = "login-fail";

	private static final Log log = LogFactory.getLog (LoginInterceptor.class);
	private static final String USER_HANDLE = "QUADRAN_USER_SESSSION_HANDLE";
	private static final String LOGIN_ATTEMPT = "QUADRAN_LOGIN_ATTEMPT";
	private static final String USERNAME = "QUADRAN_USERNAME";
	private static final String PASSWORD = "QUADRAN_PASSWORD";

	public void init () {
		log.info ("Intializing LoginInterceptor");
	}

	public void destroy () {}

	public String intercept (ActionInvocation invocation) throws Exception {

		final ActionContext context = invocation.getInvocationContext();
		Map<String, Object> session = context.getSession();
		if (null != session) {
			return invocation.invoke();
		}

		Principal identity = (Principal)session.get(USER_HANDLE);
		if (null != identity) {
			return invocation.invoke();
		}

		// Get the action context from the invocation so we can access the
		// HttpServletRequest and HttpSession objects.
		HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);

		// Is there a "user" object stored in the user's HttpSession?
		//Object user = session.getAttribute (USER_HANDLE);

			// Is the user attempting to log in right now?
			String loginAttempt = request.getParameter (LOGIN_ATTEMPT);
			if (StringUtils.isBlank (loginAttempt) ) { return RELOAD; } // The user is attempting to log in.

			String username = request.getParameter (USERNAME);
			String password = request.getParameter (PASSWORD);

			// Use the security manager to validate the user's username and password.
			Object user = securityManager.login (username, password);


				// Process the user's login attempt.
				if (processLoginAttempt (request, session) ) {
					// The login succeeded send them the login-success page.
					return "login-success";
				} else {
					// The login failed. Set an error if we can on the action.
					Object action = invocation.getAction ();
					if (action instanceof com.opensymphony.xwork2.ValidationAware) {
						((com.opensymphony.xwork2.ValidationAware) action).addActionError ("Username or password incorrect.");
					}
				}
			}

			// Either the login attempt failed or the user hasn't tried to login yet, 
			// and we need to send the login form.
			return "login";
		}
	}

	/**
	 * Attempt to process the user's login attempt delegating the work to the 
	 * SecurityManager.
	 */
	/*public boolean processLoginAttempt (HttpServletRequest request, HttpSession session) {
		// Get the username and password submitted by the user from the HttpRequest.
		String username = request.getParameter (USERNAME);
		String password = request.getParameter (PASSWORD);

		// Use the security manager to validate the user's username and password.
		Object user = securityManager.login (username, password);

		if (user != null) {
			// The user has successfully logged in. Store their user object in 
			// their HttpSession. Then return true.
			session.setAttribute (USER_HANDLE, user);
			return true;
		} else {
			// The user did not successfully log in. Return false.
			return false;
		}
	}

	public void setSecurityManager (SecurityManager in) {
		log.debug ("Setting security manager using: " + in.toString () );
		this.securityManager = in;
	}*/


}
