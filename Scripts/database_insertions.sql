insert into users(first_name, last_name, nickname, sex, age, mail, password)
values("Beso", "Kapanadze", "bkapa18", "Male", 20, "bkapa18@freeuni.edu.ge", "12345678"),
("Ilia", "Sturua", "istur17", "Male", 20, "istur17@freeuni.edu.ge", "12345678"),
("Tato", "Katsitadze", "tkats18", "Male", 20, "tkats18@freeuni.edu.ge", "12345678"),
("Mariam", "Samkharadze", "msamk18", "Female", 20, "msamk18@freeuni.edu.ge", "12345678"),
("Luka", "Lortkipanidze", "llort18", "Male", 20, "llort18@freeuni.edu.ge", "12345678");

insert into drinks(drink_name, image, instruction, parent_id, author, addition_time)
values("Redbull-Vodka", "/resources/photos/redbull-vodka.jpg", "Mix up 16th and arayi.", null, 1, sysdate());

insert into ingredients(ingredient_name)
values("arayi"),
	  ("16th");
      
insert into drinks_ingredients(drink_id,ingredient_id)
values (1,1),
	(1,2);


insert into drinks(drink_name, image, instruction, parent_id, author, addition_time)
values("Beer", "/resources/photos/beer.jpg", "Kasri", null, 2, sysdate());

insert into ingredients(ingredient_name)
values("kasri");
      
insert into drinks_ingredients(drink_id,ingredient_id)
values (2,3);


insert into drinks(drink_name, image, instruction, parent_id, author, addition_time)
values("Martini", "/resources/photos/martini.jpg", "glass of martini.", null, 3, sysdate());

insert into ingredients(ingredient_name)
values("Bottle of Martini"),
("ice");
      
insert into drinks_ingredients(drink_id,ingredient_id)
values (3,4),
(3,5);
	

insert into drinks(drink_name, image, instruction, parent_id, author, addition_time)
values("Red Wine", "/resources/photos/red_wine.jpg", "Just a red wine.", null, 4, sysdate());

insert into ingredients(ingredient_name)
values("Bottle of any wine which is red.");
      
insert into drinks_ingredients(drink_id,ingredient_id)
values (4,6);
	

insert into drinks(drink_name, image, instruction, parent_id, author, addition_time)
values("Mojito", "/resources/photos/mojito.jpg", "Just a regular mojito", null, 5, sysdate());

insert into ingredients(ingredient_name)
values("Glass for Mojito"),
("Mojito itself");
      
insert into drinks_ingredients(drink_id,ingredient_id)
values (5,7),
(5,8);
	

