package elixe.mixin;

import elixe.modules.render.SkinChanger;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {
   @Inject(method = "getLocationSkin", at = @At("HEAD"), cancellable = true)
   private void elixe$replaceLocalSkin(CallbackInfoReturnable<ResourceLocation> cir) {
      ResourceLocation replacement = SkinChanger.getLocalSkin((AbstractClientPlayer)this);
      if (replacement != null) {
         cir.setReturnValue(replacement);
      }
   }

   @Inject(method = "getSkinType", at = @At("HEAD"), cancellable = true)
   private void elixe$replaceLocalSkinType(CallbackInfoReturnable<String> cir) {
      String replacement = SkinChanger.getLocalSkinType((AbstractClientPlayer)this);
      if (replacement != null) {
         cir.setReturnValue(replacement);
      }
   }
}
