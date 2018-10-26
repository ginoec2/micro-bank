package io.empirebank.jee.transfer.interfaces.dto;

import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestTransferTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;
    private RequestTransfer requestTransfer;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() {
        validatorFactory.close();
    }

    @Before
    public void setUp() {
        requestTransfer = new RequestTransfer();
    }

    @After
    public void tearDown() {
        requestTransfer = null;
    }

    @Test
    public void shouldFailWhenRequestTransferWasNotSet() {

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        //message property is optional
        assertThat(violations.size()).isEqualTo(3);
    }

    @Test
    public void shouldPassWhenRequestTransferPropertiesAreSetCorrectly() {
        requestTransfer.setFromAccountNumber("12345");
        requestTransfer.setToAccountNumber("98765");
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void shouldFailWhenFromAccountNumberSizeIsLessThanFiveOnly() {
        requestTransfer.setFromAccountNumber("1234");
        requestTransfer.setToAccountNumber("98765");
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void shouldFailWhenFromAccountNumberSizeIsGreaterThanTwentyOnly() {
        requestTransfer.setFromAccountNumber("123456789012345678901");
        requestTransfer.setToAccountNumber("98765");
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void shouldFailWhenFromAccountNumberIsNull() {
        requestTransfer.setFromAccountNumber(null);
        requestTransfer.setToAccountNumber("98765");
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void shouldFailWhenToAccountNumberSizeIsLessThanFiveOnly() {
        requestTransfer.setFromAccountNumber("12345");
        requestTransfer.setToAccountNumber("9876");
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void shouldFailWhenToAccountNumberSizeIsGreaterThanTwentyOnly() {
        requestTransfer.setFromAccountNumber("12345");
        requestTransfer.setToAccountNumber("123456789012345678901");
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void shouldFailWhenToAccountNumberIsNull() {
        requestTransfer.setFromAccountNumber("12345");
        requestTransfer.setToAccountNumber(null);
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void shouldFailWhenAmountIsNull() {
        requestTransfer.setFromAccountNumber("12345");
        requestTransfer.setToAccountNumber("98765");
        requestTransfer.setAmount(null);
        requestTransfer.setMessage("transfer message");

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void shouldPassWhenMessageIsNull() {
        requestTransfer.setFromAccountNumber("12345");
        requestTransfer.setToAccountNumber("98765");
        requestTransfer.setAmount(new BigDecimal(100));
        requestTransfer.setMessage(null);

        Set<ConstraintViolation<RequestTransfer>> violations = validator.validate(requestTransfer);

        assertThat(violations.isEmpty()).isTrue();
        assertThat(violations.size()).isEqualTo(0);
    }

}