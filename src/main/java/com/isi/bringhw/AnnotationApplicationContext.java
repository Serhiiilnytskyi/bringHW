package com.isi.bringhw;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.reflections.Reflections;

public class AnnotationApplicationContext implements ApplicationContext {

  private Map<String, Object> beanNameObjectMap = new ConcurrentHashMap<>();

  @SneakyThrows
  public AnnotationApplicationContext(String packageName) {
    Reflections reflections = new Reflections(packageName);
    Set<Class<?>> beans = reflections.getTypesAnnotatedWith(Bean.class);
    beans.parallelStream().forEach(this::initBean);
  }

  @SneakyThrows
  private <T> void initBean(Class<T> clazz) {
    Object bean = clazz.getConstructor().newInstance();
    String name = clazz.getAnnotation(Bean.class).value();
    if (name.isBlank()) {
      char[] classNameChars = clazz.getSimpleName().toCharArray();
      classNameChars[0] += 32;
      name = new String(classNameChars);
    }
    beanNameObjectMap.put(name, bean);
  }

  public <T> T getBean(Class<T> beanType) throws NoSuchBeanException, NoUniqueBeanException {
    List<Object> beans = beanNameObjectMap.values().stream()
        .filter(beanType::isInstance)
        .toList();
    if (beans.size() > 1) {
      throw new NoUniqueBeanException();
    }
    if (beans.isEmpty()) {
      throw new NoSuchBeanException();
    }
    return (T) beans.get(0);
  }

  public <T> T getBean(String name, Class<T> beanType) throws NoSuchBeanException {
    return (T) Optional.ofNullable(beanNameObjectMap.get(name))
        .filter(beanType::isInstance)
        .orElseThrow(NoSuchBeanException::new);
  }

  public <T> Map<String, T> getAllBeans(Class<T> beanType) {
    return beanNameObjectMap.entrySet().stream()
        .filter(entry -> beanType.isInstance(entry.getValue()))
        .collect(Collectors.toMap(Entry::getKey, entry -> (T) entry.getValue()));
  }
}
