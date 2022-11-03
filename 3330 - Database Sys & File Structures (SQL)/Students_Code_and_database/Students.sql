CREATE TABLE IF NOT EXISTS Student(
	Name VARCHAR(10) NOT NULL,
	Student_Number INT PRIMARY KEY NOT NULL,
	Class INT NOT NULL,
	Major VARCHAR(10) NOT NULL

);

CREATE TABLE IF NOT EXISTS Courses(
	Course_Name VARCHAR(30) NOT NULL,
	Course_Num VARCHAR(15) PRIMARY KEY,
	Credit_Hrs 	INTEGER NOT NULL,
	Department VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Sections(
	Section_Indentifier INT PRIMARY KEY,
	Course_number VARCHAR(15),
	Semester VARCHAR(10),
	Years INTEGER,
	Instructor VARCHAR(20),
	FOREIGN KEY (Course_number) REFERENCES Courses(Course_Num)
);

CREATE TABLE IF NOT EXISTS Grade_Report(
	Student_Num INTEGER,
	Sec_Id Int,
	Grade Char Not NULL,
	FOREIGN KEY (Sec_Id) REFERENCES Sections(Section_Indentifier)
);

CREATE TABLE IF NOT EXISTS Prerequisite(
	Course_number VARCHAR(15),
	PreReqNum VARCHAR(15),
	FOREIGN KEY (Course_number) REFERENCES Courses(Course_Num),
	FOREIGN KEY (PreReqNum) REFERENCES Courses(Course_Num)

);