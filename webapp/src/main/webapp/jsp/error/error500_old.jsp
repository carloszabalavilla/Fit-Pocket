<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Ups...</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_error500.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
</head>

<body>
<div class="wrapper">
    <div class="box">
        <h1>500</h1>
        <p>Lo siento, soy yo, no eres tú.</p>
        <p>&#58;&#40;</p>
        <p><a href="<%=GlobalInfo.URL_INDEX%>">Volvamos al inicio.</a></p>
    </div>
</div>
</body>

</html>
