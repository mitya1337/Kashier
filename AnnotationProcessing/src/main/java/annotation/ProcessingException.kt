package annotation

import javax.lang.model.element.Element

class ProcessingException(val element: Element,
                          val msg: String,
                          vararg args: Any) : Exception(String.format(msg, *args))