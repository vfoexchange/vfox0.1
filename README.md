# VFOX 0.1 Installation Guide

## Description about project:
VFOX  is a personalized dashboard that can be private labeled by private wealth managers, CPAS,
estate planning attorneys, insurance advisors and more.
### Purpose
 Contain information about the installation process of backend as JAVA and frontend as Angular.
### Prerequisites
```
Java 1.8+
Maven 3.5+ required, but recommended
Mysql 5.x
Intellij Community
Node v9.11.1
docker 1.3x
```
### Installing
#### Frontend
Open Intellij
Go to VCS -> Checkout from Version control -> git
#### Backend
Open Intellij
Go to VCS -> Checkout from Version control -> git
#### MYSQL
#### Downloading a MySQL Server Docker Image

Downloading the server image in a separate step is not strictly necessary; however, performing this before you create your Docker container ensures your local image is up to date.

To download the MySQL Community Edition image, run this command:

    docker pull mysql/mysql-server:tag
                
The tag is the label for the image version you want to pull (for example,5.5,5.6,5.7,8.0,
orlatest). If:tag is omitted, thelatest label is used, and the image for the latest GA version of
MySQL Community Server is downloaded. Refer to the list of tags for available versions on
themysql/mysql-server page in the Docker Hub.

You can list downloaded Docker images with this command:

    shell> docker images
    REPOSITORY TAG IMAGE ID CREATED
    SIZE
    mysql/mysql-server latest 3157d7f55f8d 4 weeks ago
    241MB

#### Starting a MySQL Server Instance

Start a new Docker container for the MySQL Community Server with this command:

    shell>  docker run -p 3306:3306 --name=vfox-mysql -e MYSQL_ROOT_PASSWORD=vfox -e MYSQL_ROOT_HOST=% -d mysql/mysql-server:latest --default-authentication-plugin=mysql_native_password --character-set-server=latin1 --collation-server=latin1_swedish_ci

The--name option, for supplying a custom name for your server container (mysql1 in the example),
is optional; if no container name is supplied, a random one is generated. If the Docker image of the
specified name and tag has not been downloaded by an earlierdocker pull ordocker
run command, the image is now downloaded. After download completes, initialization for the
container begins, and the container appears in the list of running containers when you run
thedocker ps command; for example:

    shell>  docker ps
    CONTAINER ID IMAGE COMMAND CREATED
    STATUS PORTS NAMES
    a24888f0d6f4 mysql/mysql-server &quot;/entrypoint.sh my...&quot; 14 seconds ago Up
    13 seconds (health: starting) 3306/tcp, 33060/tcp mysql1

The container initialization might take some time. When the server is ready for use, theSTATUS of
the container in the output of thedocker ps command changes from(health:
starting) to(healthy).

The -d option used in thedocker run command above makes the container run in the background.
Use this command to monitor the output from the container:

      docker logs mysql1
           
              

#### Stopping and Deleting a MySQL Container

To stop the MySQL Server container we have created, use this command:

    docker stop mysql1
             

`docker stop` sends a SIGTERM signal to the `mysqld` process, so that the server is shut down gracefully.

Also notice that when the main process of a container (`mysqld` in the case of a MySQL Server container) is stopped, the Docker container stops automatically.

To start the MySQL Server container again:

    docker start mysql1
             

To stop and start again the MySQL Server container with a single command:

    docker restart mysql1
            

To delete the MySQL container, stop it first, and then use the `docker rm` command:

    docker stop mysql1
             
    docker rm mysql1 
           

### Release history
#### Release Date 
```
24-May-2018
```
#### Version
```
vfox0.1POC
```
## Deployment

Add additional notes about how to deploy this on a live system
### Backend
#### Go to project directory and open terminal(cmd on window).
    
     mvn clean install

#### Go to vfox-restServer directory and open terminal(cmd on window).
     mvn spring-boot:run
open rest client and request send to http://localhost:8080/ url with required parameter.
### Frontend
#### Go to the vfox-web directory.
    Install updated Node.js 
        brew install nodejs
    Install the Angular CLI globally.
        brew install angular-cli
    npm install
    npm audit fix
    npm start
open your browser on
http://localhost:4200/
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
