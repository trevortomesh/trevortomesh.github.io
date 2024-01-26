import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public class Cards extends Application {

    private static final String FILENAME = "data.json";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Card Data Entry");

        Label cardNameLabel = new Label("Card Name:");
        TextField cardNameField = new TextField();

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();

        Label textLabel = new Label("Text:");
        TextArea textField = new TextArea();

        Label imageLabel = new Label("Image Address:");
        TextField imageField = new TextField();

        Label tagsLabel = new Label("Tags (comma-separated):");
        TextField tagsField = new TextField();

        Button saveButton = new Button("Save to JSON");
        saveButton.setOnAction(e -> {
            String cardName = cardNameField.getText();
            String title = titleField.getText();
            String text = textField.getText();
            String image = imageField.getText();
            String[] tags = tagsField.getText().split(",");

            Gson gson = new Gson();
            JsonObject cardData = new JsonObject();
            cardData.addProperty("title", title);
            cardData.addProperty("text", text);
            cardData.addProperty("image", image);
            cardData.add("tags", gson.toJsonTree(tags));

            File file = new File(FILENAME);

            try {
                JsonParser parser = new JsonParser();
                JsonObject existingData = new JsonObject();
                if (file.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(FILENAME));
                    existingData = parser.parse(br).getAsJsonObject();
                    br.close();
                }

                if (existingData.has(cardName)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Card name already exists in the JSON file.");
                    alert.showAndWait();
                } else {
                    existingData.add(cardName, cardData);

                    FileWriter writer = new FileWriter(FILENAME);
                    gson.toJson(existingData, writer);
                    writer.close();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Data appended to " + FILENAME);
                    alert.showAndWait();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                cardNameLabel, cardNameField,
                titleLabel, titleField,
                textLabel, textField,
                imageLabel, imageField,
                tagsLabel, tagsField,
                saveButton
        );

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
