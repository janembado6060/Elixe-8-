package elixe.events;

public class OnRender3DEvent {
   private float tickDelta;

   public OnRender3DEvent(float tickDelta) {
      this.tickDelta = tickDelta;
   }

   public float getTickDelta() {
      return this.tickDelta;
   }
}
