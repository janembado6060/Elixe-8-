package elixe.events;

public class OnLivingUpdateEvent {
   private boolean holdingSprinting;

   public OnLivingUpdateEvent(boolean holdingSprinting) {
      this.holdingSprinting = holdingSprinting;
   }

   public boolean isHoldingSprinting() {
      return this.holdingSprinting;
   }

   public void setHoldingSprinting(boolean holdingSprinting) {
      this.holdingSprinting = holdingSprinting;
   }
}
