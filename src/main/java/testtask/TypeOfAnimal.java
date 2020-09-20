package testtask;

import javafx.scene.image.Image;
import lombok.Getter;

@Getter
public enum TypeOfAnimal {
    CAT(Food.FISH, "animals/cat.png"),
    DOG(Food.BONE, "animals/dog.png"),
    TURTLE(Food.LEAVES, "animals/turtle.png"),
    HAMSTER(Food.APPLE, "animals/hamster.png"),
    RABBIT(Food.CARROT, "animals/rabbit.png"),
    PIG(Food.POTATO, "animals/pig.png");
    private final Food food;
    private final String imgPath;
    private final Image image;

    TypeOfAnimal(Food food, String imgPath) {
        this.food = food;
        this.imgPath = imgPath;
        image = new Image(this.imgPath);
    }
}
