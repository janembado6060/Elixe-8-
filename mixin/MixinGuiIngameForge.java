package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnDrawTitleEvent;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiIngameForge.class)
public class MixinGuiIngameForge {
   @ModifyArg(
      method = "renderTitle",
      index = 0,
      at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I")
   )
   private String elixe$onDrawTitle(String title) {
      OnDrawTitleEvent event = new OnDrawTitleEvent(title, "");
      Elixe.INSTANCE.EVENT_BUS.post(event);
      return event.getTitle();
   }

   @ModifyArg(
      method = "renderTitle",
      index = 0,
      at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/client/gui/FontRenderer;drawString(Ljava/lang/String;FFIZ)I")
   )
   private String elixe$onDrawSubtitle(String subtitle) {
      OnDrawTitleEvent event = new OnDrawTitleEvent("", subtitle);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      return event.getSubtitle();
   }
}
