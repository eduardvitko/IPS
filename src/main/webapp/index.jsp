<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html lang="${sessionScope.lang}">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary" >

    <div class="container-fluid">

        <a class="navbar-brand" href="#">Navbar</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <li class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="index.button.languages" />
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">

                        <li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>
                        <li><a href="?lang=ua"><fmt:message key="label.lang.ua" /></a></li>
                    </ul>
                </li>
<%--             </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link disabled">Disabled</a>--%>
<%--                </li>--%>
            </ul>
            <div>
                <a href="/registration.jsp" class="btn btn-success"><fmt:message key="index.button.signUP" /></a>
                <a href="/login.jsp" class="btn btn-dark"><fmt:message key="index.button.login" /></a>
            </div>
        </div>
    </div>
</nav>

<img src="https://img1.akspic.ru/crops/5/1/2/0/80215/80215-priroda-atmosfera-mir-zemlya-liniya-1920x1080.jpg" width="1600" height="600">
</body>

