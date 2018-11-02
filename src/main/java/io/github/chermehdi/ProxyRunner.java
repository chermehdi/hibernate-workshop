package io.github.chermehdi;

/**
 * Force the sequence (boot, run, destroy) to be called implicitly
 *
 * @author chermehdi
 */
public class ProxyRunner extends Example {

  private final Example runnableExample;

  public ProxyRunner(Example runnableExample) {
    this.runnableExample = runnableExample;
  }

  public void run() {
    if (runnableExample != null) {
      runnableExample.boot();
      runnableExample.run();
      runnableExample.destroy();
    }
  }
}
