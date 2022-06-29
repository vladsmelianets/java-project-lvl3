package hexlet.code.schemas;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class StringSchemaTest {

    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int TEN = 10;

    private StringSchema schema;

    @BeforeEach
    void setSchema() {
        schema = new StringSchema();
    }

    @Test
    @DisplayName("validation when not required")
    void shouldBeValidWhenNotRequired() {
        Assertions.assertThat(schema.isValid("")).as("empty string should be valid").isTrue();
        Assertions.assertThat(schema.isValid(null)).as("null should be valid").isTrue();
        Assertions.assertThat(schema.isValid("what does the fox say")).as("string should be valid").isTrue();
    }

    @Test
    @DisplayName("validation when just required")
    void shouldBeValidWhenRequiredAndRequirementsMet() {
        schema.required();

        Assertions.assertThat(schema.isValid("what does the fox say")).as("string should be valid").isTrue();
        Assertions.assertThat(schema.isValid("")).as("empty string should not be valid").isFalse();
        Assertions.assertThat(schema.isValid(null)).as("null should not be valid").isFalse();
    }

    @Test
    @DisplayName("validation when required contains")
    void shouldBeValidWhenRequiredContainsAndRequirementsMet() {
        schema.required();

        Assertions.assertThat(schema.contains("what").isValid("what does the fox say"))
                .as("string containing pattern should be valid").isTrue();
        Assertions.assertThat(schema.contains("whatthe").isValid("what does the fox say"))
                .as("string not containing pattern should not be valid").isFalse();
    }

    @Test
    @DisplayName("validation when required min length")
    void shouldBeValidWhenRequiredMinLengthAndRequirementsMet() {
        schema.required();

        Assertions.assertThat(schema.minLength(FOUR).isValid("four"))
                .as("string with min length should be valid").isTrue();
        Assertions.assertThat(schema.minLength(THREE).isValid("four"))
                .as("string with greater length should be valid").isTrue();
        Assertions.assertThat(schema.minLength(TEN).isValid("four"))
                .as("string with less length should not be valid").isFalse();
    }

}
