package org.gamestudio.game.consoleui;

import org.gamestudio.entity.Comment;
import org.gamestudio.entity.Rating;
import org.gamestudio.entity.Score;
import org.gamestudio.game.core.GameField;
import org.gamestudio.game.core.GameState;
import org.gamestudio.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleUI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private final Scanner scanner = new Scanner(System.in);
   @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentService commentService;


    @Autowired
    private RatingService ratingService;
    private int currentLevelNumber;

    private GameField gameField;
    public ConsoleUI( ) {
        printGameName();
    }

    public void playGame() {
        initialCommunication();
        startGame();
    }
    public void startGame() {

        printTopScores();
        currentLevelNumber = getLevelNumberFromPlayer();
        gameField = new GameField(currentLevelNumber);
        while ( gameField.getState() == GameState.PLAYING) {
            gameField.displayLevel();
            while (!gameField.MoveCube(getMoveDirectionFromPlayer())) {
                System.err.println("Cube can`t move this way.");
            }
        }

        if ( gameField.getState() == GameState.WIN ) {
            gameField.displayLevel();
            System.out.println(ANSI_GREEN + "Congratulation, you have won!" + ANSI_RESET);
            saveGameScore();

        } else if ( gameField.getState() == GameState.LOSE) {
            System.out.println(ANSI_RED + "Ending round..." + ANSI_RESET);
        }

        System.out.println("Would you like to play again? (y/n)");
        String userAnswer = scanner.next().toUpperCase();
        if ( userAnswer.equals("Y")) {
            System.out.println(ANSI_GREEN + "Starting new game...\n" + ANSI_RESET);
            startGame();
        }
        else {
            System.out.println(ANSI_RED + "Ending the game..." + ANSI_RESET);
        }


    }

    private void initialCommunication() {
        System.out.println("Write: (-c/ -controls) for controls information");
        System.out.println("       (-r/ -rules) for rules information");
        System.out.println("       (-sc) to show comments on game");
        System.out.println("       (-com) to comment on game");
        System.out.println("       (-rate) to rate the game");
        System.out.println("       (-p/ -play) to start the game");
        System.out.println("       (-exit) to exit the game");
        String userCommand = scanner.nextLine().toUpperCase();

        if ( !(userCommand.equals("-P") || userCommand.equals("-PLAY")) ) {
            if (userCommand.equals("-C") || userCommand.equals("-CONTROLS")) {
                printControls();
            } else if (userCommand.equals("-SC")) {
                showComments();
            } else if (userCommand.equals("-COM")) {
                addNewComment();
            } else if (userCommand.equals("-RATE")) {
                addNewRating();
            } else if (userCommand.equals("-R") || userCommand.equals("-RULES")) {
                printRules();
            } else if (userCommand.equals("-EXIT")) {
                System.exit(0);
            } else {
                System.err.println("Invalid command!");
            }
            initialCommunication();
        }
    }

    private int getLevelNumberFromPlayer() {
        System.out.println("Choose level (1 - 8)");
        String levelNumString = scanner.next();
        int levelNum =  myAtoi(levelNumString);
        while (levelNum > 9 || levelNum < 1) {
            System.out.println(ANSI_RED + "No such level!. Try again..." + ANSI_RESET);
            levelNumString = scanner.next();
            levelNum =  myAtoi(levelNumString);
        }
        return levelNum;
    }

    private int myAtoi(String str)
    {

        if (Objects.equals(str, "") || str.matches("[a-zA-Z]+") ) {
            return 0;
        }
        int res = 0;

        for (int i = 0; i < str.length(); ++i)
            res = res * 10 + str.charAt(i) - '0';

        return res;
    }

    private void printControls() {
        System.out.println(ANSI_GREEN + "Cube moves through the game field in 4 directions ( left, right, up and down)");
        System.out.println("Type: \n (l) to move left\n (r) to move right\n (u) to move up\n (d) to move down");
        System.out.println("\nYou can exit current level at any time by typing (x)\n" + ANSI_RESET);
    }

    private void printRules() {
        System.out.println(ANSI_GREEN + "The point of this game is to navigate the cube through the field to its final position, which is marked by Yellow colour");
        System.out.println("Cube can move freely on the white blocks, but not on the black ones");
        System.out.println("There are also 2 subtypes of road block, Markers(▣) and Marked tiles");
        System.out.println("When cube steps on marker, its bottom side will be marked by Markers colour");
        System.out.println("Marked tile can be only stepped on, if the bottom side of cube is marked with the same colour as marker\n" + ANSI_RESET);
    }

    private void printGameName() {
        String[] gameName = {
                    "   _____      _          __  __               ",
                    "  / ____|    | |        |  \\/  |             ",
                    " | |    _   _| |__   ___| \\  / | __ _ _______ ",
                    " | |   | | | | '_ \\ / _ \\ |\\/| |/ _` |_  / _ \\",
                    " | |___| |_| | |_) |  __/ |  | | (_| |/ /  __/",
                    "  \\_____\\__,_|_.__/ \\___|_|  |_|\\__,_/___\\___|",
                    " "
        };
        for ( int i = 0; i < 7; i++) {
            System.out.println(ANSI_YELLOW + gameName[i] + ANSI_RESET);
        }

    }

    private void printTopScores() {
        var scores = scoreService.getTopScores("cubeMaze");

        System.out.println("-----------------------------------------");
        for( int i = 0; i < scores.size(); i++) {
            var score = scores.get(i);
            System.out.printf("%d. %s %d level: %d\n",i+1,score.getPlayer(), score.getPoints(),score.getLevel());
        }
        System.out.println("-----------------------------------------");
    }

    private void saveGameScore() {
        System.out.println("Points: " + gameField.getPoints());
        Score score = new Score(getPlayerName(),"cubeMaze",this.currentLevelNumber,gameField.getPoints(),new Date());
        scoreService.addScore(score);
    }

    private String getPlayerName() {
        System.out.println("Please enter your nickname: ");
        String nickname = scanner.nextLine().trim();

        while (nickname.isEmpty() || nickname.contains(" ")) {
            System.err.println("Invalid nickname, try again...");
            nickname = scanner.nextLine().trim();
        }
        return nickname;
    }

    private void showComments() {
        var comments = commentService.getComments("cubeMaze");
        System.out.println("-----------------------------------------");

        for (Comment comment : comments) {
            System.out.printf("%s: %s <%s>\n", comment.getPlayer(), comment.getComment(), comment.getCommentedAt().toString());
        }

        System.out.println("-----------------------------------------");

    }

    private void showAverageRating() {
        int aRating = ratingService.getAverageRating("cubeMaze");
        if ( aRating == 0 ) {
            System.out.println("No ratings yet");
        } else {
            System.out.println("Average rating: " + aRating);
        }
    }

    private void addNewRating() {

        showAverageRating();
        System.out.println("Enter your rating <1,5> ( 5 being best ): ");

        String pRating_String = scanner.nextLine().trim();
        int pRating = myAtoi(pRating_String);

        while ( pRating < 1 || pRating > 5) {
            System.err.println("Invalid rating, try again...");
            pRating_String = scanner.nextLine().trim();
            pRating = myAtoi(pRating_String);
        }

        ratingService.setRating(new Rating(getPlayerName(),"cubeMaze",pRating,new Date()));
    }

    private void addNewComment() {
        System.out.println("Enter your comment: ");

        String pComment = scanner.nextLine().trim();

        while (pComment.isEmpty() || pComment.length() > 299) {
            System.err.println("Invalid comment, try again...");
            pComment = scanner.nextLine().trim();
        }

        commentService.addComment(new Comment(getPlayerName(),"cubeMaze",pComment,new Date()));
    }

    private String getMoveDirectionFromPlayer() {
        String uInput = scanner.next().toUpperCase();

        while (!( uInput.equals("X") || uInput.equals("L") || uInput.equals("R") || uInput.equals("U") || uInput.equals("D"))) {
            System.err.println("Invalid command!");
            uInput = scanner.next().toUpperCase();
        }

        return uInput;
    }

}
