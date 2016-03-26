<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.security.AccessController"%>
<%@page import="javax.security.auth.Subject"%><htmleta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin Panel</title>
    </head>
    <body>
    %>
    <h1>Admin panel</h1>

<pre>
User authenticated by JAAS is [ <%= request.getRemoteUser() %> ]  <br>
<%
    if (request.isUserInRole("superuser")) {
        out.println("and is a Super user");
    } else {
        out.println("and is an other user");
    }
%>

</pre>
    </body>
    </html>