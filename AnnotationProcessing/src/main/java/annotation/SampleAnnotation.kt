package annotation

import kotlin.reflect.KClass


/**
 * Created by Mitya on 12.09.2016.
 */
@Target(AnnotationTarget.CLASS) @Retention(AnnotationRetention.RUNTIME) annotation class SampleAnnotation(
        val type: KClass<out Any>
)