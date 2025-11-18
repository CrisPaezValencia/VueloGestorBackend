# VueloGestorBackend

Descripción
VueloGestorBackend es el backend del sistema Gestor de Vuelos, desarrollado en Java y estructurado bajo los principios de la arquitectura hexagonal (Ports & Adapters). Su objetivo principal es proporcionar una API REST robusta y escalable para la gestión de usuarios, clientes, pilotos y vuelos, permitiendo la integración con diferentes frontends y servicios externos.

Estructura del Proyecto
adapter/databases.sql
Incluye las entidades que representan las tablas de la base de datos, los mappers para transformar datos entre modelos y entidades, y los adapters de repositorio que implementan la lógica de acceso a datos.

core
Contiene el modelo de dominio, los puertos (interfaces) y los servicios que encapsulan la lógica de negocio, asegurando la independencia respecto a detalles de infraestructura.

entrypoint.rest
Define los controladores REST que exponen los endpoints de la API, los DTOs (objetos de transferencia de datos) para estructurar las solicitudes y respuestas, y el manejador global de excepciones para una gestión uniforme de errores.

Características principales
API RESTful para la gestión de usuarios, clientes, pilotos y vuelos.
Separación clara de responsabilidades gracias a la arquitectura hexagonal.
Uso de DTOs para una comunicación segura y estructurada entre frontend y backend.
Manejo global de errores para respuestas consistentes y amigables.
Fácilmente extensible y mantenible, permitiendo cambios en la infraestructura sin afectar la lógica de negocio.
Resumen
Cada módulo está diseñado para facilitar la mantenibilidad, la escalabilidad y la facilidad de pruebas.
La arquitectura hexagonal permite que el sistema sea fácilmente extensible, pudiendo cambiar la base de datos o añadir nuevas interfaces sin afectar la lógica central.

En resumen, este backend proporciona todas las operaciones necesarias para la gestión integral de vuelos, usuarios, clientes y pilotos, asegurando buenas prácticas de diseño y una clara separación de responsabilidades.
