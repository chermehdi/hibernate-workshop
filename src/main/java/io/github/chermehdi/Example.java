package io.github.chermehdi;

/**
 * base class acts as a template to our example tests
 *
 * @author chermehdi
 */
public abstract class Example {

  /**
   * do any initialisation of your properties or objects to run your example here
   */
  public void boot() {
  }

  /**
   * Perform work here, this is the method called by the class running your example
   *
   * @see ProxyRunner
   */
  public abstract void run();

  /**
   * do clean up operations here, destroy or release referenced objects
   */
  public void destroy() {
  }
}
