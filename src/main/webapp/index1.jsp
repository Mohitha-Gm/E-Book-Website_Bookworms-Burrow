<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.find" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>E-Book</title>
    <link rel="shortcut icon" href="images/book-content-regular-24.png" type="image/png"/>
    <style>
        body{
                margin:0px;
                padding:0px;
                font-family:'Montserrat';
                background-image:url('images/background.jpg');
                background-size:cover;
                position:relative;
                top:50px;
            }
    </style>
</head>
<body>
    <center>
    <%
        String search = request.getParameter("search");
        find f = new find(search);
        String key = f.gett();
        String dir = f.gett(key);
    %>
    <embed src="<%=dir%>" type="application/pdf" width="90%" height="650px">
    </center>
</body>
</html>