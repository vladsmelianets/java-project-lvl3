package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    protected final Map<String, Predicate<Object>> checks;
    protected boolean requiresCheck;

    protected BaseSchema() {
        this.checks = new HashMap<>();
        this.requiresCheck = false;
    }

    public boolean isValid(Object object) {
        if (requiresCheck) {
            return checks.values().stream().allMatch(check -> check.test(object));
        }
        return true;
    }
}
