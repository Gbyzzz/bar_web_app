-- Database: bar_db

DROP DATABASE IF EXISTS bar_db;

CREATE DATABASE bar_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1251'
    LC_CTYPE = 'English_United States.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

DROP TABLE IF EXISTS images;

CREATE TABLE images
(
    image_id bigserial PRIMARY KEY,
    name varchar(45) NOT NULL,
    filename varchar(45) NOT NULL,
    content_type varchar(45) NOT NULL,
    size bigint NOT NULL,
    bytes bytea NOT NULL
);

DROP TABLE IF EXISTS users;

CREATE TYPE user_role AS ENUM ('ADMIN', 'BARTENDER', 'USER');


CREATE TABLE users
(
    user_id bigserial PRIMARY KEY,
    username varchar(20) UNIQUE NOT NULL,
    password varchar(45) NOT NULL,
    name varchar(15) DEFAULT NULL,
    surname varchar(20) DEFAULT NULL,
    phone varchar(15) UNIQUE DEFAULT NULL,
    email varchar(256) UNIQUE NOT NULL,
    user_pic_name bigint REFERENCES images (image_id),
    role user_role NOT NULL DEFAULT 'USER',
    is_active boolean NOT NULL,
    reg_date timestamp NOT NULL
);

INSERT INTO users VALUES (1,'Admin','4d54497a','Tom','Smith','7876867845','admin@bar.com',null,'ADMIN',true,'2002-03-23'),
                         (2,'Admin1','4d54497a','John','Cole','0689984689','admin1@bar.com',null,'ADMIN',true,'2002-03-23'),
                         (3,'LuckyBartender','4d54497a','Sam','Green',null,'samgreen@bar.com',null,'BARTENDER',true,'2002-03-23'),
                         (4,'Jspm','4d54497a',null,null,null,'alcoholfan2010@gmail.com',null,'USER',true,'2002-03-23'),
                         (5,'JDaniels','4d54497a','John',null,null,'jdaniels1985@yahoo.com',null,'BARTENDER',true,'2002-03-23'),
                         (6,'Gmaster','4d54497a','Gabe',null,null,'gmaster@gmail.com',null,'USER',false,'2002-03-23'),
                         (7,'SweetAndSour','4d54497a','Julia',null,null,'juli95@gmail.com',null,'USER',true,'2002-03-23'),
                         (8,'DNegroni','4d54497a','Dan',null,null,'dnegroni@gmail.com',null,'BARTENDER',false,'2002-03-23'),
                         (9,'WhiskeySour','4d54497a','Will','Anderson',null,'wa1984@gmail.com',null,'USER',true,'2002-03-23'),
                         (10,'RayWJ','4d54497a','Ray',null,null,'raywj@gmail.com',null,'USER',true,'2002-03-23'),
                         (11,'TipsyBartender','4d54497a','John',null,null,'tipsybartender@gmail.com',null,'BARTENDER',true,'2002-03-23');
SELECT setval('users_user_id_seq', (SELECT MAX(user_id) from "users"));

DROP TABLE IF EXISTS cocktails;

CREATE TABLE cocktails
(
    cocktail_id bigserial PRIMARY KEY,
    cocktail_name varchar(20) UNIQUE NOT NULL,
    cocktail_author bigint NOT NULL REFERENCES users (user_id),
    cocktail_rating real NOT NULL DEFAULT '0',
    publication_date timestamp NOT NULL,
    image_name bigint REFERENCES  images (image_id),
    cocktail_recipe text NOT NULL,
    approx_alcohol_percentage real NOT NULL DEFAULT '0'
);


DROP TABLE IF EXISTS ingredients;

CREATE TABLE ingredients
(
    ingredient_id bigserial PRIMARY KEY,
    ingredient_name varchar(20) DEFAULT NULL UNIQUE,
    ingredient_alcohol_percentage smallint NOT NULL,
    unit_of_measurement varchar (10)
);

INSERT INTO ingredients VALUES (1,'Angostura bitter',45, 'drop(s)'),
                               (2,'Bourbon',40,'ml'),
                               (3,'Sugar cube',0,'pc(s)'),
                               (4,'Water',0,'ml'),
                               (5,'Ice',0,null),
                               (6,'White rum',40,'ml'),
                               (7,'Coconut rum',21,'ml'),
                               (8,'Dark rum',40,'ml'),
                               (9,'Orange juice',0,'ml'),
                               (10,'Pineapple juice',0,'ml'),
                               (11,'Grenadine',0,'ml'),
                               (12,'Tomato juice',0,'ml'),
                               (13,'Vodka',40,'ml'),
                               (14,'Spicy sauce',0,'drop(s)'),
                               (15,'Worcestershire sauce',0,'drop(s)'),
                               (16,'Salt',0,'pinch(es)'),
                               (17,'Pepper',0,'pinch(es)'),
                               (18,'Sugar syrup',0,'ml'),
                               (19,'Cranberries',0,'g'),
                               (20,'Coffee liqueur',20,'ml'),
                               (21,'Espresso',0,'ml'),
                               (22,'Tequila',38,'ml'),
                               (23,'Gin',40,'ml'),
                               (24,'Cointreau',40,'ml'),
                               (25,'Lemon juice',0,'ml'),
                               (26,'Cola',0,'ml'),
                               (27,'Rum with spices',40,'ml'),
                               (28,'Lime',0,'pc(s)'),
                               (29,'Soda water',0,'ml'),
                               (30,'Mint',0,'pc(s)'),
                               (31,'Lillet Rose',17,'ml'),
                               (32,'Lemon',0,'pc(s)'),
                               (33,'Powdered sugar',0,'g'),
                               (34,'Ice frappe',0,null);

SELECT setval('ingredients_ingredient_id_seq', (SELECT MAX(ingredient_id) from "ingredients"));


DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes
(
    recipe_id bigserial PRIMARY KEY,
    cocktail_id bigint NOT NULL REFERENCES cocktails (cocktail_id) ON DELETE CASCADE,
    ingredient_id bigint NOT NULL REFERENCES ingredients (ingredient_id),
    quantity_of_ingredient smallint,
    UNIQUE (cocktail_id, ingredient_id)

);

DROP TABLE IF EXISTS votes;

CREATE TABLE votes
(
    vote_id bigserial PRIMARY KEY,
    user_id bigint NOT NULL REFERENCES users (user_id),
    cocktail_id bigint NOT NULL REFERENCES cocktails (cocktail_id) ON DELETE CASCADE,
    vote_value smallint NOT NULL,
    UNIQUE (user_id, cocktail_id)
);