package hexlet.code.schemas;

import hexlet.code.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class NumberSchemaTest {

    private static final int FIVE = 5;
    private static final int TEN = 10;
    private static final int NEGATIVE = -10;
    private static final int FOUR = 4;

    private NumberSchema schema;

    @BeforeEach
    void setSchema() {
        schema = new Validator().number();
    }

    @Test
    void testValidateNumberWhenNotRequired() {
        Assertions.assertThat(schema.isValid(null)).as("null should be valid when not required").isTrue();
    }

    @Test
    void testValidateNumberWhenRequired() {
        schema.required();
        Assertions.assertThat(schema.isValid(null)).as("null should not be valid when required").isFalse();
        Assertions.assertThat(schema.isValid(TEN)).as("number should be valid when required").isTrue();
        Assertions.assertThat(schema.isValid("5")).as("string should not be valid then required").isFalse();
    }

    @Test
    void testValidateNumberWhenPositive() {
        Assertions.assertThat(schema.positive().isValid(null)).as("null should be valid when positive").isTrue();
        Assertions.assertThat(schema.positive().isValid("5")).as("string should not be valid when positive").isFalse();
        Assertions.assertThat(schema.positive().isValid(TEN))
                .as("positive number should be valid when positive").isTrue();

        schema.required();
        Assertions.assertThat(schema.positive().isValid(TEN))
                .as("positive number should be valid when required positive").isTrue();
        Assertions.assertThat(schema.isValid(NEGATIVE))
                .as("negative number should not be valid when required positive").isFalse();
    }

    @Test
    void testValidateNumberWhenRange() {
        schema.required().range(FIVE, TEN);
        Assertions.assertThat(schema.isValid(FIVE))
                .as("number in low range should be valid when required range").isTrue();
        Assertions.assertThat(schema.isValid(TEN))
                .as("number in high range should be valid when required range").isTrue();
        Assertions.assertThat(schema.isValid(FOUR))
                .as("number not in range should not be valid when required range").isFalse();
    }

    @Test
    void testValidateNumberWhenRequiredPositiveRange() {
        Assertions.assertThat(schema.required().positive().range(FIVE, TEN).isValid(FIVE))
                .as("number should be valid when pass all checks").isTrue();
        Assertions.assertThat(schema.required().positive().range(FIVE, TEN).isValid(FOUR))
                .as("number should not be valid when don't pass all checks").isFalse();
    }
}
