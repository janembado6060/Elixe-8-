package elixe.events;

public class OnKeybindActionEvent {
   private boolean pressed;
   private int key;

   public OnKeybindActionEvent(boolean pressed, int key) {
      this.pressed = pressed;
      this.key = key;
   }

   public boolean isPressed() {
      return this.pressed;
   }

   public int getKey() {
      return this.key;
   }
}
