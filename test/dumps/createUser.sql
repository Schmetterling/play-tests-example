DROP TABLE IF EXISTS tblUser;
CREATE TABLE tblUser (
  id        int PRIMARY KEY,
  firstName varchar(50),
  lastName  varchar(50),
  email     varchar(50)
);