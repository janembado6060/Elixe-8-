package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnChangeWorldEvent;
import elixe.events.OnKeyEvent;
import elixe.events.OnKeybindActionEvent;
import elixe.events.OnMouseEvent;
import elixe.events.OnRightClickDelayTimerEvent;
import elixe.events.OnTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At$Shift;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
   @Inject(method = "runTick", at = @At("HEAD"))
   private void elixe$onTick(CallbackInfo ci) {
      Elixe.INSTANCE.EVENT_BUS.post(new OnTickEvent());
   }

   @ModifyConstant(method = "rightClickMouse", constant = @Constant(intValue = 4))
   private int elixe$onRightClickDelay(int original) {
      OnRightClickDelayTimerEvent event = new OnRightClickDelayTimerEvent(original);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      return event.getRightClickDelayTimer();
   }

   @Inject(
      method = "runTick",
      at = @At(value = "INVOKE", ordinal = 0, shift = At$Shift.AFTER, target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V")
   )
   private void elixe$onMouse(CallbackInfo ci) {
      int i = Mouse.getEventButton();
      if (i != -101) {
         Elixe.INSTANCE.EVENT_BUS.post(new OnMouseEvent(Mouse.getEventButtonState(), i - 100));
      }
   }

   @Inject(
      method = "runTick",
      at = @At(value = "INVOKE", ordinal = 1, shift = At$Shift.AFTER, target = "Lnet/minecraft/client/settings/KeyBinding;setKeyBindState(IZ)V")
   )
   private void elixe$onKey(CallbackInfo ci) {
      int k = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
      Elixe.INSTANCE.EVENT_BUS.post(new OnKeyEvent(Keyboard.getEventKeyState(), k));
   }

   @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("TAIL"))
   private void elixe$onChangeWorld(CallbackInfo ci) {
      Elixe.INSTANCE.EVENT_BUS.post(new OnChangeWorldEvent());
   }

   @Inject(
      method = "runTick",
      at = @At(value = "FIELD", ordinal = 1, target = "Lnet/minecraft/client/settings/GameSettings;keyBindAttack:Lnet/minecraft/client/settings/KeyBinding;")
   )
   private void elixe$onKeybindAttack(CallbackInfo ci) {
      KeyBinding kb = Minecraft.func_71410_x().field_71474_y.field_74312_F;
      OnKeybindActionEvent event = new OnKeybindActionEvent(((AccessorKeyBinding)kb).getPressTime() != 0, kb.func_151463_i());
      Elixe.INSTANCE.EVENT_BUS.post(event);
   }

   @Inject(
      method = "runTick",
      at = @At(value = "FIELD", ordinal = 2, target = "Lnet/minecraft/client/settings/GameSettings;keyBindUseItem:Lnet/minecraft/client/settings/KeyBinding;")
   )
   private void elixe$onKeybindUse(CallbackInfo ci) {
      KeyBinding kb = Minecraft.func_71410_x().field_71474_y.field_74313_G;
      OnKeybindActionEvent event = new OnKeybindActionEvent(((AccessorKeyBinding)kb).getPressTime() != 0, kb.func_151463_i());
      Elixe.INSTANCE.EVENT_BUS.post(event);
   }
}
