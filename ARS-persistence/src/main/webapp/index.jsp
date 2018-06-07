<%--
    JBoss, Home of Professional Open Source
    Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
    contributors by the @authors tag. See the copyright.txt in the
    distribution for a full listing of individual contributors.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>kitchensink-jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Here we include the css file  -->
<link rel="stylesheet" type="text/css" href="resources/css/screen.css" />
</head>
<body>
    <h1 align="center">Welcome to ARS - Accommodation Reservation System!</h1>

    <div id="container">
        <div id="content">
            <!-- here we include the Registration Form in the template page
            using the jsp include directive
             -->
            <%@ include file="login.jsp"%>


        </div>
        <div id="aside">
            <%@include file="registrationForm.jsp"%>

        </div>
        <div id="footer">
            <p>
                This JBoss EAP quickstart project was generated from a Maven archetype.<br />
            </p>
        </div>
    </div>
</body>
</html>
