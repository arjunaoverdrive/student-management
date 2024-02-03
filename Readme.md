# Student Management
Spring Boot console app to manage students.

## Technologies used
Java 17, Spring Boot v 3.2.2, Spring Shell, Gradle, Docker.

### Prerequisites
Java 17 or Docker to run the app in a container.

### Installation steps
#### Local installation
1. Clone the project repository.
2. Open a terminal/CLI and navigate to the project folder.
3. Run `./gradlew build`.
4. As a result, it'll build the application, and place the resulting artefact (jar file) in the ./build/libs folder.
5. Run `java -jar ./build/libs/student-management-0.0.1-SNAPSHOT.jar`.

#### Docker
1. Clone the project repository.
2. Open a terminal/CLI and navigate to the project folder.
3. Run `./gradlew build`.
4. Run `docker build -t student-management .` to build an image.
5. Run `docker run --rm -it student-management` to run a container with the application.

### Usage
#### Available commands
* `l` -- lists all students.
* `a --n <firstname> --l <lastname> --a <age>` -- adds a new student. All fields are required.
* `rm <id>` -- removes a student by their id.
* `c` -- deletes all students.

#### Importing students
It is possible to import students entries from a file. 
For this, prepare a file (text or csv) with students' data. Each student entry should start from a new line; 
fields should be comma-separated; the order of fields:firstname,lastname,age.
Place the file to the `/src/main/java/resources` folder and specify the file name in the 
`/src/main/java/resources/application.yaml` file as the value of the `source-file` setting (default is set to 
`./init.txt`). Make sure the `enabled` setting is set to `true`.
This should be done before running the `./gradlew build` command. 

It's also possible to use a file from another location. For this, when running the Docker container specify the 
`SOURCE_FILE_PATH=<path_to_file>` parameter as a value of the `-e` option, e.g.,
`docker run --rm -it -e SOURCE_FILE_PATH=/data.txt student-management`.
