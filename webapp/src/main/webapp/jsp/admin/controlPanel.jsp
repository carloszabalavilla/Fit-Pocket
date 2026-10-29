<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Panel de Control</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>
    <main>
        <%@ include file="../insert/mainNav.jsp" %>
        <div class="mainBlock">

            <div class="content">
                <h3 class="pageTitle">Panel de control<br/> de administrador</h3>
                <div>
                    <form method="post" action="<%=GlobalInfo.URL_JSP_NEWADMIN%>">
                       <input type="submit" name="submitBtn" value="Create admin" class="buttonC">
                    </form>
                    <br/>
                    <form method="post" action="<%=GlobalInfo.URL_JSP_LISTS%>">
                       <input type="submit" name="submitBtn" value="Database" class="buttonC">
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="../insert/footer.jsp" %>
    </main>
</div>
</body>

</html>
