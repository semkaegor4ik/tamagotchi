package testtask;

import lombok.Getter;

import java.util.Date;

@Getter
public class ConditionThread extends Thread {
    private final UIController controller = UIController.getInstance();
    private Animal animal;
    private Date nowTime;

    public static final long onePercentInMillisecondsForSatiety = 288000;
    public static final long onePercentInMillisecondsForMood = 72000;

    public void setAnimal(Animal animal){
        this.animal = animal;
        controller.setAnimalLabels(animal);
    }

    @Override
    public void run() {

        while (!Condition.DIE.equals(animal.getCondition())) {
            nowTime = new Date();
            long now = nowTime.getTime();
            long lastFeed = animal.getLastFeed().getTime();
            long lastPlay = animal.getLastPlay().getTime();
            long zeroMoodTime = animal.getLastPlay().getTime() + 100 * onePercentInMillisecondsForMood;
            long mood = 100 - (now - lastPlay) / onePercentInMillisecondsForMood;
            long satiety;
            if(now > zeroMoodTime){
                satiety = 100 - (zeroMoodTime - lastFeed) / onePercentInMillisecondsForSatiety - (now - zeroMoodTime) / (onePercentInMillisecondsForSatiety / 2);
            }
            else {
                satiety = 100 - (now - lastFeed) / onePercentInMillisecondsForSatiety;
            }
            if (satiety <= 0) {
                animal.setCondition(Condition.DIE);
            }
            if ((mood < 50 || satiety < 50) &&
                    Condition.HAPPY.equals(animal.getCondition())) {
                animal.setCondition(Condition.NORMAL);
                controller.getCondition().setImage(animal.getCondition().getImage());
            }
            if (mood < 0) {
                mood = 0;
            }
            if ((mood == 0 || satiety < 20) &&
                    Condition.NORMAL.equals(animal.getCondition())) {
                animal.setCondition(Condition.BAD);
                controller.getCondition().setImage(animal.getCondition().getImage());
            }
            if (animal.getMood() > 95 && animal.getSatiety() > 70 &&
                    !Condition.HAPPY.equals(animal.getCondition())) {
                animal.setCondition(Condition.HAPPY);
                controller.getCondition().setImage(animal.getCondition().getImage());
            }
            if (satiety != animal.getSatiety()) {
                animal.setSatiety((int) satiety);
            }
            if (mood != animal.getMood()) {
                animal.setMood((int) mood);
            }
        }
    }
}
