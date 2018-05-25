Description about project :
VFOX  is a personalized dashboard that can be private labeled by private wealth managers, CPAS,
estate planning attorneys, insurance advisors and more.

Purpose :
  Contain information about the installation process of backend as JAVA and frontend as Angular.


Prerequisites :
Java 1.8+
Maven 3.5+ required, but recommended
Mysql 5.x
Intellij Community
Node v9.11.1
docker 1.3x


Installations :
Frontend
Open Intellij
Go to VCS -> Checkout from Version control -> git


Backend
Open Intellij
Go to VCS -> Checkout from Version control -> git


MYSQL :
Downloading a MySQL Server Docker Image:
  docker pull mysql/mysql-server:tag
Starting a MySQL Server Instance :
  docker run --name=mysql1 -d mysql/mysql-server:tag
  shell> docker ps
  docker logs mysql1
  shell> docker logs mysql1 2>&1 | grep GENERATED
Connecting to MySQL Server from within the Container :
  docker exec -it mysql1 mysql -uroot -p
  mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'newpassword';
Container Shell Access :
  shell> docker exec -it mysql1 bash
Stopping and Deleting a MySQL Container :
  docker stop mysql1


Release history :
Release Date : 24-May-2018
Version : vfox0.1POC

Usage for -

Backend
go to vfox-restServer directory and open terminal(cmd on window).
run : mvn spring-boot:run
open rest client and request send to http://localhost:8080/ url with required parameter.

Frontend :
Go to the vfox-web directory .
The npm start command launches the server
npm start
open your browser on
http://localhost:4200/
