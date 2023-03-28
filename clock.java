import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JavaFXClock extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a group to hold all the elements of the clock
        Group root = new Group();

        // Create the clock face (a circle)
        Circle face = new Circle(100, 100, 90);
        face.setFill(Color.WHITE);
        face.setStroke(Color.BLACK);
        face.setStrokeWidth(2);
        root.getChildren().add(face);

        // Create the clock ticks (lines)
        for (int i = 0; i < 12; i++) {
            Line tick = new Line(0, -80, 0, -70);
            tick.setStroke(Color.BLACK);
            tick.setStrokeWidth(2);
            tick.setRotate(i * (360 / 12));
            tick.setTranslateX(100);
            tick.setTranslateY(100);
            root.getChildren().add(tick);
        }

        // Create the hour, minute, and second hands (lines)
        Line hourHand = new Line(0, 0, 0, -50);
        hourHand.setStroke(Color.BLACK);
        hourHand.setStrokeWidth(4);
        hourHand.setTranslateX(100);
        hourHand.setTranslateY(100);
        root.getChildren().add(hourHand);

        Line minuteHand = new Line(0, 0, 0, -70);
        minuteHand.setStroke(Color.BLACK);
        minuteHand.setStrokeWidth(3);
        minuteHand.setTranslateX(100);
        minuteHand.setTranslateY(100);
        root.getChildren().add(minuteHand);

        Line secondHand = new Line(0, 10, 0, -80);
        secondHand.setStroke(Color.RED);
        secondHand.setStrokeWidth(2);
        secondHand.setTranslateX(100);
        secondHand.setTranslateY(100);
        root.getChildren().add(secondHand);

        // Create the digital clock display (text)
        Text digitalClock = new Text();
        digitalClock.setFont(Font.font("Arial", 24));
        digitalClock.setFill(Color.BLACK);
        digitalClock.setEffect(new DropShadow());
        digitalClock.setTranslateX(100 - digitalClock.getLayoutBounds().getWidth() / 2);
        digitalClock.setTranslateY(200);
        root.getChildren().add(digitalClock);

        // Create a timeline to update the clock every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalTime time = LocalTime.now();

                // Update the hour, minute, and second hands
                hourHand.setRotate(time.getHour() * (360 / 12) + time.getMinute() * (360 / (12 * 60)));
                minuteHand.setRotate(time.getMinute() * (360 / 60));
                secondHand.setRotate(time.getSecond() * (360 / 60));

                // Update the digital clock display
                digitalClock.setText(time.format(DateTimeFormatter.ofPattern("hh:mm:ss a")));
                digitalClock.setTranslateX(100 - digitalClock.getLayout
