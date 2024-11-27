CREATE TABLE lessons (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    teacher VARCHAR(255) NOT NULL,
    time TIME NOT NULL
);

insert into lessons(name, teacher, time)
values ("someSubject", "somePeople", "12:37:12");

SELECT * FROM lessons 

DROP TABLE lessons




CREATE TABLE days (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    firstSubj INTEGER REFERENCES lessons(id),
    secondSubj INTEGER REFERENCES lessons(id),
    thirdSubj INTEGER REFERENCES lessons(id),
    fourthSubj INTEGER REFERENCES lessons(id)
);

insert into days(name, firstSubj, secondSubj, thirdSubj, fourthSubj)
values ("wednesday", "8", "5", "4", Null);

-- SELECT * FROM days

SELECT 
    days.name AS day_name, 
    lessons1.name AS first_subject, 
    lessons2.name AS second_subject, 
    lessons3.name AS third_subject, 
    lessons4.name AS fourth_subject
FROM days
LEFT JOIN lessons AS lessons1 ON days.firstSubj = lessons1.id
LEFT JOIN lessons AS lessons2 ON days.secondSubj = lessons2.id
LEFT JOIN lessons AS lessons3 ON days.thirdSubj = lessons3.id
LEFT JOIN lessons AS lessons4 ON days.fourthSubj = lessons4.id

-- WHERE days.id = 1;


DROP TABLE days
