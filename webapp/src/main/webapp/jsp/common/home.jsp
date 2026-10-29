<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Home</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>
    <main>
        <%@ include file="../insert/mainNav.jsp" %>
        <div class="mainBlock">
            <div class="content">
                <canvas id="chart"></canvas>
            </div>
        </div>
        <%@ include file="../insert/footer.jsp" %>
    </main>
</div>
<script src="<%=GlobalInfo.URL_PROYECTO%>/js/script_home.js"></script>
</body>

</html>
