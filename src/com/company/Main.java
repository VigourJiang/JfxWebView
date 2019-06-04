package com.company;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(final Stage stage) {
        stage.setWidth(1500);
        stage.setHeight(800);
        Scene scene = new Scene(new Group());

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(browser);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        webEngine.getLoadWorker().stateProperty()
                .addListener(new ChangeListener<State>() {
                    @Override
                    public void changed(ObservableValue ov, State oldState, State newState) {

                        if (newState == Worker.State.SUCCEEDED) {
                            stage.setTitle(webEngine.getLocation());
                        }

                    }
                });
        // 能播放
        webEngine.load("https://developer.mozilla.org/en-US/docs/Web/HTML/Element/video");
        // 不能播放
        // webEngine.load("http://html5videoformatconverter.com/html5-video-tag-example.html");
        // 可以播放
        // webEngine.load("https://videojs.com/advanced/#disneys-oceans");
        // 可以播放，但是播放的控件有问题。
        //webEngine.load("https://www.runoob.com/try/try.php?filename=tryhtml5_video_all");

        scene.setRoot(scrollPane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}