Demo DB para WSKitMedico
=========================

Este directorio contiene un script de demostración alineado con los servicios existentes en `com.cibertec.services`.

Qué contiene
- `demo.sql` — Crea la base de datos `casobd` y la tabla `TKITROBOTICA` (compatible con `BeanKit` y `WSKitMedico`).

Importación rápida (MySQL)

```bat
mysql -u root -p < docs\database\demo.sql
```

JDBC de ejemplo

```text
jdbc:mysql://localhost:3306/casobd?useSSL=false&serverTimezone=UTC
```

Notas
- `WSKitMedico` llama a `ConexionBDMySql.obtenerConexion("casobd")` — asegúrate de actualizar las credenciales en `miLib/ConexionBDMySql.java` si tu usuario/contraseña difieren.
- Si quieres que mantengamos también un demo para la gestión de eventos (tabla `evento`, `local`, etc.), puedo agregar `demo_eventos.sql` aparte.
