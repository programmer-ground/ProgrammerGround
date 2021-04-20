package com.pg.chat.global.validator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValidation, CharSequence> {
	private List<String> acceptedValues;

	@Override
	public void initialize(EnumValidation annotation) {
		acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
			.map(Enum::name)
			.collect(Collectors.toList());
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return acceptedValues.contains(value.toString());
	}

}
