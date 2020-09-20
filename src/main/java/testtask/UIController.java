package testtask;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import testtask.miniGame.Tool;

import java.io.Serializable;

@Getter
@Setter
public class UIController implements Serializable {

    private Tool animalTool;
    private Tool playerTool;
    private final Pane root;
    private static UIController controller;
    private Tamagotchi tamagotchi;

    private final AnimationTimer timer;
    //main
    private final ImageView background;
    private final ImageView imageViewEgg;
    private final ImageView imageRIP;

    private final ImageView imageFoodButton;
    private final ImageView imageFood;
    private final ImageView joyStick;
    private final ImageView imageArrowRight;
    private final ImageView imageArrowLeft;

    private final Button playButton;
    private final Button feedButton;
    private final Button left;
    private final Button right;
    private final Button enter;
    private final HBox buttons;
    private final ToggleGroup startGroup;
    private final ToggleButton cat;
    private final ToggleButton dog;
    private final ToggleButton hamster;
    private final ToggleButton rabbit;
    private final ToggleButton turtle;
    private final ToggleButton pig;
    private final TextField name;
    private final TranslateTransition transition;
    //thread
    private final ImageView imageHeartView;
    private final ImageView imageMoodView;
    private final ImageView condition;
    private final Label heartLabel = new Label();
    private final Label moodLabel = new Label();
    //animal
    private ImageView imageView;
    private Label imageLabel;
    //rockPaperScissors
    private ImageView playerRockPaperScissorsView;
    private final HBox rockPaperScissorsButtons;
    private final ToggleGroup rockPaperScissorsGroup;
    private final ToggleButton rock;
    private final ToggleButton paper;
    private final ToggleButton scissors;
    private ImageView animalRockPaperScissorsView;
    private final TranslateTransition animalTransition;
    private final TranslateTransition playerTransition;

    private UIController(){}
    public static UIController getInstance(){
        if(controller == null){
            controller = new UIController();
            return controller;
        }
        else{
            return  controller;
        }
    }

