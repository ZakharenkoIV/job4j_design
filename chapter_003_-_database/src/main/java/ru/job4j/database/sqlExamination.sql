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
    appointment_status varchar(250) not null default 'нет ответа'
);

SELECT all_application, number_of_confirmed_users
FROM (SELECT count(*) AS all_application
      from usersMeetings) AS a
         CROSS JOIN (SELECT count(appointment_status) number_of_confirmed_users
                     FROM usersMeetings
                     WHERE appointment_status LIKE 'принял') AS b;

SELECT meetings.name AS meetings_without_applications
FROM meetings
         LEFT JOIN (SELECT DISTINCT meetingId
                    FROM usersMeetings
                    WHERE appointment_status LIKE 'принял') AS b ON meetings.id = b.meetingId
WHERE b.meetingId IS NULL;
