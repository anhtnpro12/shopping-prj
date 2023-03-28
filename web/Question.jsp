<%-- 
    Document   : Question.jsp
    Created on : Nov 11, 2022, 9:41:57 AM
    Author     : TNA
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <form action="question">
            <c:forEach items="${list}" var="q">
                question ${q.getId()}: ${q.getQuestion()} <br>
                <input id="a" type="radio" name="ans" value="a" checked=""/>
                <label for="a">${q.getA()}</label> <br>            
                <input id="b" type="radio" name="ans" value="b" />
                <label for="b">${q.getB()}</label> <br>            
                <input id="c" type="radio" name="ans" value="c" />
                <label for="c">${q.getC()}</label> <br>            
                <input id="d" type="radio" name="ans" value="d" />
                <label for="d">${q.getD()}</label> <br>            
            </c:forEach>      
            <input type="text" name="page" value="${page+1}" style="display: none" />            
            <input type="text" name="listAns" value="${listAns}" style="display: none" />            
            <input type="submit" value="submit" />
        </form>
    </body>
</html>
