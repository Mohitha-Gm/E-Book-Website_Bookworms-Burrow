<%@ include file="login.html" %>
<%@ page import="com.signup" %>
<%@ page import="com.login" %>
<%@ page import="com.feedback" %>
<%@ page import="com.downloadnshow" %>

<%@ page import = "java.sql.*" %>
<%
      String UserName = request.getParameter("UserName");
      String Password = request.getParameter("Password");
      String Email = request.getParameter("Email");

      String lEmail = request.getParameter("log-Email");
      String lPassword = request.getParameter("confirm-PW");

      if(!(lEmail == null)){
            login lo = new login(lEmail,lPassword);
            Boolean ch=true;
            ch=lo.check();
                  if(!ch){
                        out.print("<script>document.getElementById('demo1').innerHTML='Invalid Email Or Password'</script><style>#demo1{ background-color:red;  padding:3px;    color:white;}");
                  }
                  else{
                        response.sendRedirect("Home_Page.html");
                        downloadnshow d = new downloadnshow();
                  }
      }

      if(UserName != null){
            signup sign = new signup(UserName,Password,Email,"Admin");
              Boolean ch2 = sign.check();
              if (!ch2){
                        out.print("<script>document.getElementById('demo').innerHTML='Sign Up Failed'</script><style>#demo{ background-color:red;  padding:3px;    color:white;}");
              }
      }
      String username1 = request.getParameter("username1");
      String FEmail = request.getParameter("FEmail");
      String Subject = request.getParameter("Subject");
      String Message = request.getParameter("Message");
      System.out.println(username1+" "+FEmail);
      if(username1 !=null){
            feedback fd = new feedback(username1,FEmail,Subject,Message);
            Boolean ch3 = fd.check();
            response.sendRedirect("Contact_Page.html");
            if(ch3){
                out.print("<script>alert('invalid')</script>");
            }
      }
      String search = request.getParameter("search");
      System.out.println(search);
      if (search != null){
            response.sendRedirect("index1.jsp");
      }
%>
