Cwitter
================
A twitter clone where users should be able to signup/log in and post tweets.

Follow below steps to run this application : 

1. Install MYSQL server on your system.

        1. Windows installation : [WINDOWS_MYSQL_INSTALLER](http://dev.mysql.com/doc/refman/5.7/en/windows-installation.html)
        2. Linux/Ubuntu installation : [UBUNTU_MYSQL_INSTALLER](https://help.ubuntu.com/12.04/serverguide/mysql.html)

2. Create localhost connection with the help of workbeanch or with command line(username root and password root).
        1. Command line : mysql --host=localhost --port=3306 --user=root --password=root
        2. Windows installation : included in mysqlinstaller for windows in above link
        3. Linux/Ubuntu installation : sudo apt-get install mysql-workbench

3. Install maven on your system

        1. Windows installation :
        2. Download : [WINDOWS_MAVEN_DOWNLOAD](https://maven.apache.org/download.cgi)
        3. Install : [WINDOWS_MAVEN_HOW_TO_INSTALL](https://maven.apache.org/install.html)
        4. Linux/Ubuntu installation : sudo apt-get install maven2

4. Create database with name cwitter in msql.

        1. Open terminal run followings : 
                1. mysql -uroot -proot
  	        2. create database cwitter;
	        3. use cwitter;

5. Now navigate to cwitter folder and run following command to run application: 

        1. mvn clean compile spring-boot:run

6. When application is up and running(On terminal it will show something like that 'Started CwitterApplication in 6.468 seconds').

        1. Now again open terminal run following commands : 
	        1. mysql -uroot -proot
        	2. use cwitter
	        3. insert into cwitter.hibernate_sequence value(0);
        2. Above command is use to initialized hibernate sequence table. This is a one time command per system. After this no need to run this command if you again run this application on current system.
7. Now hit localhost:8080 in browser to see application.