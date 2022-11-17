package cards;

import fileio.CardInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class Hero {
	private final        String            name;
	private final        int               mana;
	private              int               health;
	private final        ArrayList<String> colors;
	private static final int               HEALTH = 30;

	public Hero(final @NotNull CardInput source) {
		this.name = source.getName();
		this.mana = source.getMana();
		this.health = HEALTH;
		this.colors = source.getColors();
	}

	public void setHealth(final int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public ArrayList<String> getColors() {
		return colors;
	}

	public int getMana() {
		return mana;
	}

	public String getName() {
		return name;
	}
}
