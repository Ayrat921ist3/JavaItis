select car_id
from test.cars, test.owners
where owners.city = 'Kazan' and cars.owner_id = owners.owner_id;

select name
from test.owners, test.cars
where cars.mileage > 100 and owners.owner_id = cars.owner_id;

select name, car_id
from test.owners, test.cars
where cars.mileage > 50 and owners.age > 20 and owners.owner_id = cars.owner_id;