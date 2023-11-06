# FindYourFather

Welcome to the FindYourFather application! This application combines a Spring Boot backend with an Angular frontend. Follow these steps to deploy the application locally.

<br>

## Prerequisites

Make sure you have the following tools installed on your system:

- Java Development Kit (JDK)
- Apache Maven
- Node.js
- Angular CLI
- PostgreSQL

<br>

## Deployment Steps

<br>

### 1. Clone the Git repository

```
git clone https://github.com/Korrigan-EISTI/findyourfather.git
```

<br>

### 2. Navigate to the project directory

```
cd FindYourFather
```

<br>

### 3. Frontend - Angular

- Navigate to the frontend directory:

```
cd frontend
```

- Install Node.js dependencies:

```
npm install
```

- Compile the Angular application in production mode:

```
ng build --configuration=production
```

The files will be generated in the `src/main/resources/static` directory of the backend.

<br>

### 4. Backend - Spring Boot

- Go back to the project directory:

```
cd ..
```

- Build the Spring Boot application:

```
mvnw clean package
```

- Run the Spring Boot application with:

```
mvnw spring-boot:run
```

- or with:

```
java -jar target\findyourfather-1.0.jar
```

Your application is now accessible at `http://localhost:8080`.

<br>

## Contribution

If you'd like to contribute to this project, feel free to open issues or submit pull requests on the [GitHub repository](https://github.com/Korrigan-EISTI/findyourfather).

Enjoy using FindYourFather!