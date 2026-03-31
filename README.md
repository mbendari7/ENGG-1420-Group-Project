# 🏛️ Campus Event Booking System

> A comprehensive, Java based desktop application for managing university events, user registrations, and automated waitlists. 

**Course:** ENGG 1420 Object Oriented Programming (Winter 2026)  
**Language:** Java  

---

## 📑 Table of Contents
* [Project Overview](#project-overview)
* [Key Features](#key-features)
* [System Architecture](#system-architecture)
* [Installation and Setup](#installation-and-setup)
* [Usage Guide](#usage-guide)
* [Testing Suite](#testing-suite)

---

## 📖 Project Overview

Managing university events, tracking venue capacities, and handling waitlists can quickly become a logistical challenge. The Campus Event Booking System is a custom built desktop application designed to solve this problem. 

It provides a seamless graphical interface for administrators to create events and for users to reserve seats. Behind the scenes, the system relies on strict Object Oriented Programming principles to manage capacity constraints, enforce user specific booking limits, and automate waitlist promotions.

---

## ✨ Key Features

The project was developed in two major phases, culminating in a robust and persistent application.

### Phase 1: Core System & Logic
* **Graphical User Interface:** A centralized, menu driven Java Swing GUI for easy navigation.
* **Polymorphic Data Models:** Distinct user types (Student, Staff, Guest) and event types (Workshop, Seminar, Concert) with unique attributes and rules.
* **Automated Waitlist Management:** When an event reaches capacity, users are automatically placed on a waitlist. If a confirmed user cancels their booking, the system automatically promotes the next person in line.

### Phase 2: Persistence & Reliability
* **Data Persistence:** The system seamlessly reads and writes all state data to local CSV files (`users.csv`, `events.csv`, `bookings.csv`) so no information is lost between sessions.
* **Autosave Interception:** Closing the application window triggers a secure autosave sequence before the program terminates.
* **Advanced Search and Filtering:** Administrators can search for specific events by title or dynamically filter the view by event type.
* **Automated Testing:** A comprehensive JUnit test suite verifies all critical business logic.

---

## 🏗️ System Architecture

The codebase is organized into distinct responsibilities to ensure the system remains clean, scalable, and easy to maintain.

### 1. The Data Models
The system uses inheritance to manage entities efficiently:
* **Users:** The base `User` class branches into `Student`, `Staff`, and `Guest`. This allows the system to enforce unique constraints, such as limiting Students to a maximum of 3 active bookings.
* **Events:** The base `Event` class extends into `Workshop`, `Seminar`, and `Concert`. Each subclass tracks its own capacity, location, date, and specific supplementary information.

### 2. The Core Engine
* **BookingManager:** This is the brain of the application. It handles all the complex logic for creating bookings, checking venue capacities, handling cancellations, and promoting users from the waitlist.

### 3. File I/O
* **DataLoader & DataSaver:** These classes handle all file operations. Upon startup, the `DataLoader` parses the CSV files to rebuild the exact state of the application. Upon exit, the `DataSaver` formats the current memory arrays back into secure CSV data.

---

## 🚀 Installation and Setup

You can run this project locally using any modern Java Integrated Development Environment (IDE) such as Visual Studio Code, IntelliJ IDEA, or Eclipse.

1. **Clone the Repository:** Download or clone this project to your local machine.
2. **Open the Project:** Launch your preferred Java IDE and open the folder containing the source code.
3. **Verify Dependencies:** Ensure you have a standard Java Development Kit (JDK) installed. 
4. **Compile and Run:** Locate the `Main.java` file in your project explorer. Run this file to launch the application.

*Note: On the very first run, the system will start fresh. Once you add users and events and close the app, the CSV files will be generated automatically in your project directory.*

---

## 💻 Usage Guide

Upon launching the application, you will be greeted by the Main Navigation Menu. From here, you can access four primary modules:

1. **User Management:** Register new individuals into the system. You must specify their ID, Name, Email, and User Type.
2. **Event Management:** Create new campus events. You will define the capacity, location, and date. You can also use the search bar at the bottom to find existing events.
3. **Booking Management:** Create a link between a User and an Event. If the event has available seats, the booking is confirmed. If not, the user is waitlisted. You can also process cancellations here.
4. **Waitlist Management:** View a chronological list of users waiting for a seat in any fully booked event.

---

## 🧪 Testing Suite

To guarantee the reliability of the booking logic, this project includes an automated test suite.

1. Ensure your IDE has the **JUnit 4** library configured in its build path.
2. Open the `BookingManagerTest.java` file.
3. Run the file as a JUnit Test. 

The suite will automatically simulate high volume booking scenarios, test capacity limits, verify waitlist promotions, and check for error handling on duplicate cancellations. All tests should pass with a green indicator.
