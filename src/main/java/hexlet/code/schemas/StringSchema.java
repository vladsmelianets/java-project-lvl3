package hexlet.code.schemas;

public final class StringSchema {

    private boolean isRequired;
    private String pattern;
    private int minLength;

    public StringSchema() {
        this.isRequired = false;
        this.pattern = "";
        this.minLength = 0;
    }

    public boolean isValid(String string) {
        if (!isRequired) {
            return true;
        }
        if (doesNotMatchReqs(string)) {
            return false;
        }
        if (doesNotContain(string)) {
            return false;
        }
        if (hasLessLength(string)) {
            return false;
        }
        return true;
    }



    private boolean doesNotMatchReqs(String string) {
        return string == null || string.length() == 0;
    }

    private boolean doesNotContain(String string) {
        if (pattern.length() == 0) {
            return false;
        }
        return !string.contains(pattern);
    }

    private boolean hasLessLength(String string) {
        if (this.minLength == 0) {
            return false;
        }
        return string.length() < this.minLength;
    }

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema contains(String sequence) {
        this.pattern = sequence;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }
}
