package com.mygdx.game.desktop;

import LocationGenerator.Barrier;
import LocationGenerator.Location;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameLibGDX;
import java.util.ArrayList;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameLibGDX(), config);         
               
                
	}
}
