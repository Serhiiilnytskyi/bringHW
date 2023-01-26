package com.isi.bringhw;

/**
 * Exception thrown when an {@link ApplicationContext#getBean(Class)} method is called and multiple
 * beans of the same type are found. This exception is a runtime exception.
 */
public class NoUniqueBeanException extends RuntimeException {

}
