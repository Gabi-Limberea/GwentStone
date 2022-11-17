package cards;

import fileio.CardInput;

import java.util.List;

public final class Card {
	private final int mana;
	private final String description;
	private final String name;
	private final List<String> colors;
	private int health;
	private final int attackDamage;

	public Card(final CardInput source) {
		this.mana = source.getMana();
		this.description = source.getDescription();
		this.name = source.getName();
		this.colors = source.getColors();
		this.health = source.getHealth();
		this.attackDamage = source.getAttackDamage();
	}

	public int getMana() {
		return mana;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public List<String> getColors() {
		return colors;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(final int health) {
		this.health = health;
	}

	public int getAttackDamage() {
		return attackDamage;
	}
}
