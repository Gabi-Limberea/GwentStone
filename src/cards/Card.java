package cards;

import fileio.CardInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Card implements Cloneable {
    private final int               mana;
    private final String            description;
    private final String            name;
    private       ArrayList<String> colors;

    /**
     * @param source
     */
    public Card(final @NotNull CardInput source) {
        this.mana = source.getMana();
        this.description = source.getDescription();
        this.name = source.getName();
        this.colors = source.getColors();
    }

    /**
     * @param source
     */
    public Card(final @NotNull Card source) {
        this.mana = source.getMana();
        this.description = source.getDescription();
        this.name = source.getName();
        this.colors = new ArrayList<>();

        this.colors.addAll(source.getColors());
    }

    /**
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @return
     */
    public void setColors(ArrayList<String> colors) {
        this.colors = new ArrayList<>();

        this.colors.addAll(colors);
    }

    /**
     * @return
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * @return
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
     * @return
     */
    @Override
    public String toString() {
        return genOutput();
    }
}
