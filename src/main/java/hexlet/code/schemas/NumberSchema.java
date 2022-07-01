package hexlet.code.schemas;

import hexlet.code.schemas.checks.Check;
import hexlet.code.schemas.checks.NumberRequiredCheck;
import hexlet.code.schemas.checks.PositiveCheck;
import hexlet.code.schemas.checks.RangeCheck;

import java.util.HashMap;
import java.util.Map;

public final class NumberSchema {

    private final Map<String, Check> checks;
    private boolean requiresCheck;

    public NumberSchema() {
        this.checks = new HashMap<>();
        this.requiresCheck = false;
    }

    public boolean isValid(Object object) {
        if (requiresCheck) {
//            checks.values().stream().peek(check -> System.out.println(check.getClass().getSimpleName())).forEach(check -> System.out.println(check.perform(object)));
            return checks.values().stream().allMatch(check -> check.perform(object));
        }
        return true;
    }

    public NumberSchema positive() {
        PositiveCheck check = new PositiveCheck();
        checks.put(check.getClass().getSimpleName(), check);
        return this;
    }

    public NumberSchema range(int from, int to) {
        RangeCheck check = new RangeCheck(from, to);
        checks.put(check.getClass().getSimpleName(), check);
        return this;
    }

    public NumberSchema required() {
        requiresCheck = true;
        NumberRequiredCheck check = new NumberRequiredCheck();
        checks.put(check.getClass().getSimpleName(), check);
        return this;
    }
}
