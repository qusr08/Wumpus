package me.quasar.wumpus.states;

import java.awt.Graphics;

import me.quasar.wumpus.utils.Handler;

public class CreditsState extends State {

	public CreditsState (Handler handler) {
		super(handler);
	}

	@Override
	public void update ( ) {
		updatePanel( );
	}

	@Override
	public void render (Graphics graphics) {
		panel.render(graphics);
	}

	@Override
	public void init ( ) {

	}

}
