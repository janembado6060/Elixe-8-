package elixe.mixin;

import com.mojang.authlib.GameProfile;
import elixe.Elixe;
import elixe.events.OnLivingUpdateEvent;
import elixe.events.OnMoveEvent;
import elixe.events.OnPushOutBlocksEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends AbstractClientPlayer {
   @Unique
   private OnLivingUpdateEvent elixe$livingUpdateEvent;

   private MixinEntityPlayerSP(World w, GameProfile g) {
      super(w, g);
   }

   public void func_70091_d(double x, double y, double z) {
      OnMoveEvent event = new OnMoveEvent(x, y, z);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (!event.isCancelled()) {
         super.func_70091_d(event.getX(), event.getY(), event.getZ());
      }
   }

   @Inject(method = "onLivingUpdate()V", at = @At("HEAD"))
   private void elixe$onLivingUpdate(CallbackInfo ci) {
      boolean holdingSprinting = Minecraft.func_71410_x().field_71474_y.field_151444_V.func_151470_d();
      OnLivingUpdateEvent event = new OnLivingUpdateEvent(holdingSprinting);
      this.elixe$livingUpdateEvent = event;
      Elixe.INSTANCE.EVENT_BUS.post(event);
   }

   @Redirect(method = "onLivingUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
   private boolean elixe$sprintIsKeyDown(KeyBinding keyBinding) {
      OnLivingUpdateEvent event = this.elixe$livingUpdateEvent;
      return event != null ? event.isHoldingSprinting() : keyBinding.func_151470_d();
   }

   @Inject(method = "pushOutOfBlocks(DDD)Z", at = @At("HEAD"), cancellable = true)
   private void elixe$onPushOutOfBlocks(double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
      OnPushOutBlocksEvent event = new OnPushOutBlocksEvent();
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         cir.setReturnValue(false);
      }
   }
}
