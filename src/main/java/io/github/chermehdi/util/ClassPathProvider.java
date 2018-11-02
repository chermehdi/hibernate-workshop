package io.github.chermehdi.util;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.persistence.Entity;

/**
 * @author chermehdi
 */
public final class ClassPathProvider {

  private String targetPackage;

  public ClassPathProvider(String targetPackage) {
    this.targetPackage = targetPackage;
  }

  public List<Class<?>> loadClass(Predicate<Class<?>> clazzPredicate) throws Exception {

    String classPath = System.getProperty("java.class.path");

    Optional<Path> rootClasses = Arrays.stream(classPath.split(":"))
        .map(path -> Paths.get(path))
        .filter(Files::isDirectory)
        .findAny();

    Path path = rootClasses.get();
    int rootLength = path.toString().length();
    URL url = path.toUri().toURL();
    URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
    return Files.walk(path)
        .filter(file -> !Files.isDirectory(file) && file.toString().endsWith(".class"))
        .map(file -> file.toString().substring(rootLength + 1))
        .map(className -> className.substring(0, className.length() - 6))
        .map(className -> className.replaceAll("/", "."))
        .filter(className -> className.startsWith(targetPackage))
        .map(className -> {
          try {
            return classLoader.loadClass(className);
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
          return null;
        })
        .filter(clazzPredicate)
        .collect(Collectors.toList());
  }

  public List<Class<?>> loadClasses() throws Exception {
    return loadClass(e -> e.isAnnotationPresent(Entity.class));
  }
}
