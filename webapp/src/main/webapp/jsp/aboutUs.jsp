<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.GlobalInfo" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="insert/headTemplate.jsp" %>
    <title>Sobre Nosotros</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<%@ include file="insert/mainNav.jsp" %>
<div class="container">
    <main>
        <div class="mainBlock">
            <div class="content">
                <div class="textAbout">
                    <h3 class="pageTitle2">¡Hola!</h3>
                    <p> Somos un grupo de dos personas, Carlos y María, que estamos haciendo la
                        Formación Dual en Accenture.
                        <br/>
                        <br/>
                        Durante la dual se nos propuso hacer un proyecto en el que volcar todo el conocimiento que
                        hemos adquirido a lo largo de los meses en la formación y... ¡Aquí está!
                        <br/>
                        <br/>
                        Nuestro proyecto cuenta con una interfaz web basada en Servlets y JSP y que se conecta a una
                        base de
                        datos relacional a traves de servicio web. Está proyecto está enfocado a poder hacer
                        presupuestos para
                        que quiera
                        pueda manejar su dinero de forma más eficiente y controlada.
                    </p>
                </div>
                <div>
                    <img src="../img/aboutUs.jpg" class="aboutImg" alt="Example image"/>
                </div>
            </div>
        </div>
        <%@ include file="insert/footer.jsp" %>
    </main>
</div>
</body>

</html>
