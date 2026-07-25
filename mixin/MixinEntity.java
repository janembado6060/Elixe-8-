package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnBrightnessEntityEvent;
import elixe.events.OnGetCollisionBorderEvent;
import elixe.events.OnGoingToFallEvent;
import elixe.modules.render.NameProtect;
import java.util.List;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class MixinEntity {
   @Unique
   private OnGoingToFallEvent elixe$goingToFallEvent;

   @Inject(method = "getCustomNameTag", at = @At("RETURN"), cancellable = true)
   private void elixe$protectCustomNameTag(CallbackInfoReturnable<String> cir) {
      if (NameProtect.shouldProtectHolograms()) {
         cir.setReturnValue(NameProtect.filter(cir.getReturnValue()));
      }
   }

   @Inject(method = "getBrightnessForRender", at = @At("RETURN"), cancellable = true)
   private void elixe$onBrightnessForRender(float partialTicks, CallbackInfoReturnable<Integer> cir) {
      OnBrightnessEntityEvent event = new OnBrightnessEntityEvent((Entity)this, cir.getReturnValue());
      Elixe.INSTANCE.EVENT_BUS.post(event);
      cir.setReturnValue(event.getLight());
   }

   @Inject(method = "getCollisionBorderSize", at = @At("HEAD"), cancellable = true)
   private void elixe$onCollisionBorderSize(CallbackInfoReturnable<Float> cir) {
      OnGetCollisionBorderEvent event = new OnGetCollisionBorderEvent(0.1F);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      cir.setReturnValue(event.getBorderSize());
   }

   @Inject(method = "moveEntity", at = @At(value = "FIELD", ordinal = 0, target = "Lnet/minecraft/entity/Entity;posX:D"))
   private void elixe$onGoingToFallPre(double x, double y, double z, CallbackInfo ci) {
      if (this instanceof EntityPlayerSP) {
         OnGoingToFallEvent event = new OnGoingToFallEvent(0);
         this.elixe$goingToFallEvent = event;
         Elixe.INSTANCE.EVENT_BUS.post(event);
      } else {
         this.elixe$goingToFallEvent = null;
      }
   }

   @Redirect(method = "moveEntity", at = @At(value = "FIELD", ordinal = 0, target = "Lnet/minecraft/entity/Entity;onGround:Z"))
   private boolean elixe$safeWalkOnGround(Entity self) {
      OnGoingToFallEvent event = this.elixe$goingToFallEvent;
      return event != null && event.shouldBlockFall() ? true : self.field_70122_E;
   }

   @Redirect(method = "moveEntity", at = @At(value = "INVOKE", ordinal = 0, target = "Lnet/minecraft/entity/Entity;isSneaking()Z"))
   private boolean elixe$safeWalkSneaking(Entity self) {
      OnGoingToFallEvent event = this.elixe$goingToFallEvent;
      return event != null && event.shouldBlockFall() ? true : self.func_70093_af();
   }

   @Redirect(method = "moveEntity", at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z"))
   private boolean elixe$safeWalkEdgeEmpty(List<?> list) {
      boolean empty = list.isEmpty();
      OnGoingToFallEvent event = this.elixe$goingToFallEvent;
      if (empty && event != null) {
         event.setWillFall(true);
      }

      return empty;
   }

   @Inject(
      method = "moveEntity",
      at = @At(
         value = "INVOKE",
         ordinal = 3,
         target = "Lnet/minecraft/world/World;getCollidingBoundingBoxes(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/AxisAlignedBB;)Ljava/util/List;"
      )
   )
   private void elixe$onGoingToFallPost(double x, double y, double z, CallbackInfo ci) {
      OnGoingToFallEvent event = this.elixe$goingToFallEvent;
      if (event != null) {
         event.setState(1);
         Elixe.INSTANCE.EVENT_BUS.post(event);
      }
   }
}
