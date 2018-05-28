# VFOX 0.1 Installation Guide

## Description about project:
VFOX  is a personalized dashboard that can be private labeled by private wealth managers, CPAS,
estate planning attorneys, insurance advisors and more.
### Purpose
 Contain information about the installation process of backend as JAVA and frontend as Angular.
### Prerequisites
```
Java 1.8+
```
```
Maven 3.5+ required, but recommended
```
```
Mysql 5.x
```
```
Intellij Community
```
```
Node v9.11.1
```
```
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
                
             

Refer to the list of supported tags above. If `:tag
            ` is omitted, the `latest` tag is used, and the image for the latest GA version of MySQL Server is downloaded.

#### Starting a MySQL Server Instance

Start a new Docker container for the MySQL Community Server with this command:

    docker run --name=mysql1 -d mysql/mysql-server:tag
                 
             

The `--name` option, for supplying a custom name for your server container (`mysql1` in the example), is optional; if no container name is supplied, a random one is generated. If the Docker image of the specified name and tag has not been downloaded by an earlier `docker pull` or `docker run` command, the image is now downloaded. After download completes, initialization for the container begins, and the container appears in the list of running containers when you run the `docker ps` command; for example:

    shell> docker ps
    CONTAINER ID   IMAGE                COMMAND                  CREATED             STATUS                              PORTS                NAMES
    a24888f0d6f4   mysql/mysql-server   "/entrypoint.sh my..."   14 seconds ago      Up 13 seconds (health: starting)    3306/tcp, 33060/tcp  mysql1 

The container initialization might take some time. When the server is ready for use, the `STATUS` of the container in the output of the `docker ps` command changes from `(health: starting)` to `(healthy)`.

The `-d` option used in the `docker
        run` command above makes the container run in the background. Use this command to monitor the output from the container:

       docker logs mysql1
                

Once initialization is finished, the command's output is going to contain the random password generated for the root user; check the password with, for example, this command:

    shell> docker logs mysql1 2>&1 | grep GENERATED
    GENERATED ROOT PASSWORD: Axegh3kAJyDLaRuBemecis&EShOs

#### Connecting to MySQL Server from within the Container

Once the server is ready, you can run the `mysql` client within the MySQL Server container you just started and connect it to the MySQL Server. Use the `docker exec -it` command to start a `mysql` client inside the Docker container you have started, like this:

       docker exec -it mysql1 mysql -uroot -p
                

When asked, enter the generated root password (see the instructions above on how to find it). Because the `MYSQL_ONETIME_PASSWORD` option is true by default, after you started the server container with the sample command above and connected a `mysql` client to the server, you must reset the server root password by issuing this statement:

    mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
                

Substitute `newpassword` with the password of your choice. Once the password is reset, the server is ready for use.

#### Container Shell Access

To have shell access to your MySQL Server container, use the `docker exec -it` command to start a bash shell inside the container:

    shell> docker exec -it mysql1 bash 
    bash-4.2#    

You can then run Linux commands inside the container at the bash prompt.

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
             

If you want the [Docker volume for the server's data directory](https://dev.mysql.com/doc/refman/5.7/en/docker-mysql-more-topics.html#docker-persisting-data-configuration) to be deleted at the same time, add the `-v` option to the `docker rm` command.

#### More Topics on Deploying MySQL Server with Docker

For more topics on deploying MySQL Server with Docker like server configuration, persisting data and configuration, and server error log, see [More Topics on Deploying MySQL Server with Docker](https://dev.mysql.com/doc/refman/5.7/en/docker-mysql-more-topics.html) in the MySQL Server manual.

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
Go to vfox-restServer directory and open terminal(cmd on window).
run : mvn spring-boot:run
open rest client and request send to http://localhost:8080/ url with required parameter.
### Frontend
Go to the vfox-web directory .
The npm start command launches the server
npm start
open your browser on
http://localhost:4200/
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
