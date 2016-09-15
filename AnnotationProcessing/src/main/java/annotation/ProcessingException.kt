package annotation

import javax.lang.model.element.Element

/**
 * Created by Mitya on 12.09.2016.
 */
class ProcessingException(val element: Element, msg: String, vararg args: Any) : Exception(String.format(msg, *args)) {
}