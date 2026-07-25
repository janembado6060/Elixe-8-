package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnMouseInputGUIEvent;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiScreen.class)
public class MixinGuiScreen {
   @Unique
   private OnMouseInputGUIEvent elixe$mouseInputEvent;

   @Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButton()I", remap = false))
   private int elixe$onGuiGetEventButton() {
      OnMouseInputGUIEvent event = new OnMouseInputGUIEvent(Mouse.getEventButton(), Mouse.getEventButtonState());
      Elixe.INSTANCE.EVENT_BUS.post(event);
      this.elixe$mouseInputEvent = event;
      return event.getEventButton();
   }

   @Redirect(method = "handleMouseInput", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Mouse;getEventButtonState()Z", remap = false))
   private boolean elixe$onGuiGetEventButtonState() {
      return this.elixe$mouseInputEvent.getEventButtonState();
   }
}
