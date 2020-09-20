package testtask;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {         
    private Serialization serialization;
    private Tamagotchi tamagotchi;
    private Pane root;

    @Override
    public void start(Stage stage) throws Exception {
        serialization = new Serialization();
        tamagotchi = Tamagotchi.getInstance();
        serialization.deserialize(tamagotchi);
        root = tamagotchi.getController().getRoot();
        tamagotchi.getController().getTimer().start();
        Scene scene =  new Scene(root);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                serialization.serialize(tamagotchi);
            }
        });

        stage.setResizable(false);
        stage.setTitle("Tamagotchi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
