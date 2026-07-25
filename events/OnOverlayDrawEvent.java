package elixe.events;

public class OnOverlayDrawEvent {
   private boolean skip = false;
   private int overlayType;

   public void skip() {
      this.skip = true;
   }

   public boolean shouldSkip() {
      return this.skip;
   }

   public void setType(int type) {
      this.overlayType = type;
      this.skip = false;
   }

   public int getType() {
      return this.overlayType;
   }

   public OnOverlayDrawEvent(int overlayType) {
      this.overlayType = overlayType;
   }
}
