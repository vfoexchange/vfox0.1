# VFOX 0.1 Deployment Guide considering Linux server

### Purpose
 Contain information about the installation process of backend as JAVA and frontend as Angular.
### Prerequisites
```
ec2-instance AMI: ami-8d948ced
Java 1.8+
Nginx 1.10.3
Docker (Temporary)
```

### Command to install java 8
    sudo apt-get install openjdk-8*

### Command to install nginx
    sudo apt-get install nginx
    
### Commands to install Docker (These are temporary. We may not use Docker for MySQL in future.)
    apt-get install -y docker.io
    systemctl start docker
    systemctl enable docker
    docker pull mysql/mysql-server:latest
    docker run -p 3306:3306 --name=vfox-mysql -e MYSQL_ROOT_PASSWORD=vfox -e MYSQL_ROOT_HOST=% -d mysql/mysql-server:latest --default-authentication-plugin=mysql_native_password --character-set-server=latin1 --collation-server=latin1_swedish_ci
    
### Setting up SSL under nginx    
    Refer to this link: https://medium.com/@mrkdsgn/steps-to-install-a-go-daddy-ssl-certificate-on-nginx-on-ubuntu-14-04-ff942b9fd7ff
    mkdir /etc/nginx/ssl
    scp files from ./ssl directory to the ec2-instance and save them under /etc/nginx/ssl/
    cd /etc/nginx
    sudo chmod -R 600 ssl/    
    
    scp 'default' file from ./ngnix directory to the ec2-instace and save it under /etc/nginx/sites-available/

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

    Now upload all the files AND directories in the /dist folder to AWS server in below path.
    Note: You must use scp -r to recurse thru all directories.
    scp -r -i <pem file> dist/* ubuntu@machine:/tmp/dist
    
    /opt/app/staging/frontend/
    
    cd /etc/nginx/sites-available/  
    
    sudo /etc/init.d/nginx restart

### Test to ensure that SLL certificate is installed correctly
    Go to: https://www.ssllabs.com/ssltest/
    *Check 'Do not show the results on the boards*
    Enter Hostname: www.vfoexchange.com and click on 'Submit' button.
    You should see a 'B' grade.

### Test to ensure that application is working correctly
    Hit the home page in a browser:
        https://www.vfoexchange.com
