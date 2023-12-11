package Launcher;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import Interfaz.VisNovGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Mist";
		config.height = 600;
		config.width = 1000;

		new LwjglApplication(new VisNovGame(), config);
	}
}
