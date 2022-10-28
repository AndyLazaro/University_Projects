CREATE TABLE IF NOT EXISTS Employee(
	fname	VARCHAR(15) NOT NULL,
	mint	CHAR,
	lname	VARCHAR(15) NOT NULL,
	ssn		char(9) PRIMARY KEY NOT NULL,
	--poggers
	address varchar(30),
	sex CHAR,
	salary	NUMERIC(10,2),
	superssn CHAR(9),
	dno INTEGER NOT NULL,
	
	
	FOREIGN KEY (dno) REFERENCES Department(dnumber),
	FOREIGN KEY (superssn) REFERENCES Employee(ssn)
	
	
);

CREATE TABLE IF NOT EXISTS Department(
	Dname	varchar(15) NOT NULL,
	Dnumber	INTEGER PRIMARY KEY NOT NULL,
	Mgr_snn	char(9)	NOT NULL,
	Mgr_start_date	DATE,
	
	
	
	UNIQUE(Dname),
	FOREIGN KEY (Mgr_snn) REFERENCES Employee(ssn)
	
);