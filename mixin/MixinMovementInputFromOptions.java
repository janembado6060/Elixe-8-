package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnPlayerMoveStateEvent;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MovementInputFromOptions.class)
public abstract class MixinMovementInputFromOptions {
   @Shadow
   @Final
   private GameSettings field_78903_e;

   @Inject(method = "updatePlayerMoveState()V", at = @At("HEAD"), cancellable = true)
   private void elixe$updatePlayerMoveState(CallbackInfo ci) {
      MovementInput mi = (MovementInput)this;
      mi.field_78902_a = 0.0F;
      mi.field_78900_b = 0.0F;
      OnPlayerMoveStateEvent event = new OnPlayerMoveStateEvent(
         this.field_78903_e.field_74351_w.func_151470_d(),
         this.field_78903_e.field_74368_y.func_151470_d(),
         this.field_78903_e.field_74370_x.func_151470_d(),
         this.field_78903_e.field_74366_z.func_151470_d(),
         this.field_78903_e.field_74314_A.func_151470_d(),
         this.field_78903_e.field_74311_E.func_151470_d()
      );
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (event.isForward()) {
         mi.field_78900_b++;
      }

      if (event.isBack()) {
         mi.field_78900_b--;
      }

      if (event.isLeft()) {
         mi.field_78902_a++;
      }

      if (event.isRight()) {
         mi.field_78902_a--;
      }

      mi.field_78901_c = event.isJump();
      mi.field_78899_d = event.isSneak();
      if (mi.field_78899_d) {
         mi.field_78902_a = (float)(mi.field_78902_a * 0.3);
         mi.field_78900_b = (float)(mi.field_78900_b * 0.3);
      }

      ci.cancel();
   }
}
