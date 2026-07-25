package elixe.events;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.client.renderer.ItemRenderer;

public class OnRenderItemFirstPersonEvent extends Cancellable {
   private ItemRenderer itemRenderer;
   private float partialTicks;

   public OnRenderItemFirstPersonEvent(ItemRenderer itemRenderer, float partialTicks) {
      this.itemRenderer = itemRenderer;
      this.partialTicks = partialTicks;
   }

   public ItemRenderer getItemRenderer() {
      return this.itemRenderer;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }
}
