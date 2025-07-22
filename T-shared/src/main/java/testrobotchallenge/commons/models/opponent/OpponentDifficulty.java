package testrobotchallenge.commons.models.opponent;

public enum OpponentDifficulty {
    EASY,
    MEDIUM,
    HARD;

    public int toInt() {
        return this.ordinal() + 1;
    }
}
