package NutriaApp;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Enzo
 */
public class NutriaApp extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws SQLException {
        JFrameMain jFrameMain = new JFrameMain();
        jFrameMain.setVisible(true);
    }
}
