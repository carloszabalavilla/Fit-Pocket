<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.service.wrapper.ServiceWrapper" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../../insert/headTemplate.jsp" %>
    <title>Panel de control</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<h1>Listas de Objetos</h1>
<form method="get" action="<%=GlobalInfo.URL_PROYECTO%>/service">
    <%
        List<ServiceWrapper<?>> serviceList = (List<ServiceWrapper<?>>) request.getAttribute("serviceList");
        if (serviceList != null) {
            for (ServiceWrapper<?> serviceWrapper : serviceList) {
                String serviceName = serviceWrapper.getServiceName();
                String result = (String) request.getAttribute(serviceName);
    %>
    <label><%= serviceName %> :</label>
    <p><%= result %>
    </p>
    <br/><br/>
    <%
            }
        }
    %>
    <button type="submit">Comprobar servicios</button>
</form>
</body>

</html>
