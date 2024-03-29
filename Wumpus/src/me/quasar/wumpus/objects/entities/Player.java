package me.quasar.wumpus.objects.entities;

import java.awt.Graphics;

import me.quasar.wumpus.graphics.Assets;
import me.quasar.wumpus.objects.Map;
import me.quasar.wumpus.objects.items.Item;
import me.quasar.wumpus.objects.menus.inventory.Inventory;
import me.quasar.wumpus.utils.Constants;
import me.quasar.wumpus.utils.Handler;

public class Player extends Entity {
	private int torchCount;

	private Inventory inventory;
	private Arrow arrow;
	private Handler handler;

	private int lastDirectionMoved = -1;
	private boolean dead = false;
	private int deadCount = 0;

	public Player (float x, float y, Map map, Handler handler) {
		super(x, y, Constants.ENTITY_SPEED, map);

		this.handler = handler;

		inventory = new Inventory(4, handler);

		animation = Assets.playerIdle;
	}

	@Override
	public void update ( ) {
		animation.update( );

		inventory.update( );

		if (arrow != null && arrow.isDestroyed( )) {
			arrow = null;
		}

		if (!dead) {
			move( );
		} else {
			y -= moveSpeed;
			x += 2 * Math.cos(0.05 * deadCount);
			deadCount++;
		}
	}

	@Override
	public void render (Graphics graphics) {
		graphics.drawImage(animation.getCurrentFrame( ), (int) x, (int) y, null);

		inventory.render(graphics);

		if (arrow != null) {
			arrow.render(graphics);
		}
	}

	@Override
	protected void updateAnimations ( ) {
		if (!dead) {
			if (moveTileX > tileX) {
				if (hasTorch( )) {
					setAnimation(Assets.playerMoveRightTorch);
				} else {
					setAnimation(Assets.playerMoveRight);
				}

				lastDirectionMoved = Constants.RIGHT;
			} else if (moveTileX < tileX) {
				if (hasTorch( )) {
					setAnimation(Assets.playerMoveLeftTorch);
				} else {
					setAnimation(Assets.playerMoveLeft);
				}

				lastDirectionMoved = Constants.LEFT;
			} else if (moveTileY > tileY) {
				if (hasTorch( )) {
					setAnimation(Assets.playerMoveDownTorch);
				} else {
					setAnimation(Assets.playerMoveDown);
				}

				lastDirectionMoved = Constants.DOWN;
			} else if (moveTileY < tileY) {
				if (hasTorch( )) {
					setAnimation(Assets.playerMoveUpTorch);
				} else {
					setAnimation(Assets.playerMoveUp);
				}

				lastDirectionMoved = Constants.UP;
			} else {
				if (hasTorch( )) {
					setAnimation(Assets.playerIdleTorch);
				} else {
					setAnimation(Assets.playerIdle);
				}
			}
		} else {
			setAnimation(Assets.playerDead);
		}
	}

	public boolean hasTorch ( ) {
		return torchCount > 0;
	}

	public int getTorchCount ( ) {
		return torchCount;
	}

	public boolean shootArrow (int direction) {
		arrow = new Arrow(x, y, direction, map);

		return true;
	}

	public boolean swingSword (int objectTileX, int objectTileY, int direction) {
		switch (direction) {
			case Constants.UP :
				return (this.tileX - 1 == objectTileX || this.tileX == objectTileX || this.tileX + 1 == objectTileX) && this.tileY - 1 == objectTileY;
			case Constants.RIGHT :
				return (this.tileY - 1 == objectTileY || this.tileY == objectTileY || this.tileY + 1 == objectTileY) && this.tileX + 1 == objectTileX;
			case Constants.DOWN :
				return (this.tileX - 1 == objectTileX || this.tileX == objectTileX || this.tileX + 1 == objectTileX) && this.tileY + 1 == objectTileY;
			case Constants.LEFT :
				return (this.tileY - 1 == objectTileY || this.tileY == objectTileY || this.tileY + 1 == objectTileY) && this.tileX - 1 == objectTileX;
		}

		return false;
	}

	public boolean arrowIsDestroyed ( ) {
		return arrow == null;
	}

	public void addItem (Item item) {
		if (item.getId( ) == Constants.ID_TORCH) {
			torchCount++;
		} else {
			inventory.addItem(item);
		}
	}

	public void removeItem (int index) {
		inventory.removeItem(index);
	}

	public Inventory getInventory ( ) {
		return inventory;
	}

	public int getLastDirectionMoved ( ) {
		return lastDirectionMoved;
	}

	public boolean hasWeapon ( ) {
		if (getWeapon( ) != null) {
			return getWeapon( ).getId( ) == Constants.ID_BOW || getWeapon( ).getId( ) == Constants.ID_SWORD;
		}
		return false;
	}

	public boolean hasFlashLight ( ) {
		if (getWeapon( ) != null) {
			return getWeapon( ).getId( ) == Constants.ID_FLASHLIGHT;
		}
		return false;
	}

	public Item getWeapon ( ) {
		return inventory.getItem(0);
	}

	public Arrow getArrow ( ) {
		return arrow;
	}

	public void setDead (boolean dead) {
		this.dead = dead;

		updateAnimations( );
	}

}
