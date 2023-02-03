package it.unibo.t2sgame;

import it.unibo.t2sgame.view.api.T2SLauncher;
//import it.unibo.t2sgame.view.impl.T2SSwingLauncher;

public class T2S {
    private static final T2SLauncher launcher = () -> T2SFxApplication.launch(T2SFxApplication.class, "");
    //private static final T2SLauncher launcher = new T2SSwingLauncher();
    public static void main(final String... args) {
        launcher.launch();
    }
}
