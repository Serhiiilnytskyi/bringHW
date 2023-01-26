package com.isi.bringhw;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used to mark a class as a bean that can be managed by an IoC container. The class will
 * be instantiated and configured by the container, and its lifecycle will be managed. This
 * annotation can be used on any class.
 *
 * @see ApplicationContext
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface Bean {

  /**
   * The name of the bean. If no value is specified, the name of the class is used.
   *
   * @return the name of the bean
   */
  String value() default "";
}