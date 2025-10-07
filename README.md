# SistemaEventos - Servicio Web

Proyecto final DSWII: sistema de gestión de eventos, locales, participantes y proveedores.

Stack:
- Java (Axis2 SOAP Web Services)
- MySQL
- Estructura preparada para despliegue en Tomcat/Axis2

Descripción corta:
Este repositorio contiene el código fuente del proyecto desarrollado en la asignatura DSWII. Incluye beans para `Evento`, `Local`, `Participante`, `Proveedor`, servicios web (`WSEvento`, `WSLocal`, `WSParticipante`, `WSProveedor`) y la conexión a la base de datos (`ConexionBDMySql`).

Cómo probar (resumen):
1. Configurar la base de datos MySQL y actualizar credenciales en `miLib/ConexionBDMySql.java`.
2. Construir el proyecto o copiar las clases a la carpeta `WEB-INF/classes` de un WAR.
3. Desplegar en Tomcat con Axis2 y subir los servicios web.

Notas:
- El push al remoto en GitHub puede requerir autenticación (token o SSH). Si el push falla por permisos, utiliza un token de acceso personal (PAT) o configura una clave SSH.

Autor: Diego Peregrino

---
(README creado automáticamente para inicializar el repositorio local y hacer el primer push.)
