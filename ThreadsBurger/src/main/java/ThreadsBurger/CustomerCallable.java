package ThreadsBurger;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class CustomerCallable implements Callable<Void> {
  private final Future<Burger> burgerFuture;

  public CustomerCallable(Future<Burger> burgerFuture) {
    this.burgerFuture = burgerFuture;
  }

  private void getDrink() { System.out.println("Customer gets a drink."); }

  private void findTable() { System.out.println("Customer finds a table."); }

  private void eatBurger() { System.out.println("Customer eats burger."); }

  @Override
  public Void call() throws Exception {
    getDrink();
    findTable();
    Thread.sleep(200);
    System.out.println("Customer getting impatient.");
    // As soon as the pager beeps, the customer runs to get the burger.
    // .get() blocks this thread until the burger is ready in the kitchen.
    burgerFuture.get();
    System.out.println("Customer has fetched burger.");
    eatBurger();
    Thread.sleep(1000);
    System.out.println("Meal finished.");
    return null;
  }
}
