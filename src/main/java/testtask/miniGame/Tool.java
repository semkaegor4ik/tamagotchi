package testtask.miniGame;

import javafx.scene.image.Image;
import lombok.Getter;

@Getter
public enum Tool {
    ROCK("rockPaperScissors/rock.png"),
    PAPER("rockPaperScissors/paper.png"),
    SCISSORS("rockPaperScissors/scissors.png");

    private final String imgPath;
    private final Image image;
    Tool(String imgPath) {
        this.imgPath = imgPath;
        image = new Image(this.imgPath);
    }
}
