package elixe.events;

import me.zero.alpine.event.type.Cancellable;

public class OnPlayerAnglesEvent extends Cancellable {
   private float yaw;
   private float pitch;

   public OnPlayerAnglesEvent(float yaw, float pitch) {
      this.yaw = yaw;
      this.pitch = pitch;
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setYaw(float yaw) {
      this.yaw = yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void setPitch(float pitch) {
      this.pitch = pitch;
   }
}
