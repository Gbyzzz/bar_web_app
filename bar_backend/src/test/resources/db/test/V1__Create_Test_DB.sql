DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS cocktails;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS votes;


CREATE TABLE images
(
    image_id     bigserial PRIMARY KEY,
    name         varchar(45) NOT NULL,
    filename     varchar(45) NOT NULL,
    content_type varchar(45) NOT NULL,
    size         bigint      NOT NULL,
    bytes        bytea       NOT NULL
);


CREATE TYPE user_role AS ENUM ('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER');


CREATE TABLE users
(
    user_id  bigserial PRIMARY KEY,
    username varchar(20) UNIQUE  NOT NULL,
    password varchar(100) NOT NULL,
    name     varchar(15) DEFAULT NULL,
    surname  varchar(20) DEFAULT NULL,
    phone    varchar(15) UNIQUE DEFAULT NULL,
    email    varchar(256) UNIQUE NOT NULL,
    user_pic bigint REFERENCES images (image_id),
    role     user_role NOT NULL DEFAULT 'ROLE_USER',
    enabled  boolean NOT NULL DEFAULT 'false',
    reg_date timestamp NOT NULL
);

INSERT INTO users
VALUES (1, 'Admin', '132435', 'Tom', 'Smith',
        '7876867845', 'admin@bar.com', null, 'ROLE_ADMIN', true, '2022-10-25'),
       (2, 'WhiskeySour', '132435', 'Will', 'Anderson',
        null, 'wa1984@gmail.com', null, 'ROLE_USER', true, '2022-10-25');
SELECT setval('users_user_id_seq', (SELECT MAX(user_id) from "users"));


CREATE TABLE cocktails
(
    cocktail_id               bigserial PRIMARY KEY,
    cocktail_name             varchar(20) UNIQUE NOT NULL,
    cocktail_author           bigint             NOT NULL REFERENCES users (user_id),
    cocktail_rating           real               NOT NULL DEFAULT '0',
    publication_date          timestamp          NOT NULL,
    cocktailImage                     bigint REFERENCES images (image_id),
    cocktail_recipe           text               NOT NULL,
    approx_alcohol_percentage real               NOT NULL DEFAULT '0'
);

INSERT INTO cocktails
VALUES (1, 'Old Fashioned', 1, 5, '2022-10-25 00:00:00', null, 'В стакан олд фэшн кладём кусочек сахара, капаем на него Ангостуру и воду.' ||
                                                      'При помощи мадлера измельчаем сахар, превращая в некое подобие сиропа на дне бокала.' ||
                                                      'Наполняем стакан льдом и добавляем половину бурбона.' ||
                                                      'Тщательно перемешиваем с помощью барной ложки, охлаждая бокал и обводняя смесь.' ||
                                                      'Добавляем ещё льда и наливаем оставшуюся половину бурбона.' ||
                                                      'Снова перемешиваем.' ||
                                                      'Сбрызгиваем цедрой апельсина.' ||
                                                      'Подаём без украшения.', 33),
       (2, 'Long Island Ice Tea', 2, 5, '2022-10-25 00:00:00', null, 'Наполняем бокал льдом и до половины — колой.' ||
                                                            'Наполняем шейкер льдом и наливаем туда водку, джин, ром, текилу и трипл сек.' ||
                                                            'Взбиваем в шейкере.' ||
                                                            'Добовляем в стакан с колой лимонный фреш.' ||
                                                            'Выливаем в стакан с колой содержимое шейкера, используя стрейнер, чтобы лишний лёд не попал.' ||
                                                            'Стараемся, чтобы алкогольная смесь не перемешалась с колой.' ||
                                                            'Украшаем бокал зонтиком или чем угодно ещё — это же Лонг Айленд', 20);

SELECT setval('cocktails_cocktail_id_seq', (SELECT MAX(cocktail_id) from "cocktails"));


CREATE TABLE ingredients
(
    ingredient_id                 bigserial PRIMARY KEY,
    ingredient_name               varchar(20) DEFAULT NULL UNIQUE,
    ingredient_alcohol_percentage smallint NOT NULL,
    unit_of_measurement           varchar(10)
);

INSERT INTO ingredients
VALUES (1, 'Angostura bitter', 45, 'drop(s)'),
       (2, 'Bourbon', 40, 'ml'),
       (3, 'Sugar cube', 0, 'pc(s)'),
       (4, 'Water', 0, 'ml'),
       (5, 'Ice', 0, null),
       (6, 'White rum', 40, 'ml'),
       (7, 'Vodka', 40, 'ml'),
       (8, 'Tequila', 38, 'ml'),
       (9, 'Gin', 40, 'ml'),
       (10, 'Cointreau', 40, 'ml'),
       (11, 'Lemon juice', 0, 'ml'),
       (12, 'Cola', 0, 'ml');

SELECT setval('ingredients_ingredient_id_seq', (SELECT MAX(ingredient_id) from "ingredients"));



CREATE TABLE recipes
(
    recipe_id              bigserial PRIMARY KEY,
    cocktail_id            bigint NOT NULL REFERENCES cocktails (cocktail_id) ON DELETE CASCADE,
    ingredient_id          bigint NOT NULL REFERENCES ingredients (ingredient_id),
    quantity_of_ingredient smallint,
    UNIQUE (cocktail_id, ingredient_id)
);

INSERT INTO recipes
VALUES (1, 1, 1, 3),
       (2, 1, 2, 50),
       (3, 1, 3, 1),
       (4, 1, 4, 7),
       (5, 2, 6, 15),
       (6, 2, 7, 15),
       (7, 2, 8, 15),
       (8, 2, 9, 15),
       (9, 2, 10, 15),
       (10, 2, 11, 25),
       (11, 2, 12, 150);

SELECT setval('recipes_recipe_id_seq', (SELECT MAX(recipe_id) from "recipes"));


CREATE TABLE votes
(
    vote_id     bigserial PRIMARY KEY,
    user_id     bigint   NOT NULL REFERENCES users (user_id),
    cocktail_id bigint   NOT NULL REFERENCES cocktails (cocktail_id) ON DELETE CASCADE,
    vote_value  smallint NOT NULL,
    UNIQUE (user_id, cocktail_id)
);

INSERT INTO votes
VALUES (1, 1, 2, 5),
       (2, 2, 1, 5);

SELECT setval('votes_vote_id_seq', (SELECT MAX(vote_id) from "votes"));
