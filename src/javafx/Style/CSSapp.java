import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CSSapp extends Application
{
  @Override
  public void start(Stage primaryStage) throws Exception
  {
    Parent root = FXMLLoader.load(getClass().getResource(
        "src/javafx/Style/css_layout.fxml"));
    Scene scene = new Scene(root, 800, 500);
    scene.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
    PrimaryStage.setScene(scene);
    primaryStage.show();
  }
  public static void main(String[] args)
  {
    launch(args);
  }

}