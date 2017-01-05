package bot;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Jo on 30.12.2016.
 * Creates the gui and starts the bot.
 */
public class Main extends Application{

    private TextArea chat;
    private TextField inputField;
    private Button send;
    private ImageView face;

    public static void main(String[] args){
        launch();
    }

    //GUI//
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("...");
        primaryStage.setResizable(false);
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: #ffffff");
        primaryStage.setScene(new Scene(root, 16*30, 740));

        initComponents(root);

        primaryStage.show();
        try{
            new ChatBot(chat, inputField, send, face);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initalizes the components on the root pane
     * @param root    Root pane
     */
    private void initComponents(VBox root) {
        //Face image
        face = new ImageView(new Image("res/smile.png", 16*30, 16*30, false, false));
        root.getChildren().add(face);

        //chat
        chat = new TextArea();
        chat.setEditable(false);
        chat.setFocusTraversable(false);
        chat.setBorder((new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))));
        chat.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ;");
        root.getChildren().add(chat);

        //input field
        inputField = new TextField();
        root.getChildren().add(inputField);

        //send button
        root.setAlignment(Pos.CENTER);
        send = new Button("SEND");
        send.setPrefSize(16*30, 30);
        root.getChildren().add(send);
    }
}
