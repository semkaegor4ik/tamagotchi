package testtask.miniGame;

import lombok.Getter;
import lombok.Setter;
import testtask.UIController;

import java.io.Serializable;
import java.util.Random;

@Setter
@Getter
public class RockPaperScissorsGame implements MiniGame, Serializable {
    private static RockPaperScissorsGame rockPaperScissorsGame;
    private final Random random;
    private UIController controller;

    private RockPaperScissorsGame() {
        random = new Random();
    }

    public static RockPaperScissorsGame getInstance() {
        if (rockPaperScissorsGame == null) {
            rockPaperScissorsGame = new RockPaperScissorsGame();
            return rockPaperScissorsGame;
        } else {
            return rockPaperScissorsGame;
        }
    }

    @Override
    public void playMiniGame() {
        controller.playRockPaperScissors(choseTool());
    }

    public void checkWinner(Tool playerTool, Tool animalTool){
        if ((Tool.PAPER.equals(playerTool) &&
                Tool.ROCK.equals(animalTool)) ||
                (Tool.ROCK.equals(playerTool) &&
                        Tool.SCISSORS.equals(animalTool)) ||
                (Tool.SCISSORS.equals(playerTool) &&
                        Tool.PAPER.equals(animalTool))) {
            controller.getTamagotchi().setNewPlayDate();
        }
    }
    private Tool choseTool() {
        int intTool = random.nextInt(Tool.values().length);
        switch (intTool) {
            case 0: {
                return Tool.PAPER;
            }
            case 1: {
                return Tool.ROCK;
            }
            default: {
                return Tool.SCISSORS;
            }
        }
    }
}
