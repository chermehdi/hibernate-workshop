package io.github.mehdithe;

/**
 * Force the sequence (boot, run, destroy) to be called implicitly
 *
 * @author mehdithe
 */
public class ProxyRunner {

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
