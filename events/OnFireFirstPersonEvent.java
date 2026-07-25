package elixe.events;

public class OnFireFirstPersonEvent {
   private float height;

   public float getHeight() {
      return this.height;
   }

   public void setHeight(float height) {
      this.height = height;
   }

   public OnFireFirstPersonEvent(float height) {
      this.height = height;
   }
}
