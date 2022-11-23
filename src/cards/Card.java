package cards;

import fileio.CardInput;

import java.util.ArrayList;

public abstract class Card {
    private final int               mana;
    private final String            description;
    private final String            name;
    private       ArrayList<String> colors;

    /**
     * @param source the card to be created
     */
    public Card(final CardInput source) {
        this.mana = source.getMana();
        this.description = source.getDescription();
        this.name = source.getName();
        this.colors = source.getColors();
    }

    /**
     * @param source the card to be copied
     */
    public Card(final Card source) {
        this.mana = source.getMana();
        this.description = source.getDescription();
        this.name = source.getName();
        this.colors = new ArrayList<>();

        this.colors.addAll(source.getColors());
    }

    /**
     * Get the mana cost of the card
     *
     * @return the mana cost of the card
     */
    public int getMana() {
        return mana;
    }

    /**
     * Get the description of the card
     *
     * @return the description of the card
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the name of the card.
     *
     * @return the name of the card
     */
    public String getName() {
        return name;
    }

    /**
     * Get the colors of the card
     *
     * @return the colors of the card
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * Set the colors of the card.
     *
     * @param colors the colors to be set
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = new ArrayList<>();

        this.colors.addAll(colors);
    }

    /**
     * Generate the output for getting the colors of the card in a JSON formatted string.
     *
     * @return the colors of the card in a string list
     */
    protected ArrayList<String> genColorsOutput() {
        ArrayList<String> output = new ArrayList<>(this.colors);
        for (int i = 0; i < output.size(); i++) {
            output.set(i, "\"" + output.get(i) + "\"");
        }

        return output;
    }

    protected abstract String genOutput();

    /**
     * @return the string version of the card
     */
    @Override
    public String toString() {
        return genOutput();
    }
}
