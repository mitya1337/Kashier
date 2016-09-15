package processor

import annotation.ProcessingException
import annotation.SampleAnnotation
import com.google.auto.service.AutoService
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

/**
 * Created by Mitya on 12.09.2016.
 */

@AutoService(Processor::class)
class SampleAnnotationProcessor() : AbstractProcessor() {

    lateinit var messager: Messager

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        messager = processingEnv.messager
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        for (annotatedElement in roundEnv.getElementsAnnotatedWith(SampleAnnotation::class.java)) {
            if (annotatedElement.kind != ElementKind.CLASS) {
                throw ProcessingException(annotatedElement, "Only classes can be annotated with @%s",
                        SampleAnnotation::class.java.simpleName)
            }
            val typeElement = annotatedElement as TypeElement
            throw ProcessingException(typeElement, "Successfully got type element @%s", typeElement.simpleName)
        }
        return true
    }

    override fun getSupportedSourceVersion(): SourceVersion? = SourceVersion.latestSupported()

    override fun getSupportedAnnotationTypes(): MutableSet<String>? {
        val annotations: MutableSet<String> = HashSet()
        annotations.add(SampleAnnotation::class.java.canonicalName)
        return annotations
    }
}