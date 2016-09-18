package org.mitya.kashier.test.validator

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement

import org.mitya.kashier.test.util.apply

@RunWith(JUnit4::class)
class KashierValidatorUnitTest {

    @Test
    fun validator_should_return_has_errors_with_pair_of_element_and_its_kind_on_field_with_table_annotation() {
        val fieldWithTableAnnotation = mock(VariableElement::class.java).apply {
            `when`(it.kind).thenReturn(ElementKind.FIELD)
        }
        val expectedResult = ValidatorResult.HasErrors(fieldWithTableAnnotation to ElementKind.FIELD)

        val validator = KashierValidator()
        val validatorResult = validator.validateTables(hashSetOf(fieldWithTableAnnotation))
        assertEquals("Result should be Has Errors with pair of element and its kind", expectedResult, validatorResult)
    }

    @Test
    fun validator_should_return_has_errors_with_pair_of_element_and_its_kind_on_class_with_column_annotation() {
        val classWithColumnAnnotation = mock(TypeElement::class.java).apply {
            `when`(it.kind).thenReturn(ElementKind.CLASS)
        }

        val expectedResult = ValidatorResult.HasErrors(classWithColumnAnnotation to ElementKind.CLASS)

        val validator = KashierValidator()
        val validatorResult = validator.validateColumns(hashSetOf(classWithColumnAnnotation))
        assertEquals("Result should be has Errors with pair of element and its kind", expectedResult, validatorResult)
    }

    @Test
    fun validator_should_return_no_errors_on_valid_elements() {
        val classWithTableAnnotation = mock(TypeElement::class.java).apply {
            `when`(it.kind).thenReturn(ElementKind.CLASS)
        }

        val fieldWithColumnAnnotation = mock(VariableElement::class.java).apply {
            `when`(it.kind).thenReturn(ElementKind.FIELD)
        }

        val expectedResult = ValidatorResult.NoErrors

        val validator = KashierValidator()
        val validatorTableResult = validator.validateTables(hashSetOf(classWithTableAnnotation))
        assertEquals("Result should be No Errors for valid class element", expectedResult, validatorTableResult)

        val validatorColumnResult = validator.validateColumns(hashSetOf(fieldWithColumnAnnotation))
        assertEquals("Result should be No Errors for valid field element", expectedResult, validatorColumnResult)
    }
}