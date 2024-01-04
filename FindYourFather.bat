@echo off
cd frontend
echo --- Step 1: Navigating to frontend directory ---
echo.

:: Step 1: Navigate to frontend directory
cd frontend

:: Step 2: Execute 'ng build'
echo --- Step 2: Executing 'ng build' ---
echo.
start /WAIT ng build

:: Step 3: Return to the parent directory (FindYourFather)
echo --- Step 3: Returning to FindYourFather directory ---
echo.
cd ..

:: Step 4: Launch 'mvnw spring-boot:run'
echo --- Step 4: Launching 'mvnw spring-boot:run' ---
echo.
mvnw spring-boot:run