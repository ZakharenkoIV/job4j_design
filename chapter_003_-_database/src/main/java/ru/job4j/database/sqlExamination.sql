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

SELECT meetings.name, count(*) filter ( where usersMeetings.appointment_status like 'принял')
FROM meetings
         LEFT JOIN usersMeetings
                   ON meetings.id = usersMeetings.meetingId
group by meetings.name;

SELECT meetings.name AS meetings_without_applications
FROM meetings
         LEFT JOIN usersMeetings
                   ON meetings.id = usersMeetings.meetingId
WHERE usersMeetings.meetingId IS NULL;
