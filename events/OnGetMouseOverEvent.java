package elixe.events;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.client.renderer.EntityRenderer;

public class OnGetMouseOverEvent extends Cancellable {
   private float partialTicks;
   private EntityRenderer entityRenderer;

   public OnGetMouseOverEvent(float partialTicks, EntityRenderer entityRenderer) {
      this.partialTicks = partialTicks;
      this.entityRenderer = entityRenderer;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public EntityRenderer getEntityRenderer() {
      return this.entityRenderer;
   }
}
