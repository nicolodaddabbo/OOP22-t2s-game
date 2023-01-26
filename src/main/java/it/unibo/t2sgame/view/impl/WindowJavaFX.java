package it.unibo.t2sgame.view.impl;

import it.unibo.t2sgame.view.api.Window;
import javafx.stage.Stage;

public class WindowJavaFX implements Window{

    private final Stage stage;

    public WindowJavaFX(final Stage stage){
        this.stage = stage;
    }

    @Override
    public void launch() {
        var menu = new SceneFactoryJavaFXImpl(this.stage).createMenuScene(); 
        menu.initialize();

        this.stage.show();
    }
    
}
