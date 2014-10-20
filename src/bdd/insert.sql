
/*INSERT INTO RowExpenses (id_row, id_buyer, amount, description, id_group)
VALUES ('1','1','15','patate', '2'); */

INSERT INTO Ingredients (name_ingredient, quantity, unity, id_recipe) VALUES ('burger bread', '10', 'unit', '1');
INSERT INTO Ingredients (name_ingredient, quantity, unity, id_recipe) VALUES ('tomatoes', '5', 'unit', '1');

INSERT INTO Ingredients (name_ingredient, quantity, unity, id_recipe) VALUES ('cheese grater', '100', 'gram', '1');
 
INSERT INTO Ingredients (name_ingredient, quantity, unity, id_recipe) VALUES ('minced beef', '10', 'unit', '1');
INSERT INTO Ingredients (name_ingredient, quantity, unity, id_recipe) VALUES ('burger sauce', '1', 'unit', '1');
INSERT INTO Ingredients (name_ingredient, quantity, unity, id_recipe) VALUES ('salad', '1', 'unit', '1');

SET @descriptionBurgers = ' Faire cuire les steaks hachés sans matière grasse dans une poele avec un couvercle.
Pendant ce temps, coupez les tomates en tranches fines et lavez la salade si besoin.
Lorsque les steaks sont prêts, préparez les burgers:
    Déposez les tranches de pains burgers dans une assiète.
    Mettre du fromage sur les deux côtés du pain.
    Les faire chauffer au micro onde 30 secondes.
    Recouvrez les de sauce burger.
    Mettez le steak, 3 tranches de tomates et une feuille de salade.
';

INSERT INTO Recipes (name_recipe, nb_person, description, type) VALUES ('Homemade burgers', '5', 'unit', 'course');





