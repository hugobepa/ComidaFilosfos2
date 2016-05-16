/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comidafilosfos2;

/**
 *
 * @author hugo
 */
public class ComidaFilosfos2 {

     public static void main(String[] args) throws Exception {
               final Philosopher[] philosophers = new Philosopher[5];
              Object[] chopsticks = new Object[philosophers.length];
              for (int i = 0; i < chopsticks.length; i++) {
                      chopsticks[i] = new Object();
              }
        for (int i = 0; i < philosophers.length; i++) {
              Object left = chopsticks[i];
              Object right = chopsticks[(i + 1) % chopsticks.length];
              if(i==0){
                   philosophers[i] = new Philosopher(right, left);
              }else{
                     philosophers[i] = new Philosopher(left, right);
              }
              Thread t = new Thread(philosophers[i], "Phil " + (i + 1));
              t.start();
     }
  }
}


class Philosopher implements Runnable {
  private final Object left;
  private final Object right;

  public Philosopher(Object left, Object right) {
    this.left = left;
    this.right = right;
  }
  private void ponder() throws InterruptedException {
    Thread.sleep(((int) Math.random() * 10) + 10);
  }
  public void run() {
    try {
      while (true) {
        ponder(); // thinking
        synchronized (left) {
          ponder();
          synchronized (right) {
            ponder(); // eating
          }
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      return;
    }
  }
 }
