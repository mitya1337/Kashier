package org.mitya.kashier.test.processor

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.lang.model.SourceVersion

@RunWith(JUnit4::class)
class KashierProcessorUnitTest {

    @Test
    fun processor_supported_source_version_should_be_latest_supported() {
        val processor = KashierProcessor()
        assertEquals("Processor should support latest version", SourceVersion.latestSupported(), processor.supportedSourceVersion)
    }

    @Test
    fun processor_should_support_all_relevant_annotations() {
        val annotationsToSupport = hashSetOf(
                Table::class.java.canonicalName,
                Column::class.java.canonicalName
        )

        val processor = KasierProcessor()
        assertEquals("Processor should support all the annotations", annotationsToSupport, processor.supportedAnnotationTypes)
    }
}