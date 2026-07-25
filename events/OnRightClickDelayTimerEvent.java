package elixe.events;

public class OnRightClickDelayTimerEvent {
   private int rightClickDelayTimer;

   public OnRightClickDelayTimerEvent(int rightClickDelayTimer) {
      this.rightClickDelayTimer = rightClickDelayTimer;
   }

   public int getRightClickDelayTimer() {
      return this.rightClickDelayTimer;
   }

   public void setRightClickDelayTimer(int rightClickDelayTimer) {
      this.rightClickDelayTimer = rightClickDelayTimer;
   }
}
