package me.quasar.wumpus.utils;

import java.util.Random;

public class Constants {
	public static final int SPRITE_WIDTH = 16;
	public static final int SPRITE_HEIGHT = 16;
	public static final int SPRITE_SCALE = 4;
	public static final int SPRITE_ANIMATION_LENGTH = 4;
	public static final int SPRITE_ANIMATION_SPEED = 75;

	public static final int IMAGE_WIDTH = SPRITE_WIDTH * SPRITE_SCALE;
	public static final int IMAGE_HEIGHT = SPRITE_HEIGHT * SPRITE_SCALE;

	public static final int MAP_BORDER = 2;
	public static final int MAP_MAX_SIZE = 12;
	public static final int MAP_MIN_SIZE = 4;
	public static int MAP_SIZE = (MAP_MAX_SIZE + MAP_MIN_SIZE) / 2;
	public static int MAP_WIDTH = (MAP_SIZE + (MAP_BORDER * 2)) * IMAGE_WIDTH;
	public static int MAP_HEIGHT = (MAP_SIZE + (MAP_BORDER * 2)) * IMAGE_HEIGHT;

	public static final int INFOBOX_WIDTH = IMAGE_WIDTH * 5;

	public static final float ENTITY_SPEED = IMAGE_WIDTH / 10f;
	public static final int ENTITY_RANGE = 2;

	public static final String GAME_TITLE = "Wumpus";
	public static int GAME_WIDTH = (IMAGE_WIDTH * (MAP_SIZE + (MAP_BORDER * 2) + 1)) + INFOBOX_WIDTH;
	public static int GAME_HEIGHT = IMAGE_HEIGHT * (MAP_SIZE + (MAP_BORDER * 2));
	public static final int GAME_FPS = 60;
	public static final float GAME_TEXT_SIZE = 16f;
	public static final float GAME_FADESPEED = 0.04f;

	public static float INFOBOX_CENTER = GAME_WIDTH - ((GAME_WIDTH - (MAP_WIDTH + IMAGE_WIDTH)) / 2.0f);

	public static final Random RANDOM = new Random( );
	
	public static void UPDATE (int NEW_SIZE) {
		MAP_SIZE = NEW_SIZE;
		
		MAP_WIDTH = (MAP_SIZE + (MAP_BORDER * 2)) * IMAGE_WIDTH;
		MAP_HEIGHT = (MAP_SIZE + (MAP_BORDER * 2)) * IMAGE_HEIGHT;
		
		GAME_WIDTH = (IMAGE_WIDTH * (MAP_SIZE + (MAP_BORDER * 2) + 1)) + INFOBOX_WIDTH;
		GAME_HEIGHT = IMAGE_HEIGHT * (MAP_SIZE + (MAP_BORDER * 2));
		
		INFOBOX_CENTER = GAME_WIDTH - ((GAME_WIDTH - (MAP_WIDTH + IMAGE_WIDTH)) / 2.0f);
	}
}