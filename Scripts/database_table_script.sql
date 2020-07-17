DROP TABLE IF EXISTS ranking;
DROP TABLE IF EXISTS favourites;
DROP TABLE IF EXISTS drinks_ingredients;
DROP TABLE IF EXISTS drinks;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
	user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    nickname VARCHAR(100) NOT NULL UNIQUE,
    sex VARCHAR(20) NOT NULL,
    age INT NOT NULL,
    mail VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE drinks(
	drink_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    drink_name VARCHAR(200) NOT NULL,
    image VARCHAR(500),
    instruction VARCHAR(4000),
    parent_id INT,
    author INT NOT NULL,
	addition_time datetime,
    CONSTRAINT parent_id_fk FOREIGN KEY (parent_id)
    REFERENCES drinks(drink_id),
    CONSTRAINT author_fk FOREIGN KEY (author)
    REFERENCES users(user_id)
);

CREATE TABLE ingredients(
	ingredient_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ingredient_name VARCHAR(200) NOT NULL
);

CREATE TABLE drinks_ingredients(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    drink_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    CONSTRAINT di_drink_id_fk FOREIGN KEY (drink_id)
    REFERENCES drinks(drink_id),
    CONSTRAINT di_ingredient_id_fk FOREIGN KEY (ingredient_id)
    REFERENCES ingredients(ingredient_id)
);

CREATE TABLE favourites(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    drink_id INT NOT NULL,
    CONSTRAINT f_user_id_fk FOREIGN KEY (user_id)
    REFERENCES users(user_id),
    CONSTRAINT f_drink_id_fk FOREIGN KEY (drink_id)
    REFERENCES drinks(drink_id)
);

CREATE TABLE ranking(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    drink_id INT NOT NULL,
    rank_score INT NOT NULL,
    CONSTRAINT r_user_id_fk FOREIGN KEY (user_id)
    REFERENCES users(user_id),
    CONSTRAINT r_drink_id_fk FOREIGN KEY (drink_id)
    REFERENCES drinks(drink_id)
);


