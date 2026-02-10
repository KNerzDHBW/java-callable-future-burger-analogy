package ThreadsBurger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

  public static void main(String[] args) {
    // Create an executor service with two threads, same syntax as
    // newSingleThreadExecutor but with 2 threads instead of 1
    ExecutorService executor = Executors.newFixedThreadPool(2);

    // Kitchen works on burger via callable submitted to executor
    // Creating a Future<Burger> corresponds to the waiter handing the customer
    // a pager that will beep when the burger is ready.
    Future<Burger> burgerFuture = executor.submit(new BurgerMakerCallable());

    // Customer also submitted to the same executor service as a runnable.
    // As there is no return value from the runnable customerFuture, we can use
    // Future<?> to represent a future that does not return anything.
    // Note that the CustomerRunnable needs to know about the burgerFuture in
    // order to call .get().
    Future<Void> customerFuture =
        executor.submit(new CustomerCallable(burgerFuture));

    try {
      // Wait for customer to finish
      customerFuture.get();
    } catch (Exception e) {
      e.printStackTrace();
    }

    executor.shutdown();
  }
}