    {
        root = new Pane();
        root.setPrefSize(Tamagotchi.weight, Tamagotchi.height);
        root.setPrefSize(Tamagotchi.weight, Tamagotchi.height);
        imageViewEgg = new ImageView(new Image("egg.png"));
        imageViewEgg.setPreserveRatio(true);
        imageViewEgg.setFitWidth(130);
        imageViewEgg.setTranslateX(Tamagotchi.weight / 2 - imageViewEgg.getFitWidth() / 2);
        imageViewEgg.setTranslateY(Tamagotchi.height/3*2);

        imageRIP = new ImageView();
        imageRIP.setPreserveRatio(true);
        imageRIP.setFitWidth(130);
        imageRIP.setImage(Condition.DIE.getImage());

        background = new ImageView(new Image("background.jpg"));
        imageArrowRight = new ImageView(new Image("buttons/arrowRight.png"));
        imageArrowRight.setPreserveRatio(true);
        imageArrowRight.setFitWidth(50);
        imageArrowLeft = new ImageView(new Image("buttons/arrowLeft.png"));
        imageArrowLeft.setPreserveRatio(true);
        imageArrowLeft.setFitWidth(50);

        background.setFitHeight(Tamagotchi.height);
        background.setFitWidth(Tamagotchi.weight);

        transition = new TranslateTransition(Duration.seconds(3));
        transition.setFromX(Tamagotchi.weight / 2);
        transition.setFromY(Tamagotchi.height);

        joyStick = new ImageView(new Image("buttons/joystick.png"));
        joyStick.setPreserveRatio(true);
        joyStick.setFitWidth(50);
        imageFoodButton = new ImageView(new Image("buttons/plate.png"));
        imageFoodButton.setPreserveRatio(true);
        imageFoodButton.setFitWidth(50);

        buttons = new HBox();
        startGroup = new ToggleGroup();
        cat = new ToggleButton("CAT");
        dog = new ToggleButton("DOG");
        hamster = new ToggleButton("HAMSTER");
        rabbit = new ToggleButton("RABBIT");
        turtle = new ToggleButton("TURTLE");
        pig = new ToggleButton("PIG");

        buttons.setTranslateX(Tamagotchi.weight / 2);
        buttons.setTranslateY(Tamagotchi.height / 2);
        cat.setToggleGroup(startGroup);
        dog.setToggleGroup(startGroup);
        hamster.setToggleGroup(startGroup);
        rabbit.setToggleGroup(startGroup);
        turtle.setToggleGroup(startGroup);
        pig.setToggleGroup(startGroup);

        cat.setUserData(TypeOfAnimal.CAT);
        dog.setUserData(TypeOfAnimal.DOG);
        hamster.setUserData(TypeOfAnimal.HAMSTER);
        rabbit.setUserData(TypeOfAnimal.RABBIT);
        turtle.setUserData(TypeOfAnimal.TURTLE);
        pig.setUserData(TypeOfAnimal.PIG);
        imageFood = new ImageView();
        imageFood.setPreserveRatio(true);
        imageFood.setFitWidth(50);
        buttons.getChildren().addAll(cat, dog, hamster, rabbit, turtle, pig);
        name = new TextField();
        enter = new Button("CREATE");
        name.setTranslateX(Tamagotchi.weight / 2);
        name.setTranslateY(Tamagotchi.height / 2 + 30);
        enter.setTranslateX(Tamagotchi.weight / 2);
        enter.setTranslateY(Tamagotchi.height / 2 + 60);
        name.setPromptText("Enter animal name");

        playButton = new Button();
        playButton.setGraphic(joyStick);
        playButton.setOnAction(event -> tamagotchi.play());
        playButton.setTranslateX(Tamagotchi.weight / 5 * 4);
        playButton.setTranslateY(Tamagotchi.height-70);
        feedButton = new Button();
        feedButton.setGraphic(imageFoodButton);
        feedButton.setOnAction(event -> tamagotchi.checkFeed());
        feedButton.setTranslateX(Tamagotchi.weight / 5 * 3);
        feedButton.setTranslateY(Tamagotchi.height-70);

        left = new Button();
        right = new Button();
        left.setTranslateX(Tamagotchi.weight / 5);
        left.setTranslateY(Tamagotchi.height-70);
        right.setTranslateX(Tamagotchi.weight / 5 * 2);
        right.setTranslateY(Tamagotchi.height-70);

        right.setGraphic(imageArrowRight);
        left.setGraphic(imageArrowLeft);
        enter.setOnAction(event->{
            if(!name.getText().isEmpty() &&
                    startGroup.getSelectedToggle() !=null){
                tamagotchi.createAnimal((TypeOfAnimal)startGroup.getSelectedToggle().getUserData(), name.getText());
                root.getChildren().addAll(imageLabel, feedButton, playButton, left, right);
                root.getChildren().removeAll(buttons,name, enter,imageViewEgg);
            }
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(tamagotchi.getAnimal()!=null){
                    heartLabel.setText(String.valueOf(tamagotchi.getAnimal().getSatiety()));
                    moodLabel.setText(String.valueOf(tamagotchi.getAnimal().getMood()));
                    if(right.isPressed()){
                        tamagotchi.moveRight();
                        imageView.setScaleX(-1);
                        imageLabel.setTranslateX(tamagotchi.getAnimal().getX());
                    }
                    else if(left.isPressed()){
                        tamagotchi.moveLeft();
                        imageView.setScaleX(1);
                        imageLabel.setTranslateX(tamagotchi.getAnimal().getX());
                    }
                    tamagotchi.die();
                }
            }
        };

        //rockPaperScissorsGame
        rockPaperScissorsButtons = new HBox();
        rockPaperScissorsGroup = new ToggleGroup();
        rock = new ToggleButton("ROCK");
        paper = new ToggleButton("PAPER");
        scissors = new ToggleButton("SCISSORS");

        rockPaperScissorsButtons.setTranslateX(Tamagotchi.weight / 2);
        rockPaperScissorsButtons.setTranslateY(Tamagotchi.height / 3);
        rock.setToggleGroup(rockPaperScissorsGroup);
        paper.setToggleGroup(rockPaperScissorsGroup);
        scissors.setToggleGroup(rockPaperScissorsGroup);

        rock.setUserData(Tool.ROCK);
        paper.setUserData(Tool.PAPER);
        scissors.setUserData(Tool.SCISSORS);
        rockPaperScissorsButtons.getChildren().addAll(rock, paper, scissors);
        playerRockPaperScissorsView = new ImageView();
        playerRockPaperScissorsView.setPreserveRatio(true);
        playerRockPaperScissorsView.setFitWidth(150);

        animalRockPaperScissorsView = new ImageView();
        animalRockPaperScissorsView.setPreserveRatio(true);
        animalRockPaperScissorsView.setFitWidth(150);

        animalTransition = new TranslateTransition(Duration.seconds(3));
        animalTransition.setFromX(Tamagotchi.weight / 3);
        animalTransition.setFromY(Tamagotchi.height / 2);
        animalTransition.setToX(Tamagotchi.weight / 3);
        animalTransition.setToY(-150);
        playerTransition = new TranslateTransition(Duration.seconds(3));
        playerTransition.setFromX(Tamagotchi.weight / 3 * 2);
        playerTransition.setFromY(Tamagotchi.height / 2);
        playerTransition.setToX(Tamagotchi.weight / 3 * 2);
        playerTransition.setToY(Tamagotchi.height);

        playerTransition.setOnFinished(transitionEvent -> {
            root.getChildren().removeAll(animalRockPaperScissorsView, playerRockPaperScissorsView);
            tamagotchi.getRockPaperScissorsGame().checkWinner(playerTool, animalTool);
        });

        //animal

        imageLabel = new Label();
        imageView = new ImageView();
        imageView.setPreserveRatio(true);

        //thread

        imageHeartView = new ImageView(new Image("heart.png"));
        imageMoodView = new ImageView(new Image("gamepad.png"));
        condition = new ImageView();

        condition.setPreserveRatio(true);
        condition.setFitWidth(Tamagotchi.stateOfImageSize);
        imageHeartView.setPreserveRatio(true);
        imageHeartView.setFitWidth(Tamagotchi.stateOfImageSize);
        imageMoodView.setPreserveRatio(true);
        imageMoodView.setFitWidth(Tamagotchi.stateOfImageSize);

        heartLabel.setGraphic(imageHeartView);
        moodLabel.setGraphic(imageMoodView);

        heartLabel.setTranslateX(Tamagotchi.weight - 150);
        heartLabel.setTranslateY(50);

        moodLabel.setTranslateX(Tamagotchi.weight - 150);
        moodLabel.setTranslateY(100);

        condition.setTranslateX(Tamagotchi.weight - 150);
        condition.setTranslateY(150);

        heartLabel.setGraphicTextGap(20);
        heartLabel.setContentDisplay(ContentDisplay.LEFT);
        moodLabel.setGraphicTextGap(20);
        moodLabel.setContentDisplay(ContentDisplay.LEFT);
    }

