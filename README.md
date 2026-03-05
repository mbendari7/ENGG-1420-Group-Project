# Campus Event Booking System

**Course:** ENGG*1420: Object-Oriented Programming, Winter 2026  

## Project Overview
The Campus Event Booking System is a Java-based application designed to model the complete lifecycle of campus events and user reservations. The system supports administrative workflows (managing users and events) alongside core booking operations, including seat reservations, cancellations, and automatic waitlist management.

This repository currently contains **Phase 1: Core System**, which demonstrates strong object-oriented design principles including class hierarchies, encapsulation, and polymorphism. 

## Phase 1 Features
* **Main Navigation GUI:** A centralized, menu-driven graphical user interface to access all system modules.
* **User Management:** Supports distinct user types (`Student`, `Staff`, `Guest`).
* **Event Management:** Supports varied event types (`Workshop`, `Seminar`, `Concert`), each with unique attributes.
* **Booking Management:** Enforces capacity constraints and user-specific booking limits.
* **Waitlist Management:** Automatically handles waitlist placements and promotes users on a strict first-come, first-served basis when confirmed seats are cancelled.

## How to Compile and Run (IDE)
The easiest way to run this project is by using a Java Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or Visual Studio Code.

1. Clone or download this repository to your local machine.
2. Open your preferred Java IDE.
3. Open or import the project folder containing all the `.java` files.
4. Locate the `Main.java` file in your IDE's project explorer.
5. Right-click on `Main.java` and select **Run** (or click the green 'Play' button at the top of your IDE).

## Navigating the Application
Upon running the program, the Main Navigation Menu will appear with four options:
1.  **User Management:** Opens the interface to register new users.
2.  **Event Management:** Opens the interface to create new events.
3.  **Booking Management:** Opens the interface to book users into events or cancel existing bookings.
4.  **Waitlist Management:** Opens the interface to view the waitlist for full events.

*(Note: Currently in Phase 1, data is stored in temporary application memory. File persistence and search functionalities will be integrated in Phase 2.)*
