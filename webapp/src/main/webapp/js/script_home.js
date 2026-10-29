// Datos de ejemplo
var presupuestoSeleccionado = "Presupuesto 1";
var datosSemanales = [
    {semana: "Semana 1", gastos: 500, ingresos: 1000},
    {semana: "Semana 2", gastos: 800, ingresos: 1200},
    {semana: "Semana 3", gastos: 600, ingresos: 900},
    {semana: "Semana 4", gastos: 700, ingresos: 1100}
];

// Obtener las etiquetas y valores para el gráfico
var semanas = datosSemanales.map(dato => dato.semana);
var gastos = datosSemanales.map(dato => dato.gastos);
var ingresos = datosSemanales.map(dato => dato.ingresos);

// Configurar el gráfico de barras agrupadas
var ctx = document.getElementById("chart").getContext("2d");
var chart = new Chart(ctx, {
    type: "bar",
    data: {
        labels: semanas,
        datasets: [
            {
                label: "Gastos",
                backgroundColor: "rgba(255, 99, 132, 0.5)",
                data: gastos
            },
            {
                label: "Ingresos",
                backgroundColor: "rgba(75, 192, 192, 0.5)",
                data: ingresos
            }
        ]
    },
    options: {
        responsive: true,
        scales: {
            x: {
                stacked: false
            },
            y: {
                stacked: false
            }
        }
    }
});

// Actualizar el gráfico cuando se seleccione un presupuesto en la lista
function actualizarGrafico() {
    // Obtener los datos del presupuesto seleccionado (por ejemplo, mediante AJAX)

    // Simular datos de ejemplo
    var nuevoPresupuesto = "Presupuesto 2";
    var nuevosDatosSemanales = [
        {semana: "Semana 1", gastos: 400, ingresos: 900},
        {semana: "Semana 2", gastos: 700, ingresos: 1100},
        {semana: "Semana 3", gastos: 500, ingresos: 800},
        {semana: "Semana 4", gastos: 600, ingresos: 1000}
    ];

    // Actualizar las etiquetas y valores del gráfico
    semanas = nuevosDatosSemanales.map(dato => dato.semana);
    gastos = nuevosDatosSemanales.map(dato => dato.gastos);
    ingresos = nuevosDatosSemanales.map(dato => dato.ingresos);

    // Actualizar los datos del gráfico
    chart.data.labels = semanas;
    chart.data.datasets[0].data = gastos;
    chart.data.datasets[1].data = ingresos;

    // Actualizar el título del gráfico con el nuevo presupuesto
    chart.options.plugins.title.text = nuevoPresupuesto;

    // Redibujar el gráfico
    chart.update();
}

// Ejecutar la función de actualización al cargar la página
actualizarGrafico();
