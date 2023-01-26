package com.isi.bringhw;

/**
 * Exception thrown when an {@link ApplicationContext#getBean(Class)} or
 * {@link ApplicationContext#getBean(String, Class)} method is called and no beans are found.
 */
public class NoSuchBeanException extends RuntimeException {

}
