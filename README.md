# CubeMaze

## Introduction

CubeMaze is an engaging and challenging game where the objective is to navigate a cube through a maze to its final position. The game is designed to test the player's spatial reasoning and problem-solving skills.

[Showcase Video](https://youtu.be/71-jNMRiNbw)

## Technologies Used

This project is a full-stack application, developed using the following technologies:

- Backend: Java (Spring Boot, JPA)
- Frontend: React.js(JavaScript, HTML, CSS)
- Database: PostgreSQL (for storing comments, ratings, and scores)
- Login Verification: Firebase

## Gameplay

The cube can move freely on the road tiles, but the game introduces complexity with two subtypes of road tiles: Markers and Marked tiles.

- **Markers**: When the cube steps on a marker, its bottom side will be marked by the marker's color.
- **Marked Tiles**: These tiles can only be stepped on if the bottom side of the cube is marked with the same color as the tile.

The goal of this game is to navigate the cube through the maze to its final position
(yellow tile) while following the rules of the game. 
Multiple levels are available, each with increasing complexity and difficulty.


## Data saving

After completing a level, the player's score is saved in the database(when logged in). The player can also view
the scores of other players, comments and average rating of the game.
Only logged-in users can rate the game and leave comments.

