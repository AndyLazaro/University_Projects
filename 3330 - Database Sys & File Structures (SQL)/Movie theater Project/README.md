This folder contains the first sql project done in class. We were tasked with creating the ER diagram of a Movie Theater and a database where customers could see movies
in their area, search by city, cinema, and time slot and reserve a seat

constraints:

  - each ticket must be unique to the cinema, screen room, and movie session
  - each movie session has to be unique between rooms, and cinemas
    - (ex: gladiator cannot have 2 entries at cinema prism at 12pm - 2pm in room 2. it can be in rooms 1 and 2)
  - each cinema must have 3 screening rooms
  - each screening room has 10 seats avalible
  - customers cannot reserve seats that are taken

Folder contains these files:


1. The tools used for this project were NotePad++ for programming the SQL Create table statements and SQLite3
    to insert the data to the database and create the SELECT statements. In addition to lucidcharts and mspaint 
    to create the ER Diagram.



2. Open ER_Diagram.jpeg for ER Diagram of our database



3. Open Movies.sql for the source code of the CREATE Statements



4. The data was inserted into the database using ".mode csv" and using the excel files to insert the data
    using the sql function .import "file_Path.csv" "table_name" and then using the update function to fix
    some inconsistencies with quotation marks in certain tables



5. Open SQL Queries.docx for the SELECT Statements and the output



6. Contributions
    - ANDY LAZARO    - Creating the database and loading the data
    - AIDEN MONDRESS - Creating the ER Diagram for the database
    - ANDY LAZARO    - Creating the SELECT Queries for the database
