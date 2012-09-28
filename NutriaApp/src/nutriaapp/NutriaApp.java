/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NutriaApp;

import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import NutriaModel.DbConnection;
import NutriaModel.Ingredient;
import NutriaModel.Nutrient;
import NutriaModel.NutrientDao;
import NutriaModel.NutrientDaoImpl;

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
        
        NutrientDao a = new NutrientDaoImpl();
        
        try {
            DbConnection conn = new DbConnection();
            TableUtils.createTableIfNotExists(conn.getConnection(), Nutrient.class);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        Ingredient ing = new Ingredient();
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