    public void killAnimal(Animal animal){
        root.getChildren().removeAll(heartLabel, moodLabel, condition, left, right, feedButton, playButton);
        imageLabel.setGraphic(imageRIP);
        imageLabel.setTranslateX(animal.getX());
        imageLabel.setTranslateY(Tamagotchi.height/3*2);
    }

    public void rise(MaturationStage maturationStage){
        imageView.setFitWidth(maturationStage.getImageWidth());
        imageLabel.setTranslateY(imageLabel.getTranslateY() - 50);
    }

    public void deleteRIP(){
        root.getChildren().removeAll(imageLabel);
    }

    public void addStartButtons(){
        root.getChildren().addAll(imageViewEgg, name, enter, buttons);
    }

    public void feed(){
        root.getChildren().addAll(imageFood);
        transition.setNode(imageFood);
        transition.setToX(imageLabel.getTranslateX() + tamagotchi.getAnimal().getMaturationStage().getImageWidth() / 2 - imageFood.getFitWidth() / 2);
        transition.setToY(imageLabel.getTranslateY() + 40);
        transition.setOnFinished(event->{
            root.getChildren().removeAll(imageFood);
            tamagotchi.feed();
        });
        transition.play();
    }

    public void playRockPaperScissors(Tool choseTool){
        if (!root.getChildren().contains(animalRockPaperScissorsView) &&
                !root.getChildren().contains(playerRockPaperScissorsView) &&
                !root.getChildren().contains(rockPaperScissorsButtons)) {
            root.getChildren().addAll(rockPaperScissorsButtons);
            rockPaperScissorsGroup.selectedToggleProperty().addListener(event -> {
                if (rockPaperScissorsGroup.getSelectedToggle() != null) {
                    playerTool = (Tool) rockPaperScissorsGroup.getSelectedToggle().getUserData();
                    animalTool = choseTool;
                    root.getChildren().removeAll(animalRockPaperScissorsView, playerRockPaperScissorsView);
                    playerRockPaperScissorsView.setImage(playerTool.getImage());
                    animalRockPaperScissorsView.setImage(animalTool.getImage());
                    animalTransition.setNode(animalRockPaperScissorsView);
                    playerTransition.setNode(playerRockPaperScissorsView);
                    root.getChildren().addAll(animalRockPaperScissorsView, playerRockPaperScissorsView);
                    animalTransition.play();
                    playerTransition.play();

                    root.getChildren().removeAll(rockPaperScissorsButtons);
                }
            });
        }
    }

