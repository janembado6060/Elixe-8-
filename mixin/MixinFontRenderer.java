package elixe.mixin;

import elixe.modules.render.NameProtect;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FontRenderer.class)
public class MixinFontRenderer {
   @ModifyVariable(method = "renderStringAtPos", at = @At("HEAD"), argsOnly = true, ordinal = 0)
   private String elixe$filterRenderedText(String text) {
      return NameProtect.filter(text);
   }

   @ModifyVariable(method = "getStringWidth", at = @At("HEAD"), argsOnly = true, ordinal = 0)
   private String elixe$filterMeasuredText(String text) {
      return NameProtect.filter(text);
   }
}
