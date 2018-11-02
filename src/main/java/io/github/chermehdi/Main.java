package io.github.chermehdi;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;

/**
 * @author chermehdi
 */
public class Main {

  public static void main(String[] args) throws Exception {
//    new ProxyRunner(new JdbcPersistence()).run();
//    new ProxyRunner(new EmbeddablePrimaryExample()).run();
    String classPath = System.getProperty("java.class.path");
    Path classRoot = null;
    for (String classFile : classPath.split(":")) {
      Path path = Paths.get(classFile);
      if (Files.isDirectory(path)) {
        classRoot = path;
        break;
      }
    }

    List<Path> directory = Files.walk(classRoot)
        .collect(Collectors.toList());
    List<Path> classFilesFiltered = new ArrayList<>();
    for (Path path : directory) {
      if (!Files.isDirectory(path) && path.toString().endsWith(".class")) {
        classFilesFiltered.add(path);
      }
    }
    URL url = classRoot.toUri().toURL();
    URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
    List<Class<?>> classes = new ArrayList<>();
    int rootSize = classRoot.toString().length();
    for (Path path : classFilesFiltered) {

      String className = path.toString().substring(rootSize + 1);

      className = className.substring(0, className.length() - 6).replaceAll("/", ".");
      try {
        Class<?> entityClass = classLoader.loadClass(className);
        if (entityClass.isAnnotationPresent(Entity.class)) {
          classes.add(entityClass);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    classes.forEach(System.out::println);
  }
}
