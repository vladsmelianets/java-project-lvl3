package hexlet.code.schemas;

public final class NumberSchema {

    private boolean requiresValidation;
    private boolean requiresPositiveCheck;
    private boolean requiresRangeCheck;
    private int up;
    private int down;

    public NumberSchema() {
        this.requiresValidation = false;
        this.requiresPositiveCheck = false;
        this.requiresRangeCheck = false;

    }

    public boolean isValid(Object object) {
        if (!requiresValidation) {
            return true;
        }
        if (doesNotMatchReqs(object)) {
            return false;
        }
        if (notPositive(object)) {
            return false;
        }
        if (notInRange(object)) {
            return false;
        }
        return true;
    }

    private boolean doesNotMatchReqs(Object object) {
        return !(object instanceof Number);
    }

    private boolean notPositive(Object object) {
        if (!requiresPositiveCheck) {
            return false;
        }
        return (int) object < 1;
    }

    private boolean notInRange(Object object) {
        if (!requiresRangeCheck) {
            return false;
        }
        return (int) object < down || (int) object > up;
    }

    public NumberSchema positive() {
        requiresPositiveCheck = true;
        return this;
    }

    public NumberSchema range(int from, int to) {
        requiresRangeCheck = true;
        this.up = to;
        this.down = from;
        return this;
    }

    public NumberSchema required() {
        requiresValidation = true;
        return this;
    }
}
