package part3.simple.hello.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloJavafx extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Создаём корневой элемент сцены
        Group root = new Group();
        // Создаём сцену и задаём её размеры
        Scene scene = new Scene(root, 400, 300);
        // Связываем сцену и окно
        primaryStage.setScene(scene);
        // Задаём заголовок окну
        primaryStage.setTitle("Hello, JavaFX");
        // Показываем окно
        primaryStage.show();
    }
    public static void main(String[] args) {
        // Запускаем JavaFX-приложение
        launch(args);
    }
}