# Automate YouTube - _Project Author (Darpan Sarode)_

## Project Name and Description:
Automate the youtube and check the Films, Music and News tab. Use DataProvider to Search for various items from a data-set.

## Description
### TestCase01
Go to YouTube.com and Assert you are on the correct URL. Click on “About” at the bottom of the sidebar, and print the message on the screen.

### TestCase02
Go to the “Films” tab and in the “Top Selling” section, scroll to the extreme right. Apply a Soft Assert on whether the movie is marked “A” for Mature or not. Apply a Soft assert on whether the movie is either “Comedy” or “Animation”.


### TestCase03
Go to the “Music” tab and in the 1st section, scroll to the extreme right. Print the name of the playlist. Soft Assert on whether the number of tracks listed is less than or equal to 50.

### TestCase04
Go to “News” tab and print the title and body of the 1st 3 “Latest News Posts” along with the sum of number of likes on all 3 of them. No likes given means 0.

## Installation Instructions:
java --version 11 and more

gradlew version -- version 8.0


## Usage and Examples:
Provide sample code or usage scenarios to showcase how the project works.
> Example:
```
# to run the project
./gradlew run
```

## Important Links:
Details about useful external links
 
