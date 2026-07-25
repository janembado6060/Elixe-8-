package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnFireFirstPersonEvent;
import elixe.events.OnRenderItemFirstPersonEvent;
import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
   @Inject(method = "renderItemInFirstPerson", at = @At("HEAD"), cancellable = true)
   private void elixe$onRenderItemFP(float partialTicks, CallbackInfo ci) {
      OnRenderItemFirstPersonEvent event = new OnRenderItemFirstPersonEvent((ItemRenderer)this, partialTicks);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         ci.cancel();
      }
   }

   @ModifyConstant(method = "renderFireInFirstPerson", constant = @Constant(floatValue = -0.3F))
   private float elixe$onFireFP(float original) {
      OnFireFirstPersonEvent event = new OnFireFirstPersonEvent(original);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      return event.getHeight();
   }
}
