package testtask;

import lombok.Getter;
import lombok.Setter;
import testtask.miniGame.MiniGame;
import testtask.miniGame.RockPaperScissorsGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Tamagotchi implements Serializable {
    private static Tamagotchi tamagotchi;
    private Date dateOfDead;
    private ConditionThread conditionThread;
    private Animal animal;
    private final List<MiniGame> miniGames;
    private int countOfFeed = 0;
    private final RockPaperScissorsGame rockPaperScissorsGame ;
    private final long timeToReborn = 7200000;
    public static final int weight = 1000;
    public static final int height = 500;
    public static final int stateOfImageSize = 40;
    private final UIController controller;


    private Tamagotchi(){
        controller = UIController.getInstance();
        rockPaperScissorsGame = RockPaperScissorsGame.getInstance();
        controller.setTamagotchi(this);
        rockPaperScissorsGame.setController(controller);
        miniGames = new ArrayList<>();
        miniGames.add(rockPaperScissorsGame);
    }

    public static Tamagotchi getInstance(){
        if(tamagotchi == null){
            tamagotchi = new Tamagotchi();
            return tamagotchi;
        }
        else
            return tamagotchi;
    }

    public void drawBackground(){
        controller.drawBackground();
    }

    public void moveRight(){
        animal.moveX(4, controller);
    }

    public void moveLeft(){
        animal.moveX(-4, controller);
    }

    public void die(){
        if(Condition.DIE.equals(animal.getCondition())&&
                dateOfDead == null){
            controller.killAnimal(animal);
            try {
                conditionThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dateOfDead = new Date();
        }
        else if(dateOfDead!=null && (new Date().getTime() - dateOfDead.getTime() > timeToReborn)){
            controller.deleteRIP();
            animal = null;
            dateOfDead = null;
            controller.addStartButtons();
        }
    }
    public void start(){
        controller.drawBackground();
        controller.createAnimal();
    }
    public void createAnimal(TypeOfAnimal typeOfAnimal, String name){
        animal = new Animal(typeOfAnimal, name);
        conditionThread = new ConditionThread();
        conditionThread.setDaemon(true);
        conditionThread.setAnimalAndImageLabel(animal);
        conditionThread.start();
    }

    public void feed(){
        animal.setLastFeed(new Date());
        animal.setSatiety(100);
        countOfFeed += 1;
        if(countOfFeed == 4){
            animal.rise(controller);
            countOfFeed = 0;
        }
    }
    public void checkFeed(){
        if(animal.getSatiety()<=70){
            controller.feed();
        }
    }

    public void play() {
        miniGames.get(0).playMiniGame();
    }

    public void setNewPlayDate() {
        tamagotchi.getAnimal().setLastPlay(new Date());
        tamagotchi.getAnimal().setMood(100);
    }
}
