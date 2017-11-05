package br.org.ufpr.tcc.validator.validacoes;


import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NaoVazioValidator implements ConstraintValidator<NaoVazio, Object> {
	public void initialize(NaoVazio parameters) {
	}

	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		if (value == null)
			return false;
		if (value.getClass().isArray()) {
			return Array.getLength(value) > 0;
		} else if (value instanceof Collection) {
			return ((Collection) value).size() > 0;
		} else if (value instanceof Map) {
			return ((Map) value).size() > 0;
		} else {
			return ((String) value).length() > 0;
		}
	}
}