package testtask;

import lombok.Data;

import java.io.*;
import java.util.Date;

@Data
public class Serialization implements Serializable {

    private Date dateOfDead;
    private Animal animal;
    private int countOfFeed;

    public void serialize(Tamagotchi tamagotchi){
        dateOfDead = tamagotchi.getDateOfDead();
        animal = tamagotchi.getAnimal();
        countOfFeed = tamagotchi.getCountOfFeed();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tamagotchi.dat")))
        {
            oos.writeObject(this);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void deserialize(Tamagotchi tamagotchi){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tamagotchi.dat")))
        {
            ConditionThread conditionThread = new ConditionThread();
            Serialization serialization = (Serialization)ois.readObject();
            if(serialization.animal!= null) {
                tamagotchi.drawBackground();
                tamagotchi.setDateOfDead(serialization.dateOfDead);
                tamagotchi.setAnimal(serialization.animal);
                tamagotchi.setCountOfFeed(serialization.countOfFeed);
                conditionThread.setAnimalAndImageLabel(serialization.animal);
                if (!Condition.DIE.equals(serialization.animal.getCondition()))
                    conditionThread.start();
                tamagotchi.setConditionThread(conditionThread);
                tamagotchi.getController().drawAllElements(serialization.animal);
            }
            else{
                tamagotchi.start();
            }
        }
        catch(IOException|ClassNotFoundException ex){
            tamagotchi.start();
        }
    }
}
