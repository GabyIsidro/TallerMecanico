# 🔧 Backend - Sistema de Gestión de Taller Mecánico

Este repositorio contiene el código fuente del backend para un sistema integral de gestión de talleres mecánicos. El proyecto nace de una necesidad real: optimizar y digitalizar la administración de un taller familiar especializado en autos, camiones y camionetas.
📖 Idea de Negocio y Propósito

  > El objetivo principal de esta aplicación es resolver los problemas organizativos típicos de un taller mecánico en crecimiento. Actualmente, la gestión manual o en papel dificulta el seguimiento de los trabajos y el control de los clientes.

### Este sistema permite:

  > Gestión de Vehículos: Registro detallado de unidades de diferente porte (autos, utilitarios, camiones).

  > Historial de Reparaciones: Seguimiento de los servicios realizados a cada vehículo para un mejor mantenimiento preventivo.

  > Administración de Clientes: Base de datos de dueños y empresas.

  > Control de Órdenes de Trabajo: Estado de las reparaciones (Pendiente, En Proceso, Terminado, Entregado).

## 🏗️ Arquitectura del Sistema

El proyecto sigue una Arquitectura en Capas (Layered Architecture) para asegurar la separación de responsabilidades, la escalabilidad y facilitar el mantenimiento del código.

El flujo de datos se estructura de la siguiente manera:

  > Controller Layer (Controladores): Maneja las peticiones HTTP (REST API) y define los endpoints de entrada.

  > Service Layer (Servicios): Contiene la lógica de negocio pura (validaciones, cálculos, reglas del taller).

  > Repository/Data Access Layer (Persistencia): Se encarga de la comunicación con la base de datos.

  > Model/Domain Layer (Entidades): Representa los objetos del negocio (Vehículo, Cliente, Orden, Repuesto).

## 🚀 Tecnologías Utilizadas
### Lenguajes y Frameworks

  >  Lenguaje: [Java 25]

  > Framework: [Spring Boot 3 / Spring Data JPA / Spring Web]

  > Gestión de Dependencias: [Maven]

### Base de Datos

  > Motor:  [MySQL]

  > ORM: [Hibernate / JPA] para el mapeo objeto-relacional.

### Herramientas y Utilidades

  > Control de Versiones: Git & GitHub.

  > Pruebas (Testing): [Postman].

## 🔌 Endpoints Principales (API REST)

A continuación se describen algunos de los recursos disponibles en la API:
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/clientes` | Listar todos los clientes. |
| `POST` | `/api/vehiculos` | Registrar un nuevo auto o camión. |
| `GET` | `/api/vehiculos/{id}/historial` | Ver reparaciones pasadas de un vehículo. |
| `POST` | `/api/ordenes` | Crear una nueva orden de trabajo. |
| `PUT` | `/api/ordenes/{id}/estado` | Actualizar el estado de una reparación. |

## 🛠️ Instalación y Configuración

### Sigue estos pasos para correr el proyecto en tu entorno local:

>   Clonar el repositorio:

    git clone https://github.com/tu-usuario/taller-mecanico-backend.git

>   Configurar la Base de Datos:

        Crea una base de datos llamada taller_db (o el nombre que uses).

        Configura las credenciales en application.properties o application.yml.

>   Compilar y Ejecutar:

    ./mvnw spring-boot:run

  (O ejecútalo directamente desde tu IDE favorito como IntelliJ o Eclipse).

## 👥 Contribución

Este es un proyecto personal con fines académicos y de implementación real. Si deseas sugerir mejoras o detectar errores, por favor abre un Issue o envía un Pull Request.

## Desarrollado por: Gabriel Isidro Garcia
### Estudiante de Analista en Informática
