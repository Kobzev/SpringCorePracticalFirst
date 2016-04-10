Application for managing a movie theater. 

Allows for admins to enter events, view purchased tickets. 
Allows for users to register, view events with air dates and times, get ticket price, buy tickets.

#Credentials
user1: admin@admin.com  admin
user2: user@user.com user

generated xsd schema:
schemagen -d src/main/resources/ -cp target/classes/ ua.kobzev.theatre.domain.User ua.kobzev.theatre.domain.Event

generated classes from xsd:
xjc -d src/main/java -p ua.kobzev.theatre.domain.generated src/main/resources/schema/All.xsd

