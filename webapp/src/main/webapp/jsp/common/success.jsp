<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Acción Realizada con Éxito</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<section class="boxBody">
    <div>
        <h3 class="H3Box2">Operación realizada <br/>con éxito</h3>
    </div>
    <br/>
    <form method="POST" action="<%=GlobalInfo.URL_JSP_HOME%>">
        <input type="submit" value="Volver" class="buttonA"/>
    </form>
</section>
</body>

</html>
