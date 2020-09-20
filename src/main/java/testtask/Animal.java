package testtask;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Animal implements Serializable {

    private MaturationStage maturationStage;
    private TypeOfAnimal typeOfAnimal;
    private final String name;
    private Condition condition;

    private int satiety;
    private int mood;

    private Date lastFeed;
    private Date lastPlay;
    
    private int x = Tamagotchi.weight / 2;


    public Animal(TypeOfAnimal typeOfAnimal, String name){
        this.typeOfAnimal = typeOfAnimal;
        this.name = name;
        lastFeed = new Date();
        lastFeed.setTime(lastFeed.getTime() - ConditionThread.onePercentInMillisecondsForSatiety * 60);
        lastPlay =  new Date();
        lastPlay.setTime(lastPlay.getTime() - ConditionThread.onePercentInMillisecondsForMood * 100);
        maturationStage = MaturationStage.BABY;
        condition = Condition.NORMAL;
    }

    public void moveX(int x, UIController controller){
        boolean right = x>0?true:false;
        for(int i = 0; i < Math.abs(x); i++) {
            if (right && this.x < Tamagotchi.weight - controller.getImageView().getFitWidth()){
                this.x++;
                controller.getImageLabel().setTranslateX(this.x);
            }
            else if(this.x > 0){
                this.x--;
                controller.getImageLabel().setTranslateX(this.x);
            }
        }
    }

    public void rise(UIController controller){
        if(MaturationStage.BABY.equals(maturationStage)){
            maturationStage = MaturationStage.TEEN;
            controller.rise(maturationStage);
        }
        else if(MaturationStage.TEEN.equals(maturationStage)){
            maturationStage = MaturationStage.ADULT;
            controller.rise(maturationStage);
        }
    }
}
