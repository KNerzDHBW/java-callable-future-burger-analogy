package ThreadsBurger;

import java.util.concurrent.Callable;

public class BurgerMakerCallable implements Callable<Burger> {

  @Override
  public Burger call() throws Exception {
    System.out.println("Kitchen receives order and starts working on burger.");
    // Stuff happens in the kitchen. You do not want to know.
    Thread.sleep(2000);

    System.out.println("Burger ready in kitchen.");
    // This is the moment the pager starts beeping!
    Burger burger = new Burger();

    // Clean-up cooking spot. Hopefully.
    Thread.sleep(1000);
    System.out.println("Kitchen ready for next order.");
    return burger;
  }
}
