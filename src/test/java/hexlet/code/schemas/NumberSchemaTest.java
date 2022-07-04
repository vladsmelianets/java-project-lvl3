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
    private static final int ELEVEN = 11;

    private NumberSchema schema;

    @BeforeEach
    void setSchema() {
        schema = new Validator().number();
    }

    @Test
    void shouldValidateAnythingWhenNotRequired() {
        Assertions.assertThat(schema.isValid(null)).as("null should be valid").isTrue();
    }

    @Test
    void shouldValidateAnyNumberWhenRequired() {
        Assertions.assertThat(schema.required().isValid(null)).as("null should not be valid").isFalse();

        Assertions.assertThat(schema.isValid(TEN)).as("number should be valid").isTrue();
        Assertions.assertThat(schema.isValid("5")).as("string should not be valid").isFalse();
    }

    @Test
    void shouldValidatePositiveNumberWhenRequiredPositive() {
        Assertions.assertThat(schema.required().positive().isValid(TEN)).as("positive number should be valid").isTrue();

        Assertions.assertThat(schema.isValid(NEGATIVE)).as("negative number should not be valid").isFalse();
    }

    @Test
    void shouldValidateNumberInRangeWhenRequiredRange() {
        Assertions.assertThat(schema.required().range(FIVE, TEN).isValid(FIVE)).as("number in range should be valid")
                .isTrue();

        Assertions.assertThat(schema.isValid(TEN)).as("number in range should be valid").isTrue();
        Assertions.assertThat(schema.isValid(FOUR)).as("number not in range should not be valid").isFalse();
        Assertions.assertThat(schema.isValid(ELEVEN)).as("number not in range should not be valid").isFalse();
    }
}
