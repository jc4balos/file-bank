# Storage Service

This uses springboot and and MySql as a database system.

## Features

User Features:

- Add, Delete, Modify Files and Folders
- Download Files, and Folders
- Modify Profile

Admin Features:

- Add, Modify, Activate and Deactivate User
- Add, Modify, Activate and Deactivate Access Levels
- See Logs
- View and Delete Trash Files
- Modify Permissions

## Run Locally

Create a database schema on your MySQL named "file_storage"

```
CREATE DATABASE file_storage;
```

Clone the project

```bash
  git clone https://github.com/jc4balos/file-bank.git
```

Go to the project directory

```bash
  cd <my-project-directory>
```

On `storage-service\src\main\resources`, create a file named `secrets.properties` and apply configuration for the following:

```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

Clean and compile the project

```bash
  mvn clean
  mvn compile
```

Start the server (You may use springboot tools)

## API Reference

[Filebank API](https://voltesiv.postman.co/workspace/b4567ae1-ea0d-4950-aa6e-ad09758017cd)

## License

[GNU General Public License v3.0](https://choosealicense.com/licenses/gpl-3.0/#)

## Deployment

To create a build that will be used for deployment

```bash
  mvn package
```

You can now use the .jar file in running the server by using:

```
java -jar <storage-service-file-name.jar>
```
