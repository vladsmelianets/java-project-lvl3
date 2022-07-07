package hexlet.code.schemas;

import hexlet.code.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class StringSchemaTest {

    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int TEN = 10;

    private StringSchema schema;

    @BeforeEach
    void setSchema() {
        schema = new Validator().string();
    }

    @Test
    void testValidateStringWhenNotRequired() {
        Assertions.assertThat(schema.isValid("")).as("empty string should be valid when not required").isTrue();
        Assertions.assertThat(schema.isValid(null)).as("null should be valid when not required").isTrue();
        Assertions.assertThat(schema.isValid("what does the fox say")).as("string should be valid when not required")
                .isTrue();
        Assertions.assertThat(schema.isValid("")).as("empty string should be valid when not required").isTrue();
    }

    @Test
    void testValidateStringWhenRequired() {
        schema.required();
        Assertions.assertThat(schema.isValid("what does the fox say"))
                .as("string should be valid when required").isTrue();
        Assertions.assertThat(schema.isValid("")).as("empty string should not be valid when required").isFalse();
        Assertions.assertThat(schema.isValid(null)).as("null should not be valid when required").isFalse();
        Assertions.assertThat(schema.isValid(THREE)).as("int should not be valid when required").isFalse();
    }

    @Test
    void testValidateStringWhenContains() {
        schema.required();
        Assertions.assertThat(schema.contains("what").isValid("what does the fox say"))
                .as("string containing pattern should be valid when contains").isTrue();
        Assertions.assertThat(schema.contains("whatthe").isValid("what does the fox say"))
                .as("string not containing pattern should not be valid when contains").isFalse();
    }

    @Test
    void testValidateStringWhenMinLength() {
        schema.required();
        Assertions.assertThat(schema.required().minLength(FOUR).isValid("four"))
                .as("string with min length should be valid when minLength").isTrue();
        Assertions.assertThat(schema.minLength(THREE).isValid("four"))
                .as("string with greater length should be valid when minLength").isTrue();
        Assertions.assertThat(schema.minLength(TEN).isValid("four"))
                .as("string with less length should not be valid when minLength").isFalse();
    }

    @Test
    void testValidateStringWhenRequiredContainsMinLength() {
        Assertions.assertThat(schema.required().contains("what").minLength(FOUR).isValid("what does the fox say"))
                .as("string should be valid when pass all checks").isTrue();
        Assertions.assertThat(schema.required().contains("whatthe").minLength(FOUR).isValid("what does the fox say"))
                .as("string should not be valid when don't pass all checks").isFalse();
    }
}
