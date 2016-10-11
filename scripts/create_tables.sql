-- DROP TABLE IF EXISTS test.cars cascade;
CREATE TABLE test.owners
(
    owner_id serial PRIMARY KEY,
    city varchar(15) NOT NULL,
    age INT NOT NULL,
    name varchar(20)
);

CREATE TABLE test.cars
(
    car_id serial PRIMARY KEY,
    mileage INT NULL,
    owner_id int references test.owners(owner_id)
);
-- DROP TABLE IF EXISTS test.owners cascade;


-- DROP TABLE IF EXISTS test.owners_cars cascade;
/*
create table test.owners_cars
(
   	owner_id int references test.owners(owner_id),
    car_id int REFERENCES test.cars(car_id)
);*/
