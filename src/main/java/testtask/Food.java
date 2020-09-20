package testtask;

import javafx.scene.image.Image;
import lombok.Getter;

@Getter
public enum Food {
    FISH("food/fish.png"),
    BONE("food/bone.png"),
    POTATO("food/potato.png"),
    CARROT("food/carrot.png"),
    APPLE("food/apple.png"),
    LEAVES("food/leaves.png");

    private final String imgPath;
    private final Image image;
    Food(String imgPath) {
        this.imgPath = imgPath;
        image = new Image(this.imgPath);
    }
}
