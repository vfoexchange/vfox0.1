# VFOX 0.1 Deployment Guide considering Linux server

### Purpose
 Contain information about the installation process of backend as JAVA and frontend as Angular.
### Prerequisites
```
Java 1.8+
Nginx 1.10.3
```

### Command to install java 8
    sudo apt-get install openjdk-8*

### Command to install nginx
    sudo apt-get install nginx
    
### Backend
#### Go to /opt/app/staging/backend/ directory 
    cd /opt/app/staging/backend/
    nohup java -jar vfox-restServer-0.0.1-SNAPSHOT.jar >> vfox.log &
### Frontend
#### Go to the vfox-web directory.
    Change the AWS  API URL in file vfox_web/src/app/common-services/app-constant.ts at - public ApiUrl:string = 'http://<hostname>:<port>/';

    Build  webapp  for compiled code
    ng build --prod --aot=true
    command run in the vfox_web directory. After successfully building, it will create a dist folder in your directory which contains all necessary files built from  webapp.

    Now upload all the files in the /dist folder to AWS server in below path
    /opt/app/staging/frontend/
    
    cd /etc/nginx/sites-available/  
    Change port and webapp path in default file as -
    root /opt/app/staging/frontend;
    
    sudo /etc/init.d/nginx restart
open your browser on
http://www.vfoexchange.com