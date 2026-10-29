<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <meta http-equiv="refresh" content="3; url=<%= GlobalInfo.URL_JSP_HOME %>"/>
    <title>Acceso denegado</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_error403.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
</head>

<body>
<div class="message">No estás autorizado.
</div>
<div class="message2">Has intentado acceder a una página a la que no estás autorizado.</div>
<div class="container">
    <div class="neon">403</div>
    <div class="door-frame">
        <div class="door">
            <div class="rectangle">
            </div>
            <div class="handle">
            </div>
            <div class="window">
                <div class="eye">
                </div>
                <div class="eye eye2">
                </div>
                <div class="leaf">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
