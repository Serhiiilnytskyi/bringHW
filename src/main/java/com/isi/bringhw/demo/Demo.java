package com.isi.bringhw.demo;

import com.isi.bringhw.AnnotationApplicationContext;
import com.isi.bringhw.NoSuchBeanException;
import com.isi.bringhw.NoUniqueBeanException;

public class Demo {

  public static void main(String[] args) throws NoSuchBeanException, NoUniqueBeanException {
    AnnotationApplicationContext applicationContext = new AnnotationApplicationContext(
        "com.isi.bringhw.demo");

    FirstBean firstBean = applicationContext.getBean(FirstBean.class);
    System.out.println("FirstBean describe: " + firstBean.describe());

    applicationContext.getAllBeans(SuperBean.class).forEach((key, value) -> System.out.println(
        "Bean name: " + key + ", bean class: " + value.getClass().getSimpleName()));

    SuperBean customName2 = applicationContext.getBean("customName2", SuperBean.class);
    SuperBean customName3 = applicationContext.getBean("customName3", SuperBean.class);
    System.out.println("SuperBean customName2: " + customName2.describe());
    System.out.println("SuperBean customName3: " + customName3.describe());
  }
}