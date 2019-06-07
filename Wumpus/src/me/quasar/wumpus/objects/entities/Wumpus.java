package me.quasar.wumpus.objects.entities;

import java.awt.Graphics;

import me.quasar.wumpus.graphics.Assets;
import me.quasar.wumpus.objects.Map;
import me.quasar.wumpus.utils.Constants;

public class Wumpus extends Entity {
	private boolean hidden;

	public Wumpus (float x, float y, Map map) {
		super(x, y, Constants.ENTITY_SPEED, map);

		animation = Assets.wumpusIdle;
	}

	@Override
	public void update ( ) {
		animation.update( );

		hidden = (map.getTile(moveTileX + Constants.MAP_BORDER, moveTileY + Constants.MAP_BORDER).isHidden( )
			|| map.getTile(moveTileX + Constants.MAP_BORDER, moveTileY + Constants.MAP_BORDER).isCovered( ))
			&& (map.getTile(tileX + Constants.MAP_BORDER, tileY + Constants.MAP_BORDER).isHidden( )
				|| map.getTile(tileX + Constants.MAP_BORDER, tileY + Constants.MAP_BORDER).isCovered( ));

		move( );
	}

	@Override
	public void render (Graphics graphics) {
		if (!hidden) {
			graphics.drawImage(animation.getCurrentFrame( ), (int) x, (int) y, null);
		}
	}

	@Override
	protected void updateAnimations ( ) {
		if (moveTileX > tileX) {
			setAnimation(Assets.wumpusMoveRight);
		} else if (moveTileX < tileX) {
			setAnimation(Assets.wumpusMoveLeft);
		} else if (moveTileY > tileY) {
			setAnimation(Assets.wumpusMoveDown);
		} else if (moveTileY < tileY) {
			setAnimation(Assets.wumpusMoveUp);
		} else {
			setAnimation(Assets.wumpusIdle);
		}
	}

	public void moveRandomly ( ) {
		boolean moved = false;

		while (!moved) {
			int randomDirection = Constants.RANDOM.nextInt(5);
			switch (randomDirection) {
				case 0 :
					moved = moveUp(true, false);
					break;
				case 1 :
					moved = moveRight(true, false);
					break;
				case 2 :
					moved = moveDown(true, false);
					break;
				case 3 :
					moved = moveLeft(true, false);
					break;
				case 4 :
					moved = true;
					break;
			}
		}
	}

}
