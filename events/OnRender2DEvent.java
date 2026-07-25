package elixe.events;

public class OnRender2DEvent {
   private float tickDelta;

   public OnRender2DEvent(float tickDelta) {
      this.tickDelta = tickDelta;
   }

   public float getTickDelta() {
      return this.tickDelta;
   }
}
