#Create cwitter database before running application
create database cwitter;
use cwitter;

#Run after application up(one time)
insert into cwitter.hibernate_sequence value(0);

select * from cwitter.user;