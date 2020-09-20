package testtask;

import lombok.Getter;

@Getter
public enum MaturationStage {
    BABY(100),
    TEEN(150),
    ADULT(200);
    private final int imageWidth ;

    MaturationStage(int imageWidth) {
        this.imageWidth = imageWidth;
    }
}
