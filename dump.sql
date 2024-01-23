PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE Posts(
id integer PRIMARY KEY AUTOINCREMENT,
userid int,
time datetime,
subforumid int, text string);
CREATE TABLE Users(
id integer PRIMARY KEY AUTOINCREMENT,
name string,
joined datetime);
INSERT INTO Users VALUES(0,'Grand Lord Master King Emperor Root Admin','2024-01-16 10:10:10');
CREATE TABLE ModeratorList(
userid PRIMARY KEY);
INSERT INTO ModeratorList VALUES(0);
CREATE TABLE AdminList(
userid PRIMARY KEY);
INSERT INTO AdminList VALUES(0);
CREATE TABLE Attachements(
id integer PRIMARY KEY AUTOINCREMENT,
filename string);
CREATE TABLE Subforum(
id integer PRIMARY KEY AUTOINCREMENT,
name string);
INSERT INTO Subforum (name) VALUES('Potatoes');
CREATE TABLE PostRelations(
parentid int,
childid int,
PRIMARY KEY(parentid, childid)
);
COMMIT;
