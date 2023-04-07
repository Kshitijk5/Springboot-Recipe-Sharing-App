# Recipe Sharing App
This is a recipe sharing app built with Spring Boot. Users can share their own recipes and view recipes shared by others. The app uses Spring Security with Basic Authentication to authenticate users and restrict access to certain resources. There are two roles: ROLE_USER and ROLE_ADMIN.

# Requirements
* Java 8 or later
* Maven
* MySQL

# Installation
* Clone the repository: git clone https://github.com/Kshitijk5/Springboot-Recipe-Sharing-App.git
* Open the project directory: cd recipe-sharing-app
* Update the application.properties file with your database credentials.
* Build the project: mvn clean package
* Run the project: java -jar target/recipe-sharing-app-0.0.1-SNAPSHOT.jar

# Usage
Once the application is running, you can access it in your web browser at http://localhost:8080.

# User Registration
Users can register for an account by heading over to http://localhost:8080/auth/signup. After registering, users can log in using their email address and password from http://localhost:8080/signin .

# Recipe Sharing
Logged-in users can share their own recipes by clicking the "Share Recipe" button on the home page. They will be prompted to enter the recipe name, ingredients, and instructions. After submitting the recipe, it will be added to the list of shared recipes.

# Recipe Viewing
All users can view shared recipes by accessing http://localhost:8080/recipe. 

# Resource Access
Logged-in users can only edit and delete the recipes they own. Users with the ROLE_ADMIN role can edit and delete any recipe.

# Contributing
If you would like to contribute to the project, please fork the repository and submit a pull request.

# License
This project is licensed under the MIT License.
