CREATE DATABASE examination;

CREATE TABLE meetings
(
    id   serial primary key,
    name varchar(250) not null
);

CREATE TABLE users
(
    id   serial primary key,
    name varchar(250) not null
);

CREATE TABLE usersMeetings
(
    userId             int          not null references users (id),
    meetingId          int          not null references meetings (id),
    appointment_status varchar(250) not null
);

SELECT all_meeting, quantity_users
from (select count(*) from usersMeetings) as all_meeting
         cross join (select count(appointment_status)
                     from usersMeetings
                     where appointment_status LIKE 'принял') as quantity_users;

select name
from meetings
         right join (select meetingId
                     from usersMeetings
                     group by meetingId
                     having count(appointment_status) filter ( where appointment_status like 'принял') = 0) m
                    on meetings.id = m.meetingId
;
