package io.udemy.dougllas.validation;

import io.udemy.dougllas.validation.constraintvalidation.NotEmptyListValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {

    String message() default "A lista nao pode ser vazia.";

    /* essas duas classes sao metodos default para rodar uma annotation, ver v.62 */
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
