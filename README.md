Cwitter
================
A twitter clone where users should be able to signup/log in and post tweets.

Follow below steps to run this application : 

1. Install MYSQL server on your system.


1. Windows installation : http://dev.mysql.com/doc/refman/5.7/en/windows-installation.html
2. Linux/Ubuntu installation : https://help.ubuntu.com/12.04/serverguide/mysql.html

2. Create localhost connection with the help of workbeanch or with command line(username root and password root).

Command line : mysql --host=localhost --port=3306 --user=root --password=root

Windows installation : included in mysqlinstaller for windows in above link
Linux/Ubuntu installation : sudo apt-get install mysql-workbench

3. Install maven on your system

Windows installation :
Download : https://maven.apache.org/download.cgi
Install : https://maven.apache.org/install.html

Linux/Ubuntu installation : sudo apt-get install maven2

4. Create database with name cwitter in msql.

Open terminal run following commands : 
	1. mysql -uroot -proot
	2. create database cwitter;
	3. use cwitter;

5. Now navigate to cwitter folder and run following command to run application: 

mvn clean compile spring-boot:run

6. When application is up and running(On terminal it will show something like that 'Started CwitterApplication in 6.468 seconds').
Now again open terminal run following commands : 
	1. mysql -uroot -proot
	2. use cwitter
	3. insert into cwitter.hibernate_sequence value(0);
Above command is use to initialized hibernate sequence table. This is a one time command per system. After this no need to run this command if you again run this application on current system.

7. Now hit localhost:8080 in browser to see application.