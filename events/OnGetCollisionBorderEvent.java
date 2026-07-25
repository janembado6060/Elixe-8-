package elixe.events;

public class OnGetCollisionBorderEvent {
   private float borderSize;

   public OnGetCollisionBorderEvent(float borderSize) {
      this.borderSize = borderSize;
   }

   public float getBorderSize() {
      return this.borderSize;
   }

   public void setBorderSize(float borderSize) {
      this.borderSize = borderSize;
   }
}
