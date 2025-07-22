package testrobotchallenge.commons.models.opponent;

public enum OpponentType {
    EVOSUITE,
    RANDOOP,
    STUDENT,
    LLM;

    public static OpponentType fromStringIgnoreCase(String name) {
        for (OpponentType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown OpponentType: " + name);
    }
}
