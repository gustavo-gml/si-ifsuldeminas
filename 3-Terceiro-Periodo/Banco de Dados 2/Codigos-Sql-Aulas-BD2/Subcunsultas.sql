USE sakila;

-- 1
SELECT title, rental_duration
FROM film
where rental_duration = (SELECT MAX(rental_duration) FROM film);


-- 2
SELECT first_name, title, length
FROM actor as a
join film_actor as fa ON a.actor_id = fa.actor_id 
join film on fa.film_id = film.film_id
where length >(SELECT AVG(length) FROM film);

-- 3 
SELECT first_name, amount
FROM customer as c
join payment as p on c.customer_id = p.customer_id
WHERE amount > (SELECT AVG(amount) FROM payment)
Order by amount desc;

-- 4
use world;
SELECT Name, Continent, Code
FROM country 
where Population = (select max(population) from country);


-- 5 
SELECT Name, Continent, Population
FROM country 
where Population > (select avg(population) from country);

-- 6
select ci.Name, co.Name
from city as ci
join country as co on ci.CountryCode = co.Code
where LifeExpectancy = (select min(LifeExpectancy) from country);

-- 7
select ci.Name, co.Name
from city as ci
join country as co on ci.CountryCode = co.Code
where LifeExpectancy = (select max(LifeExpectancy) from country);

-- 8
SELECT Name, Continent 
FROM country
JOIN countrylanguage ON Code = CountryCode
GROUP BY Name, Continent
HAVING COUNT(Language) = (
    SELECT COUNT(Language) 
    FROM countrylanguage 
    GROUP BY CountryCode 
    ORDER BY COUNT(Language) DESC 
    LIMIT 1
)
ORDER BY Name ASC;


create view paises_com_mais_idiomas As
SELECT Name, Continent 
FROM country
JOIN countrylanguage ON Code = CountryCode
GROUP BY Name, Continent
HAVING COUNT(Language) = (
    SELECT COUNT(Language) 
    FROM countrylanguage 
    GROUP BY CountryCode 
    ORDER BY COUNT(Language) DESC 
    LIMIT 1
)
ORDER BY Name ASC;
