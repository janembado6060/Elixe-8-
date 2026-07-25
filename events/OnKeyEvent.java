package elixe.events;

public class OnKeyEvent {
   private boolean state;
   private int key;

   public OnKeyEvent(boolean state, int key) {
      this.state = state;
      this.key = key;
   }

   public boolean isState() {
      return this.state;
   }

   public int getKey() {
      return this.key;
   }
}
