DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS cocktails;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS votes;

CREATE TYPE user_role AS ENUM ('ROLE_ADMIN', 'ROLE_BARTENDER', 'ROLE_USER');

CREATE TABLE images
(
    image_id     bigserial PRIMARY KEY,
    name         varchar(45) NOT NULL,
    filename     varchar(45) NOT NULL,
    content_type varchar(45) NOT NULL,
    size         bigint      NOT NULL,
    bytes        bytea       NOT NULL

);

CREATE TABLE users
(
    user_id  bigserial PRIMARY KEY,
    username varchar(30) UNIQUE  NOT NULL,
    password varchar(100)        NOT NULL,
    name     varchar(15)                  DEFAULT NULL,
    surname  varchar(30)                  DEFAULT NULL,
    phone    varchar(15) UNIQUE           DEFAULT NULL,
    email    varchar(256) UNIQUE NOT NULL,
    user_pic bigint REFERENCES images (image_id) ON DELETE CASCADE,
    role     user_role           NOT NULL DEFAULT 'ROLE_USER',
    enabled  boolean             NOT NULL DEFAULT 'false',
    reg_date timestamp           NOT NULL
);

CREATE TABLE cocktails
(
    cocktail_id               bigserial PRIMARY KEY,
    cocktail_name             varchar(50) UNIQUE NOT NULL,
    cocktail_author           bigint             NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    cocktail_rating           real               NOT NULL DEFAULT '0',
    publication_date          timestamp          NOT NULL,
    image                     bigint REFERENCES images (image_id) ON DELETE CASCADE,
    cocktail_recipe           text               NOT NULL,
    approx_alcohol_percentage real               NOT NULL DEFAULT '0'
);

CREATE TABLE ingredients
(
    ingredient_id                 bigserial PRIMARY KEY,
    ingredient_name               varchar(50) NOT NULL UNIQUE,
    ingredient_alcohol_percentage smallint NOT NULL,
    unit_of_measurement           varchar(15)
);

CREATE TABLE recipes
(
    recipe_id              bigserial PRIMARY KEY,
    cocktail_id            bigint NOT NULL REFERENCES cocktails (cocktail_id) ON DELETE CASCADE,
    ingredient_id          bigint NOT NULL REFERENCES ingredients (ingredient_id) ON DELETE CASCADE,
    quantity_of_ingredient smallint,
    UNIQUE (cocktail_id, ingredient_id)
);

CREATE TABLE votes
(
    vote_id     bigserial PRIMARY KEY,
    user_id     bigint   NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    cocktail_id bigint   NOT NULL REFERENCES cocktails (cocktail_id) ON DELETE CASCADE,
    vote_value  smallint NOT NULL,
    UNIQUE (user_id, cocktail_id)
);