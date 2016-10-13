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
    owner_id int references test.owners(owner_id) on delete cascade
);

