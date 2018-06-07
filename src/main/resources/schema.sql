create table PASSENGER (
  ID uuid default random_uuid() PRIMARY KEY,
  FIRST_NAME VARCHAR(100) ,
  LAST_NAME VARCHAR(100),
  EMAIL VARCHAR(100)
);

create table FLIGHT (
  ID uuid default random_uuid() PRIMARY KEY,
  DEPARTURE VARCHAR(100) ,
  ARRIVAL VARCHAR(100),
  DEPARTURE_DATE VARCHAR(100),
  ARRIVAL_DATE VARCHAR(100)
);


create table BOOKING (
  ID uuid default random_uuid() PRIMARY KEY,
  PASSENGER_ID uuid,
  foreign key (PASSENGER_ID) references PASSENGER(ID),
  FLIGHT_ID uuid,
  foreign key (FLIGHT_ID) references FLIGHT(ID)
);