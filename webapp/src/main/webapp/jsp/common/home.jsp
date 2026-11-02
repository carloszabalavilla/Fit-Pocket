<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Tu espacio · Fit-Pocket</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/local-refresh.css">
</head>

<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>
    <main>
        <%@ include file="../insert/mainNav.jsp" %>
        <div class="mainBlock">
            <div class="content">
                <section class="overview">
                    <p class="overview-label">TU ESPACIO / FIT-POCKET</p>
                    <h1>Tu bolsillo, con perspectiva.</h1>
                    <p>Consulta tus presupuestos, revisa los ingresos y gastos y organiza tus próximos movimientos.</p>
                    <div class="overview-actions">
                        <a class="overview-primary" href="<%=GlobalInfo.URL_SERVLET_LISTBUDGETS%>">Ver mis presupuestos →</a>
                        <a href="<%=GlobalInfo.URL_SERVLET_ADDBUDGET%>">Crear un presupuesto</a>
                    </div>
                    <p class="overview-note">Proyecto de María Garzón y Carlos Zabala · formación dual en Accenture</p>
                </section>
            </div>
        </div>
        <%@ include file="../insert/footer.jsp" %>
    </main>
</div>
</body>

</html>
