package br.org.ufpr.tcc.validator.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NaoNuloValidator implements ConstraintValidator<NaoNulo, Object> {
    public void initialize(NaoNulo parameters) {
    }

    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        return object != null;
    }
}