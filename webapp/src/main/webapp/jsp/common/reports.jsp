<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>
    <%@ include file="../insert/headTemplate.jsp" %>
    <title>Informes</title>
    <link rel="stylesheet" href="<%=GlobalInfo.URL_PROYECTO%>/css/style_v2.css"/>
</head>

<body>
<div class="container">
    <%@ include file="../insert/header.jsp" %>
    <main>
        <%@ include file="../insert/mainNav.jsp" %>
        <div class="mainBlock">
            <div class="contentList">
                <h3 class="pageTitle1">Filtro de Fechas</h3>
                <br/>
                <form id="filterForm" class="form">
                    <label for="startDate">Fecha de inicio:</label>
                    <br/>
                    <input type="date" id="startDate" name="startDate">
                    <br/>
                    <br/>
                    <label for="endDate">Fecha de fin:</label>
                    <br/>
                    <input type="date" id="endDate" name="endDate">
                    <br/>
                    <br/>
                    <button type="submit" class="buttonC">Filtrar</button>
                </form>
                <br/>
                <br/>
                <h3 class="pageTitle1">Gastos</h3>
                <br/>
                <table id="expensesTable">
                    <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Descripción</th>
                        <th>Monto</th>
                        <th>Categoria</th>
                    </tr>
                    </thead>
                    <tbody id="expensesBody">
                    <!-- Aquí se mostrarán los gastos filtrados por fecha -->
                    </tbody>
                </table>
                <br/>
                <br/>
                <h3 class="pageTitle1">Ingresos</h3>
                <br/>
                <table id="incomeTable">
                    <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Descripción</th>
                        <th>Monto</th>
                        <th>Categoria</th>
                    </tr>
                    </thead>
                    <tbody id="incomeBody">
                    <!-- Aquí se mostrarán los ingresos filtrados por fecha -->
                    </tbody>
                </table>
                <% if (request.getAttribute("error") != null) { %>
                <p class="errorAlert"><%= request.getAttribute("error") %>
                </p><br/>
                <% } else if (request.getAttribute("ok") != null) { %>
                <p><%= request.getAttribute("ok") %>
                </p><br/>
                <% } %>
                <br/>
                <form id="generatePdf" action="<%=GlobalInfo.URL_SERVLET_GENERATEREPORTPDF%>" method="GET">
                    <button type="submit">Generar reporte</button>
                </form>
            </div>
        </div>
        <%@ include file="../insert/footer.jsp" %>
    </main>
</div>

<script>
    const filterForm = document.getElementById('filterForm');
    const expensesBody = document.getElementById('expensesBody');
    const incomeBody = document.getElementById('incomeBody');

    filterForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const startDate = document.getElementById('startDate').value;
        const endDate = document.getElementById('endDate').value;

        // Aquí debes implementar la lógica para obtener los gastos e ingresos filtrados por fecha
        // Puedes hacer una solicitud AJAX a un servidor o utilizar datos en el lado del cliente

        // Ejemplo de cómo añadir datos a la tabla de gastos
        const expenseData = [
            { fecha: '2023-06-01', descripcion: 'Compra del hogar', monto: 100, categoria: 'Hogar' },
            { fecha: '2023-06-02', descripcion: 'Vuelos', monto: 150, categoria: 'Viaje' },
            { fecha: '2023-06-03', descripcion: 'Casa rural', monto: 200, categoria: 'Ocio' }
        ];

        expensesBody.innerHTML = '';
        expenseData.forEach(function(expense) {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${expense.fecha}</td>
                <td>${expense.descripcion}</td>
                <td>${expense.monto}</td>
                <td>${expense.categoria}</td>
            `;
            expensesBody.appendChild(row);
        });

        // Ejemplo de cómo añadir datos a la tabla de ingresos
        const incomeData = [
            { fecha: '2023-06-01', descripcion: 'Ahorro Dubai', monto: 500, categoria: 'Viaje'},
            { fecha: '2023-06-02', descripcion: 'Casa rural', monto: 300, categoria: 'Ocio'},
            { fecha: '2023-06-03', descripcion: 'Verano', monto: 200, categoria: 'Viaje'}
        ];

        incomeBody.innerHTML = '';
        incomeData.forEach(function(income) {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${income.fecha}</td>
                <td>${income.descripcion}</td>
                <td>${income.monto}</td>
                <td>${income.categoria}</td>

            `;
            incomeBody.appendChild(row);
        });
    });
</script>

</body>

</html>
