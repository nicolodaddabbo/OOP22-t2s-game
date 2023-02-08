package it.unibo.t2sgame;

import it.unibo.t2sgame.view.api.T2SLauncher;
//import it.unibo.t2sgame.view.impl.T2SSwingLauncher;
/**
 * Main class of the application.
 */
public final class T2S {
    private static final T2SLauncher LAUNCHER = () -> T2SFxApplication.launch(T2SFxApplication.class, "");
    private T2S() {
        throw new UnsupportedOperationException("This is a utility class");
    }
    //private static final T2SLauncher LAUNCHER = new T2SSwingLauncher();
    /**
     * main of the program.
     * @param args passed to the main
     */
    public static void main(final String... args) {
        LAUNCHER.launch();
    }
}
