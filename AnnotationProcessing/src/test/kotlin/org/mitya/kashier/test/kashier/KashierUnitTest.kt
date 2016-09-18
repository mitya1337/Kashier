package org.mitya.kashier.test.kashier

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.annotation.processing.Messager

import org.mitya.kashier.test.util.apply
import org.mockito.Mockito.*
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement

@RunWith(JUnit4::class)
class KashierUnitTest {

    @Test
    fun kashier_should_print_messages_when_validator_returns_errors() {
        val fieldWithTableAnnotation = mock(VariableElement::class.java).apply {
            `when`(it.kind).thenReturn(ElementKind.FIELD)
        }

        val classWithColumnAnnotation = mock(TypeElement::class.java).apply {
            `when`(it.kind).thenReturn(ElementKind.CLASS)
        }

        val mockedMessager = mock(Messager::class.java)

        val validatorReturningErrors = mock(Validator::class.java).apply {
            `when`(it.validateTables).thenReturn(ValidatorResult.HasErrors(fieldWithTableAnnotation to ElementKind.FIELD))
            `when`(it.validateTables).thenReturn(ValidatorResult.HasErrors(classWithColumnAnnotation to ElementKind.CLASS))
        }

        val kashier = Kashier(mockedMessager, validatorReturningErrors)
        kashier.process()
    }
}