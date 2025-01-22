IMDb Spring-based Application Project

This application allows users to search for data in an imported IMDb dataset,
presenting back information including full name of title, year of release,
and average viewer rating. It is built using the Spring framework, utilising
the H2 in-memory database, and Thymeleaf.

Import the datasets title.basics.tsv.gz and title.ratings.tsv.gz from the link:
https://datasets.imdbws.com/
and save these to the project folder.

Run the project with the command:

mvn spring-boot:run 

Or run through the ImdbApplication class.
 
Open the URL http://localhost:8080/home which takes the user to the home page,
allowing for film/ series titles to be searched.

On clicking the 'Find' button, the user will be directed to  the (example) URL http://localhost:8080/byPrimaryTitle?title=film&year=1950-1979