    public void setAnimalLabels(Animal animal){
        imageView.setImage(new Image(tamagotchi.getAnimal().getTypeOfAnimal().getImgPath()));
        imageView.setFitWidth(tamagotchi.getAnimal().getMaturationStage().getImageWidth());
        imageLabel.setText(tamagotchi.getAnimal().getName());
        imageLabel.setGraphic(imageView);
        imageLabel.setTranslateX(tamagotchi.getAnimal().getX());
        imageLabel.setTranslateY(Tamagotchi.height/3*1.75);
        imageLabel.setGraphicTextGap(20);
        imageLabel.setContentDisplay(ContentDisplay.BOTTOM);

        imageFood.setImage(animal.getTypeOfAnimal().getFood().getImage());
        if(!Condition.DIE.equals(animal.getCondition())) {
            condition.setImage(animal.getCondition().getImage());
            heartLabel.setText(String.valueOf(animal.getSatiety()) + "%");
            moodLabel.setText(String.valueOf(animal.getMood()) + "%");
            root.getChildren().addAll(heartLabel, moodLabel, condition);
        }
    }

    public void drawBackground(){
        root.getChildren().addAll(background);
    }
    public void createAnimal(){
        root.getChildren().addAll(imageViewEgg, name, enter, buttons);
    }
    public void drawAllElements(Animal animal){
        if(MaturationStage.ADULT.equals(animal.getMaturationStage()))
        {
            imageLabel.setTranslateY(imageLabel.getTranslateY() - 100);
        }
        else if(MaturationStage.TEEN.equals(animal.getMaturationStage()))
        {
            imageLabel.setTranslateY(imageLabel.getTranslateY() - 50);
        }
        if(Condition.DIE.equals(animal.getCondition())){
            imageLabel.setGraphic(imageRIP);
            imageLabel.setTranslateX(animal.getX());
            imageLabel.setTranslateY(Tamagotchi.height/3*2);
            root.getChildren().addAll(imageLabel);
        }
        else {
            root.getChildren().addAll(imageLabel, feedButton, playButton, left, right);
        }
    }
}

