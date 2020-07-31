insert into drinks(drink_name, image, instruction, parent_id, author)
values("Bourbon", "/resources/photos/bourbon.jpg", "A mash bill that contains wheat instead of rye produces what is known as a wheated bourbon. The grain is ground and mixed with water. Usually mash from a previous distillation is added to ensure consistency across batches, creating a sour mash. Finally, yeast is added, and the mash is fermented.", 
null, (select user_id from users where nickname = "bkapa18"));

insert into drinks(drink_name, image, instruction, parent_id, author)
values("Old Fashioned", "/resources/photos/old-fashioned.jpg", "There may be no better test of a bartender's mettle than ordering an Old Fashioned. The recipe is simple:

- 2 oz bourbon or rye whiskey
- 2 dashes Angostura bitters
- 1 sugar cube or 1 tsp sugar
- Orange twist garnish

Put sugar in glass. Cover it with dashes of bitters. Add whiskey and stir until sugar dissolves. Add ice, stir again, and serve. If the barman starts shaking the ingredients or muddling fruit, have your next round at another bar.", 
(select d.drink_id from drinks d where d.drink_name = "Bourbon"), 
(select user_id from users where nickname = "bkapa18"));

insert into ingredients(ingredient_name) values
		("Bourbon"),
		("Angostura bitters"),
        ("Sugar"),
        ("Orange twist garnish");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Old Fashioned"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Bourbon")),
    ( (select drink_id from drinks d where d.drink_name = "Old Fashioned"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Angostura bitters")), 
	( (select drink_id from drinks d where d.drink_name = "Old Fashioned"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Sugar")),
	( (select drink_id from drinks d where d.drink_name = "Old Fashioned"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Orange twist garnish"));

insert into drinks(drink_name, image, instruction, parent_id, author)
values("Tequila", "/resources/photos/tequila.jpg", 
"Tequila is a distilled spirit made from the Agave tequilana Weber Blue, blue agave or Agave Azul.", 
null, (select user_id from users where nickname = "istur17"));

insert into ingredients(ingredient_name) values
		("Agave tequilana");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Tequila"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Agave tequilana"));


insert into drinks(drink_name, image, instruction, parent_id, author)
values("Margarita", "/resources/photos/margarita.jpg", "Cloyingly sweet margarita mixes have given this drink a bad name. A well-made version is a fresh mix of lime juice and tequila, with a hint of sweetener:

- 2 oz silver tequila
- 1 oz Cointreau
- 1 oz lime juice
- Salt for the rim

Since this recipe includes fresh juice, it should be shaken. Serve over ice in a glass with a salted rim.", 
(select d.drink_id from drinks d where d.drink_name = "Tequila"),
(select user_id from users where nickname = "istur17"));

insert into ingredients(ingredient_name) values
		("Silver tequila"),
		("Cointreau"),
        ("Lime juice"),
        ("Salt");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Margarita"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Silver tequila")),
    ( (select drink_id from drinks d where d.drink_name = "Margarita"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Cointreau")), 
	( (select drink_id from drinks d where d.drink_name = "Margarita"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Lime juice")),
	( (select drink_id from drinks d where d.drink_name = "Margarita"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Salt"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Vodka", "/resources/photos/vodka.jpg", 
"If using rye or wheat, first the grain must be mixed into water and then heated to create a wort. The heat breaks down the starches into fermentable sugars, which escape the grain and move into the water. Next, the wort is drained and the ensuing liquid becomes the ferment for vodka, also known as the wash.", 
null, (select user_id from users where nickname = "tkats18"));

insert into drinks(drink_name, image, instruction, parent_id, author)
values("Cosmopolitan", "/resources/photos/cosmopolitan.jpg", "The cosmo became almost ubiquitous in the '90s thanks to the TV show Sex and the City, but this spin on the martini remains just as tasty today as when Carrie Bradshaw made it famous.

- 1.5 oz citrus vodka
- 1 oz Cointreau
- .5 oz lime juice
- .25 oz cranberry juice

Build all ingredients in a shaker tine with ice and shake. Strain into a martini glass and garnish with lime wheel or zest.", 
(select d.drink_id from drinks d where d.drink_name = "Vodka"),
(select user_id from users where nickname = "tkats18"));

insert into ingredients(ingredient_name) values
		("Citrus vodka"),
        ("Cranberry juice");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Cosmopolitan"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Citrus vodka")),
    ( (select drink_id from drinks d where d.drink_name = "Cosmopolitan"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Cointreau")), 
	( (select drink_id from drinks d where d.drink_name = "Cosmopolitan"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Lime juice")),
	( (select drink_id from drinks d where d.drink_name = "Cosmopolitan"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Cranberry juice"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Gin", "/resources/photos/gin.jpg", "Distilled gin is produced exclusively by redistilling ethanol of agricultural origin with an initial strength of 96% ABV (the azeotrope of water and ethanol) in stills traditionally used for gin, in the presence of juniper berries and of other natural botanicals, provided that the juniper taste is predominant.", 
null, (select user_id from users where nickname = "msamk18"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Negroni", "/resources/photos/negroni.jpg", "A favorite of bartenders all over the world, the Negroni is a simple three-ingredient cocktail:

- 1 oz gin
- 1 oz Campari
- 1 oz sweet vermouth

Stir ingredients with ice.", 
(select d.drink_id from drinks d where d.drink_name = "Gin"), 
(select user_id from users where nickname = "msamk18"));

insert into ingredients(ingredient_name) values
		("Gin"),
        ("Campari"),
        ("Sweet vermouth");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Negroni"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Gin")),
    ( (select drink_id from drinks d where d.drink_name = "Negroni"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Campari")), 
	( (select drink_id from drinks d where d.drink_name = "Negroni"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Sweet vermouth"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Moscow Mule", "/resources/photos/moscow-mule.jpg", "Popular for good reason, the Moscow Mule is one of the most refreshing things to sip on a hot summer day. Its suggested vessel, a copper mug, also just looks sharp.

- 2 oz vodka
- 4 to 6 oz ginger beer
- .5 oz lime juice

Squeeze lime juice into a Moscow Mule mug. Add two or three ice cubes, pour in the vodka, and fill with cold ginger beer. Stir and serve.", 
(select d.drink_id from drinks d where d.drink_name = "Vodka"),
(select user_id from users where nickname = "llort18"));

insert into ingredients(ingredient_name) values
		("Vodka"),
        ("Ginger beer");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Moscow Mule"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Vodka")),
    ( (select drink_id from drinks d where d.drink_name = "Moscow Mule"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Ginger beer")), 
	( (select drink_id from drinks d where d.drink_name = "Moscow Mule"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Lime juice"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Martini", "/resources/photos/martini.jpg", "James Bond was wrong—whether you drink it with gin or vodka, stirred is the way to go when ordering a martini.

- 3 oz gin or vodka
- .5 oz dry vermouth
- Lemon peel or olive

Stir ingredients in a mixing glass with ice. Strain into chilled martini glass. Squeeze oil from lemon peel into the glass or garnish with olive.", 
(select d.drink_id from drinks d where d.drink_name = "Martini"),
(select user_id from users where nickname = "bkapa18"));

insert into ingredients(ingredient_name) values
		("Dry vermouth"),
        ("Lemon peel");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Martini"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Gin")),
    ( (select drink_id from drinks d where d.drink_name = "Martini"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Dry vermouth")), 
	( (select drink_id from drinks d where d.drink_name = "Martini"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Lemon peel"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Rum", "/resources/photos/rum.jpg", "To make rum, dissolve sugar and molasses in hot water, let it cool, and add hydrated yeast. Let that mixture ferment for a couple days before you chill it to knock the yeast to the bottom of the bucket. Then, distill the rum by running a siphon from the mash to a collection tank.", 
null, (select user_id from users where nickname = "istur17"));

insert into ingredients(ingredient_name) values
        ("Molasses");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Rum"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Sugar")),
    ( (select drink_id from drinks d where d.drink_name = "Rum"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Molasses"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Mojito", "/resources/photos/mojito.jpg", "Originating in Cuba, this refreshing rum-based sip is filled with mint and lime—a perfect combination for sipping by the pool or beach. If you're craving a little literary cred, the mojito was also said to be a favorite of author Ernest Hemingway.

- 3 mint leaves
- 2 oz white rum
- .75 oz lime juice
- .5 oz simple syrup

Muddle mint into a shaker tin, then add ice and all other ingredients. Shake to chill and strain into a highball glass with ice. Top with club soda if desired and garnish with mint.", 
(select d.drink_id from drinks d where d.drink_name = "Rum"),
(select user_id from users where nickname = "istur17"));

insert into ingredients(ingredient_name) values
		("Mint leaves"),
        ("White rum"),
        ("Simple syrup");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Mojito"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Mint leaves")),
    ( (select drink_id from drinks d where d.drink_name = "Mojito"), 
	  (select ingredient_id from ingredients i where ingredient_name = "White rum")), 
	( (select drink_id from drinks d where d.drink_name = "Mojito"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Lime juice")),
      ( (select drink_id from drinks d where d.drink_name = "Mojito"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Simple syrup"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Whiskey Sour", "/resources/photos/whiskey-sour.jpg", "Perhaps the most refreshing whiskey cocktail, this is an old reliable favorite.

- 2 oz whiskey
- 1 oz lemon juice
- 1 tsp sugar
- 1 egg white (optional)

Combine ingredients in a cocktail shaker and shake (bartenders use this 'dry shake' to incorporate the egg white). Add ice and shake again. Strain over ice in a rocks glass.", 
(select d.drink_id from drinks d where d.drink_name = "Whiskey"),
(select user_id from users where nickname = "tkats18"));

insert into ingredients(ingredient_name) values
		("Whiskey"),
        ("Lemon juice"),
        ("Egg white");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Whiskey Sour"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Whiskey")),
    ( (select drink_id from drinks d where d.drink_name = "Whiskey Sour"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Lemon juice")), 
	( (select drink_id from drinks d where d.drink_name = "Whiskey Sour"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Sugar")),
      ( (select drink_id from drinks d where d.drink_name = "Whiskey Sour"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Egg white"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("French 75", "/resources/photos/french-75.jpg", "Created during World War I, the name of this drink was supposedly inspired by the fact that taking a sip of it feels like getting shelled with a French 75mm field gun, a powerful piece of artillery.

- 2 oz gin
- 2 dashes simple syrup
- .5 oz lemon juice
- Champagne

Shake gin, simple syrup, and lemon juice with ice. Strain into a champagne glass. Top with champagne.", 
(select d.drink_id from drinks d where d.drink_name = "Gin"),
(select user_id from users where nickname = "msamk18"));

insert into ingredients(ingredient_name) values
		("Champagne");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "French 75"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Gin")),
    ( (select drink_id from drinks d where d.drink_name = "French 75"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Simple syrup")), 
	( (select drink_id from drinks d where d.drink_name = "French 75"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Lemon juice")),
      ( (select drink_id from drinks d where d.drink_name = "French 75"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Champagne"));
      
insert into drinks(drink_name, image, instruction, parent_id, author)
values("Manhattan", "/resources/photos/manhattan.jpg", "Created sometime in the mid-1800s, the Manhattan is one of the booziest classic drink recipes.

- 2 oz rye whiskey
- 1 oz sweet vermouth
- 2 dashes Angostura bitters

Stir ingredients in a mixing glass with ice. Strain into chilled martini glass or cocktail coupe.", 
(select d.drink_id from drinks d where d.drink_name = "Whiskey"),
(select user_id from users where nickname = "llort18"));

insert into ingredients(ingredient_name) values
		("Rye whiskey");
        
INSERT INTO drinks_ingredients(drink_id, ingredient_id) VALUES
	( (select drink_id from drinks d where d.drink_name = "Manhattan"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Rye whiskey")),
    ( (select drink_id from drinks d where d.drink_name = "Manhattan"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Sweet vermouth")), 
	( (select drink_id from drinks d where d.drink_name = "Manhattan"), 
	  (select ingredient_id from ingredients i where ingredient_name = "Angostura bitters"));
      
