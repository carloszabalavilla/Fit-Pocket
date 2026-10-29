<%@ page isErrorPage="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Vaya...</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_error404.css"/>
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@600;900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <script src="https://kit.fontawesome.com/4b9ba14b0f.js" crossorigin="anonymous"></script>
</head>

<body>
<div class="mainbox">
    <div class="err">4</div>
    <i class="far fa-question-circle fa-spin"></i>
    <div class="err2">4</div>
    <div class="msg">Quizás esta página ya no está aquí... <br/>O se ha borrado... <br/>O acaso se ha escondido?<br/>Y
        si nunca llegó a existir?<p>
            Volvamos al <a href="<%=GlobalInfo.URL_INDEX%>">inicio</a> y probemos desde ahí.</p>
    </div>
</div>
</body>

</html>
