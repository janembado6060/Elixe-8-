package elixe.events;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.entity.Entity;

public class OnRenderNameEvent extends Cancellable {
   private Entity entity;

   public OnRenderNameEvent(Entity entity) {
      this.entity = entity;
   }

   public Entity getEntity() {
      return this.entity;
   }
}
