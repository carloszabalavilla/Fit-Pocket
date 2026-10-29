# Fit-Pocket

Aplicación de gestión financiera desarrollada por **María Garzón y Carlos Zabala** durante la formación dual en **Accenture**. Java17, Jakarta EE/Jersey, JSP y MySQL.

## Estructura y procedencia

webapp/ contiene la interfaz JSP; webservice/ la API REST; database/ el esquemaV2 y datos ficticios. Los commits de las ramas WebApp, WebService y Database se conservan como padres de la reorganización. Consulta PROVENANCE.md.

## Paquete local para Windows

Requiere Java17, Node22/24 y MySQL Server8.0 instalado. No es necesario iniciar tu servicio MySQL. Extrae el paquete y ejecuta node start.cjs (o Iniciar.cmd). Si MySQL está en otra ubicación, define MYSQL_HOME con su carpeta de instalación. El launcher crea una instancia propia en .runtime/mysql, en un puerto libre de loopback, sin modificar bases personales. Tomcat escucha en http://127.0.0.1:8083/ProyectoPrueba/.

Acceso exclusivamente ficticio: demo / DemoOnly123!. Hay presupuesto, categoría, moneda, ingreso y gasto de ejemplo. Ctrl+C detiene los dos procesos; los datos se conservan en .runtime/mysql. No expongas este entorno en Internet. Correo de contacto/recuperación necesita SMTP local adicional y no forma parte de la validación de este paquete.

## Compilar desde fuente

Con JDK17 y JAVA_HOME: mvnw.cmd clean verify (Linux/macOS: bash ./mvnw clean verify). Se generan webservice/target/webService.war y webapp/target/ProyectoPrueba.war. Despliega ambos en Tomcat10.1, con DB_URL, DB_USERNAME, DB_PASSWORD y FIT_API_URL. Importa database/01-schema.sql y database/02-demo.sql en una base vacía. El servicio y la interfaz deben usar los contextos /webService y /ProyectoPrueba.

Se corrigieron parámetros SQL de edición, contrato JSON de actualización, ResultSet forward-only y conexiones ocultas en el constructor Budget. Las pruebas vacías ya no se cuentan como evidencia. El paquete sigue siendo una demo local: falta una revisión completa de autorización, contraseñas heredadas y dependencias antes de producción.

Sustituye las credenciales históricas DB/SMTP/admin si siguen vigentes. No están incluidas como accesos de este paquete; siguen pudiendo existir en el historial.

Prueba HTTP reproducible después del arranque: `node scripts/smoke.cjs` (Node22/24). Utiliza y elimina únicamente registros ficticios de prueba.
