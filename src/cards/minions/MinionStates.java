package cards.minions;

public enum MinionStates {
    ACTIVE, FROZEN;

    private int timeStamp;
    private int freezeTimeStamp;

    MinionStates() {
        timeStamp = 0;
        freezeTimeStamp = 0;
    }
    public int getFreezeTimeStamp() {
        return freezeTimeStamp;
    }

    public void setFreezeTimeStamp(final int timeStamp) {
        this.freezeTimeStamp = timeStamp;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(final int timeStamp) {
        this.timeStamp = timeStamp;
    }
}
