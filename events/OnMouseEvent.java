package elixe.events;

public class OnMouseEvent {
   private boolean state;
   private int button;

   public OnMouseEvent(boolean state, int button) {
      this.state = state;
      this.button = button;
   }

   public boolean isState() {
      return this.state;
   }

   public int getButton() {
      return this.button;
   }
}
