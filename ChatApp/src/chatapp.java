import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

public class chatapp extends Application {

    private TextArea chatArea;
    private TextField inputField;
    private Button sendButton;
    private PrintWriter out;

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 59001;

    @Override
    public void start(Stage primaryStage) {

        // Chat area
        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        // Input field
        inputField = new TextField();
        inputField.setPromptText("Type a message...");

        // Send button
        sendButton = new Button("Send");
        
     // Send when button is clicked
        sendButton.setOnAction(e -> sendMessage());

        // Send when Enter key is pressed
        inputField.setOnAction(e -> sendMessage());

        // Bottom bar
        HBox bottomBar = new HBox(10, inputField, sendButton);
        inputField.setPrefWidth(400);

        // Main layout
        BorderPane layout = new BorderPane();
        layout.setCenter(chatArea);
        layout.setBottom(bottomBar);

        // Window setup
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setTitle("Lehman Chat");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ask for name then connect
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Welcome!");
        dialog.setHeaderText("Enter your name to join the chat:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> connectToServer(name));
    }

    private void connectToServer(String name) {
    	
    
    	
        try {
            Socket socket = new Socket(SERVER_ADDRESS, PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            // Read and ignore the server's name prompt
            in.readLine();

            // Send our name
            out.println(name);

            // Background thread to listen for messages
            Thread listenerThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        final String msg = message;
                        Platform.runLater(() -> {
                            chatArea.appendText(msg + "\n");
                        });
                    }
                } catch (IOException e) {
                    Platform.runLater(() -> {
                        chatArea.appendText("Disconnected from server.\n");
                    });
                }
            });
            listenerThread.setDaemon(true);
            listenerThread.start();

        } catch (IOException e) {
            chatArea.appendText("Could not connect to server.\n");
        }
    }
    
    private void sendMessage() {
	    String message = inputField.getText();
	    if (message != null && !message.isEmpty()) {
	        out.println(message);
	        inputField.clear();
	    }
	}

    public static void main(String[] args) {
        launch(args);
    }
}