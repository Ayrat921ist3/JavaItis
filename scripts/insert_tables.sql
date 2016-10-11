-- insert into test.cars (mileage, o) values ()
insert into test.owners (city, age, name) values('Kazan', 24, 'Vasya');

insert into test.owners (city, age, name) values('Moscow', 22, 'Petya');

insert into test.owners (city, age, name) values('Kazan', 21, 'Anton');

insert into test.owners (city, age, name) values('Kazan', 20, 'Aleksey');

insert into test.owners (city, age, name) values('Moscow', 30, 'Katya');

insert into test.owners (city, age, name) values('Voronej', 21, 'Igor');

insert into test.owners (city, age, name) values('Kazan', 45, 'Grigory');

insert into test.owners (city, age, name) values('Moscow', 18, 'Anna');

insert into test.owners (city, age, name) values('Kazan', 21, 'Victor');

insert into test.owners (city, age, name) values('Murmansk', 80, 'Mitya');


-- insert int test.cars (mileage, owner_id) values (10000, 1);
-- Inserting into cars

insert into test.cars (mileage, owner_id) values (10000, 1);

insert into test.cars (mileage, owner_id) values (15000, 1);

insert into test.cars (mileage, owner_id) values (273877, 2);

insert into test.cars (mileage, owner_id) values (98712, 3);

insert into test.cars (mileage, owner_id) values (100, 4);

insert into test.cars (mileage, owner_id) values (200, 4);

insert into test.cars (mileage, owner_id) values (1230, 5);

insert into test.cars (mileage, owner_id) values (6000, 6);

insert into test.cars (mileage, owner_id) values (16000, 6);

insert into test.cars (mileage, owner_id) values (0, 7);

insert into test.cars (mileage, owner_id) values (14000, 9);

insert into test.cars (mileage, owner_id) values (1239, 10);

SELECT owners.owner_id, cars.car_id
INTO TABLE test.owner_cars FROM test.owners, test.cars
where owners.owner_id = cars.owner_id;