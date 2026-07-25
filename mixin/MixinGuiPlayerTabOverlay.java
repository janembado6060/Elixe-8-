package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnTabPlayerNameEvent;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GuiPlayerTabOverlay.class)
public class MixinGuiPlayerTabOverlay {
   @Inject(method = "getPlayerName", at = @At("RETURN"), cancellable = true)
   private void elixe$onTabName(NetworkPlayerInfo info, CallbackInfoReturnable<String> cir) {
      OnTabPlayerNameEvent event = new OnTabPlayerNameEvent(cir.getReturnValue(), info.func_178845_a().getId());
      Elixe.INSTANCE.EVENT_BUS.post(event);
      cir.setReturnValue(event.getName());
   }
}
