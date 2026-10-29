<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="edu.fpdual.webapplication.dto.Profile" %>
<%@ page import="edu.fpdual.webapplication.service.ProfileService" %>
<%@ page import="edu.fpdual.webapplication.client.ProfileClient" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Perfil</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>

</head>
<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>
    <main>
        <%@ include file="../insert/mainNav.jsp" %>
        <div class="mainBlock">
            <div class="content">
                <% Profile profile = new ProfileService(new ProfileClient()).getProfileById(thisSession.getUserId()); %>
                <img src="<%= GlobalInfo.URL_PROYECTO%>/img/userImg.png" class="profileImg" alt="Imagen de perfil"/>
                <form method="post" action="<%= GlobalInfo.URL_SERVLET_PROFILE %>">
                    <label for="userName">Nombre</label>
                    <br/>
                    <input type="text" id="userName" name="userName" value="<%= thisSession.getUserName() %>"/>
                    <br/>
                    <br/>
                    <label for="email">Correo Electrónico</label>
                    <br/>
                    <input type="email" id="email" name="email" value="<%= profile.getEmail() %>"/>
                    <br/>
                    <br/>
                    <label for="link">Página Web</label>
                    <br/>
                    <% if (profile.getLink() == null) {%>
                    <input type="url" id="link" name="link" value=""/>
                    <%} else { %>
                    <input type="url" id="link" name="link" value="<%= profile.getLink() %>"/>
                    <% } %>
                    <br/>
                    <br/>
                    <label for="location">Ubicación</label>
                    <br/>
                    <% if (profile.getLocation() == null) {%>
                    <input type="text" id="location" name="location" value=""/>
                    <%} else { %>
                    <input type="text" id="location" name="location" value="<%= profile.getLocation() %>"/>
                    <% } %>
                    <br/>
                    <br/>
                    <label for="phone">Teléfono</label>
                    <br/>
                    <% if (profile.getPhone() == 0) {%>
                    <input type="number" id="phone" name="phone" value=""/>
                    <%} else { %>
                    <input type="number" id="phone" name="phone" value="<%= profile.getPhone() %>"/>
                    <% } %>
                    <br/>
                    <br/>
                    <label for="description">Bio</label>
                    <br/>
                    <% if (profile.getPhone() == 0) {%>
                    <textarea id="description" name="description" rows="5"
                              cols="65"></textarea>
                    <%} else { %>
                    <textarea id="description" name="description" rows="5"
                              cols="65"><%= profile.getDescription() %></textarea>
                    <% } %>
                    <br/>
                    <br/>
                    <% if (request.getAttribute("error") != null) { %>
                    <p class="errorAlert"><%= request.getAttribute("error") %>
                    </p><br/>
                    <% } else if (request.getAttribute("ok") != null) { %>
                    <p><%= request.getAttribute("ok") %>
                    </p>
                    <% } %>
                    <button type="submit" name="submitBtn" value="Guardar" class="buttonC">Actualizar</button>
                </form>
            </div>
        </div>
        <%@ include file="../insert/footer.jsp" %>
    </main>
</div>
</body>

</html>
