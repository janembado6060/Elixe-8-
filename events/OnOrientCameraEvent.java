package elixe.events;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.entity.Entity;

public class OnOrientCameraEvent extends Cancellable {
   Entity cameraEntity;
   double x;
   double y;
   double z;
   float partialTicks;

   public OnOrientCameraEvent(Entity cameraEntity, double x, double y, double z, float partialTicks) {
      this.cameraEntity = cameraEntity;
      this.x = x;
      this.y = y;
      this.z = z;
      this.partialTicks = partialTicks;
   }

   public Entity getCameraEntity() {
      return this.cameraEntity;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }
}
