# BAR
This web app is a cocktails' catalogue where bartenders can share their
recipes. 
<br>
There are 3 types of users:
<br>
* USER - can watch cocktails' recipes and give marks to its authors<br>
* BARTENDER - can do the same as USER + add new cocktail recipes
and add new ingredients
* ADMIN - can do the same as BARTENDER + administrate(delete, change
roles, etc) all users, cocktails and ingredients.
<br>
<br>
To check the capabilities of each type of user here is some login/passwords:
ADMIN: Admin/123456
BARTENDER: LuckyBartender/123456
USER: Jspm/123456
<br>
<br>


How to run:
1) Most convenient way is to run it in Docker, just run 'docker compose up' and wait until it will deploy.
Then you can reach it by the default url: 'http://localhost:7777/'
2) As this app is using Redis as cache you should run Redis first (Default host/port - localhost:6379), configure
database by installing PostgreSQL and then run backend by running 'gradle build; java -jar build/libs/bar_spring-0.0.1-SNAPSHOT.jar' command and frontend by running 'ng serve' command. Then you can reach it by the default url: 'http://localhost:4200/'



Used technologies:
<br>
Backend: Spring Boot, Spring Data JPA, Spring Security, Redis(cache)
<br>
Frontend: Angular
<br>
Database: PostgreSQL