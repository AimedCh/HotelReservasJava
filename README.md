# HotelReservasJava 🏨✨

## Descripción del Proyecto 📋
El sistema **HotelReservasJava** es una aplicación desarrollada en Java que permite gestionar reservas de habitaciones de un hotel. Ofrece funcionalidades como registrar clientes, realizar reservas, cancelar reservas, filtrar habitaciones y generar informes de ingresos.

Este proyecto está diseñado para demostrar conceptos clave de programación orientada a objetos (POO), colecciones, manejo de fechas y validaciones de entrada.

---

## Características Principales 🚀

- **Registro de Clientes** 👤: Agrega nuevos clientes con nombre, documento y correo electrónico.
- **Reserva de Habitaciones** 🛏️: Realiza reservas seleccionando una habitación disponible y especificando fechas.
- **Cancelación de Reservas** ❌: Cancela reservas existentes y libera las habitaciones correspondientes.
- **Filtrado de Habitaciones** 🔍: Filtra habitaciones disponibles por tipo o precio.
- **Generación de Informes** 📊: Genera informes de ingresos totales por período.

---

## Estructura del Proyecto 🗂️
HotelResrvasJava/
│
├── models/ # 📦 Clases que representan entidades
│ ├── Habitacion.java
│ ├── Cliente.java
│ ├── Reserva.java
│
├── services/ # 🛠️ Clases que manejan la lógica del negocio
│ ├── GestionHabitaciones.java
│ ├── GestionClientes.java
│ ├── GestionReservas.java
│
├── utils/ # 🔧 Clases auxiliares (manejo de archivos, fechas, etc.)
│ ├── ArchivoUtils.java
│
├── Main.java # 🎯 Punto de entrada del programa
└── README.md # 📖 Documentación del proyecto


---

## Requisitos Previos ⚙️

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes programas:

- **Java JDK 8+** ☕: [Descarga aquí](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **IDE (opcional)**: Recomendado IntelliJ IDEA, Eclipse o cualquier otro IDE compatible con Java.

---

## Cómo Ejecutar el Proyecto 🏃‍♂️

1. **Clona el Repositorio**:
   ```bash
   git clone https://github.com/AimedCh/HotelReservasJava.git
   ```
   
