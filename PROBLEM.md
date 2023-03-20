# Exercise #
############

- Create a class that represents a car with a name, number of seats, current speed, production number
and engine which is characterized by its horsepower and miles per gallon.

- The car should have a method of accelerating with an input of -1.0 to 1.0 that takes the engines horse-power into
account (units, wind resistance etc. are irrelevant) and a method that formats its characteristics
name, car-type, production number and horse power as a string.

- Additionally, there should be a convertible specialization of car that has means to move its roof up
and down. There should be a print if the roof actually changes position.

-  Start out with creating a Prius with 5 seats and an engine with 121hp / 53MPG as well as a Porsche
Boxster convertible with two seats, 265hp and 32MPG.
Lower the roof of the Boxster and start racing the Prius with 20% acceleration each for the first one
to hit a speed of 200 (call acceleration method multiple times). Print the speeds of both cars after each
acceleration step


- Create a parking lot with methods to park and remove a car. The spots should be enumerated
and the lot should maintain a directory which car is parked on which spot.
If someone tries to park a car when the lot is at full capacity an error should be raised.

- Create a lot of size three and two more cars. Try to park all of the cars in the lot. Remove one when
the lot is already at full capacity when trying to park a new car.

- Finally, print the directory of the lot.