# Receipt2Recipe-
Receipt 2 Recipe 

Team:
Qosai Kadadha - kadadha@wisc.edu - StealthyShadow
Alex Ionkov - ionkov@wisc.edu - ionif
Hayden Schultz - hmschultz3@wisc.edu - hmschultz3
George - Khankeldian@wisc.edu - GKhankles


Project Introduction:
The current problem that we are trying to solve is two-fold: 1) people tend to forget the different foods they can create using ingredients at home, causing them to eat out more than necessary, and 2) there is not an app that can easily import the foods they buy into a central database (current competitors require you to manually enter in ingredients). Our app is going to solve both of these problems. By scanning your grocery list, we can make it quick and easy to import your ingredients, and by recommending recipes, people will eat out less. Eating out less means our customers will be more financially responsible and learn how to cook different foods. 

Our app will allow users to plan their meals by tracking their food purchases and recommending recipes. We will accomplish this by scanning the receipts using a google vision OCR API, saving those items in a database, and using the Spoonacular API(linked below) to recommend recipes. We could also add additional features for planning your next shopping list based on recipes you have nearly all of the ingredients for. 

Natural Users: The natural users for this product would be those who are just learning about how to cook for themselves (22 and younger) or those who want to better keep track of their ingredients and the meals they can cook with them. 

Competitors: 
Whisk: provides recipes from an ingredient list, helps create meal plans, allows recipes to be easily shared and saved, and shows nutritional data on all recipes. 
	
This app works great for its intended purposes, and being able to easily track nutritional information is extremely helpful. However, being linked to the original recipe source is inconvenient and wastes a lot of time and adds a lot of fluff. This results in seeing a ton of ads through the original sites.

Empty my fridge: creates a list of items that one already has in their home and gives them recipes they can make with those items. Also allows lists and recipes to be shared and updated by multiple people. 

Limited recipe filter options, ingredients have to be manually entered which is time consuming and tedious. No custom items list so if an item is not in app’s inventory, there is no way to add it. 

Mealtime: automatically creates organized grocery lists based on desired recipes. Also offers grocery deliveries based on items selected. Offers many filters based on dietary restrictions or health concerns.

Many recipes are hidden behind a payment plan, local grocery store chains are much harder to use and many cannot be used for deliveries. Nutritional information is also locked behind payment plans. 

Our Niche:
Receipt-2-Recipe  is the only app that will allow the items on the receipt to be scanned. Every other app requires manual entry of food items and that is extremely time consuming and tedious. 

Main Modules:
On Mobile: 
Scan receipts
Search through recipes
Filter through recipes based on dietary preferences or allergies
Save recipes to an account
Open API Calls:
Parse receipt data
https://cloud.google.com/vision/docs/ocr
https://betterprogramming.pub/google-vision-and-google-sheets-api-line-by-line-receipt-parsing-2e2661261cda
Get recipes from ingredients
https://spoonacular.com/food-api/docs#Search-Recipes-by-Ingredients

Mobile Innovation:
This app will make heavy use of the camera feature of phones. Most desktops do not have a camera and the ease of use with smartphone cameras make this application idea only feasible on mobile phones. The most innovative part of this app is simply just automating the item input. Manual input of items will no longer be needed and it will save users a lot of time.  
