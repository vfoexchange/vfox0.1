# VFOX 0.1 Deployment Guide considering Linux server

### Purpose
 Contain information about the installation process of backend as JAVA and frontend as Angular.
### Prerequisites
```
ec2-instance AMI: ami-8d948ced
Java 1.8+
Nginx 1.10.3


apt-get install -y docker.io
systemctl start docker
systemctl enable docker
docker pull mysql/mysql-server:latest
docker run -p 3306:3306 --name=vfox-mysql -e MYSQL_ROOT_PASSWORD=vfox -e MYSQL_ROOT_HOST=% -d mysql/mysql-server:latest --default-authentication-plugin=mysql_native_password --character-set-server=latin1 --collation-server=latin1_swedish_ci


```

### Command to install java 8
    sudo apt-get install openjdk-8*

### Command to install nginx
    sudo apt-get install nginx
    
### Setting up SSL under nginx    
    scp files from ./ssl directory to the ec2-instance and save them under /home/ubuntu/ssl
    scp 'default' file from ./ngnix directory to the ec2-instace and save it under /etc/nginx/sites-available/

### Test to ensure that SLL certificate is installed correctly
    Go to: https://www.ssllabs.com/ssltest/
    *Check 'Do not show the results on the boards*
    Enter Hostname: www.vfoexchange.com and click on 'Submit' button. 

### Backend
#### Go to /opt/app/staging/backend/ directory (Create this directory strucutre)
    cd /opt/app/staging/backend/
    nohup java -jar vfox-restServer-0.0.1-SNAPSHOT.jar >> vfox.log &
### Frontend
#### Go to the vfox-web directory.
    Change the AWS  API URL in file vfox_web/src/app/common-services/app-constant.ts at - public ApiUrl:string = 'http://<hostname>:<port>/';

    Build  webapp  for compiled code
    ng build --prod --aot=true
    command run in the vfox-web directory. After successfully building, it will create a dist folder in your directory which contains all necessary files built from  webapp.

    Now upload all the files in the /dist folder to AWS server in below path
    /opt/app/staging/frontend/
    
    cd /etc/nginx/sites-available/  
    Change port and webapp path in default file as -
    root /opt/app/staging/frontend;
    
    sudo /etc/init.d/nginx restart
open your browser on
http://www.vfoexchange.com