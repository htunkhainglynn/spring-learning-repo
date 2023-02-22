drop table if exists COURSE;

CREATE TABLE COURSE (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name varchar(40) NOT NULL,
  level varchar(20) NOT NULL,
  duration INT NOT NULL,
  fees INT NOT NULL
);
