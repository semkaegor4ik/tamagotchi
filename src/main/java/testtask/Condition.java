package testtask;

import javafx.scene.image.Image;
import lombok.Getter;

@Getter
public enum Condition {
    HAPPY("conditions/happy.png"),
    NORMAL("conditions/normal.png"),
    BAD("conditions/bad.png"),
    DIE("conditions/rip.png");
    private final String imgPath;
    private final Image image;
    Condition(String imgPath) {
        this.imgPath = imgPath;
        image = new Image(this.imgPath);
    }
}
