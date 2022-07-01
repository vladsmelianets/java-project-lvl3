package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final Map<String, Predicate<Object>> checks;

    protected BaseSchema() {
        this.checks = new HashMap<>();
    }

    protected final Map<String, Predicate<Object>> getChecks() {
        return checks;
    }

    public final boolean isValid(Object object) {
        if (checks.containsKey("required")) {
            return checks.values().stream().allMatch(check -> check.test(object));
        }
        return true;
    }
}
