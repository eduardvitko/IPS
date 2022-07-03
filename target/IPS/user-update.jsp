
<%@ page import="com.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<jsp:useBean id="UserService" class="com.service.UserServiceImpl"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html lang="${sessionScope.lang}">
<%
   UserDto userDto = (UserDto) session.getAttribute("userDto");
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

</head>
<body>


<div class="row">
    <div class="col-sm-4">
        <form  method="POST" action="/edit/form" >
            <input type="number" class="form-control" placeholder="id" name="id" id="id" value="<%=userDto!=null?userDto.getId():""%>" hidden="hidden">

            <div alight="left">
                <label class="form-label">Phone</label>
                <input type="text" class="form-control" placeholder="phone" name="phone" id="phone" value="<%=userDto!=null?userDto.getPhone():""%>" required>
            </div>

            <div alight="left">
                <label class="form-label">Password</label>
                <input type="password" class="form-control" placeholder="password" name="password" id="password" value="<%=userDto!=null?userDto.getPassword():""%>" required>
            </div>

            <div alight="left">
                <label class="form-label">isActive</label>
                <input type="text" class="form-control" placeholder="isActive" name="isActive" id="isActive" value="<%=userDto!=null?userDto.isActive():""%>" required >
            </div>
            <div alight="left">
                <label class="form-label">Role</label>
                <input type="text" class="form-control" placeholder="role" name="role" id="role" value="<%=userDto!=null?userDto.getRole():""%>" required >
            </div>
            </br>

            <div alight="right">
                <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
            </div>

        </form>
    </div>
</div>

</body>
</html>
