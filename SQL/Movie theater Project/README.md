This folder contains the first sql project done in class. We were tasked with creating the ER diagram of a Movie Theater and a database where customers could see movies
in their area, search by city, cinema, and time slot and reserve a seat

constraints:
  each ticket must be unique to the cinema, screen room, and movie session
  each movie session has to be unique between rooms, and cinemas
      (ex: gladiator cannot have 2 entries at cinema prism at 12pm - 2pm in room 2. it can be in rooms 1 and 2)
  each cinema must have 3 screening rooms
  each screening room has 10 seats avalible
  customers cannot 
