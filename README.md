# INSTALLATION
Since this is a Spring Boot application made in IntelliJ IDEA, the easiest way to run this is to install IntelliJ IDEA and then import the project.
This is also a Maven project, meaning that once you import the project, you should run `mvn clean install` and then reload the Maven dependencies in the pom.xml.

Database information is located in `application.yaml`. The application runs on a PostgreSQL, so one must have a local database created with the data from the YAML file.

The SQL script with instructions is located in `doc` folder inside the project root folder.
