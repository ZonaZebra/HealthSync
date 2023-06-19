# HealthSync Application

The application is designed to manage patient registration and user authentication in a pediatric doctor's office.

## Overview

The HealthSync application is built using Java and JavaFX. It utilizes a PostgreSQL database to store user and patient information. The application provides a graphical user interface (GUI) for users to register as patients, log in to their accounts, and access various features based on their roles.

## Features

The HealthSync application includes the following features:

- User Registration: Patients can register by providing their personal information, including name, password, birthday, contact information, insurance information, and pharmacy information.
- User Authentication: Users can log in to the application using their UserID and password.
- User Roles: Users are assigned roles based on their account type (Patient or Staff).
- Database Initialization: The application automatically initializes the database, creating the necessary tables for user management and patient information.
  
## Application Structure

The HealthSync application consists of the following main components:

- `com.healthsync.app`: Contains the main application class (`App`) that initializes the application and sets up the login scene.
- `com.healthsync.entities`: Contains the entity classes representing users (`User`), patients (`Patient`), and staff (`Staff`).
- `com.healthsync.service`: Contains the service classes for user registration and management (`RegistrationService`, `UserService`).
- `com.healthsync.ui`: Contains the UI classes representing scenes and views, including the base scene (`BaseScene`), login scene (`LoginScene`), and registration scene (`RegistrationScene`).
- `com.healthsync.util`: Contains utility classes for database connection and initialization, user ID generation, and password hashing.

## Usage

To run the HealthSync application, follow these steps:

1. Set up the required dependencies:
   - JavaFX: Ensure that the JavaFX libraries are available in the classpath.
   - PostgreSQL: Install PostgreSQL and configure the database connection details (`DB_URL`, `USER`, `PASSWORD`) in the `DBConnection` class.
       - By default there set to:
       -```
    DB_URL = "jdbc:postgresql://localhost:5434/postgres";
    USER = "postgres";
    PASSWORD = "password";
         ```
   - JBCrypt: Include the JBCrypt library in the classpath for password hashing.

2. Build the application using Maven: Run the Maven build command to compile and package the application. The resulting JAR file will contain all the necessary dependencies.

3. Run the application: Execute the generated JAR file to launch the HealthSync application.

4. Use the application:
   - Registration: On the login screen, click the "Register" button to access the patient registration form. Fill in the required information and click "Register" to create a new patient account.
   - Login: Enter your UserID and password on the login screen and click the "Login" button to access the application features.

## Dependencies

The HealthSync application depends on the following libraries and technologies:

- JavaFX: The JavaFX framework is used for building the graphical user interface.
- PostgreSQL: The PostgreSQL database is used for storing user and patient information.
- JBCrypt: The JBCrypt library is used for password hashing.

We appreciate your interest in the HealthSync application!
