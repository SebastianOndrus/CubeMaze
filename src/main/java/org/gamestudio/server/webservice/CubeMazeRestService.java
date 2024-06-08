package org.gamestudio.server.webservice;

import org.gamestudio.game.core.Cube;
import org.gamestudio.game.core.GameField;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cubeMaze/field")
public class CubeMazeRestService {

    private GameField gameField = new GameField(1);

    @GetMapping()
    public GameField getGameField() {
        return gameField;
    }

    @GetMapping("/newGame")
    public GameField newGame(@RequestParam int level) {
        gameField = new GameField(level);
        return gameField;
    }

    @GetMapping("/move")
    public GameField moveCube(@RequestParam int row, @RequestParam int column) {
        calculateMove(row, column);
        return gameField;
    }

    private void calculateMove(int row, int column) {
        Cube cube = gameField.getCube();
        if (cube.getCubeX() - column == 1 && cube.getCubeY() - row == 0) { // pohyb dolava
            gameField.MoveCube("L");
        } else if (cube.getCubeX() - column == -1 && cube.getCubeY() - row == 0) { // pohyb doprava
            gameField.MoveCube("R");
        } else if (cube.getCubeY() - row == -1 && cube.getCubeX() - column == 0) { // pohyb dole
            gameField.MoveCube("D");
        } else if (cube.getCubeY() - row == 1 && cube.getCubeX() - column == 0) { // pohyb hore
            gameField.MoveCube("U");
        }
    }
}
