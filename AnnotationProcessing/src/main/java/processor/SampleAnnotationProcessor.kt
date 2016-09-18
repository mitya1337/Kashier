package processor

import annotation.ProcessingException
import annotation.SampleAnnotation
import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement


@AutoService(Processor::class)
class SampleAnnotationProcessor() : AbstractProcessor() {

    private lateinit var messager: Messager

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        messager = processingEnv.messager
    }

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment): Boolean {
        for (annotatedElement in roundEnv.getElementsAnnotatedWith(SampleAnnotation::class.java)) {
            if (annotatedElement.kind != ElementKind.CLASS) {
                throw ProcessingException(annotatedElement, "Only classes can be annotated with @${SampleAnnotation::class.java.simpleName}")
            }

            val typeElement = annotatedElement as TypeElement
            throw ProcessingException(typeElement, "Successfully got type element ${typeElement.simpleName}")
        }

        return true
    }

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun getSupportedAnnotationTypes(): MutableSet<String> = hashSetOf(SampleAnnotation::class.java.canonicalName)
}