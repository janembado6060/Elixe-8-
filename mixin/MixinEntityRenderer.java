package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnBlindnessEvent;
import elixe.events.OnGetMouseOverEvent;
import elixe.events.OnNauseaScaleEvent;
import elixe.events.OnOrientCameraEvent;
import elixe.events.OnPlayerAnglesEvent;
import elixe.events.OnRender2DEvent;
import elixe.events.OnRender3DEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At$Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {
   @Unique
   private boolean elixe$orientCamCancelled;

   @Inject(method = "getMouseOver", at = @At("HEAD"), cancellable = true)
   private void elixe$onGetMouseOver(float partialTicks, CallbackInfo ci) {
      OnGetMouseOverEvent event = new OnGetMouseOverEvent(partialTicks, (EntityRenderer)this);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         ci.cancel();
      }
   }

   @Inject(
      method = "renderWorldPass",
      at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", args = "ldc=hand")
   )
   private void elixe$onRender3D(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
      Elixe.INSTANCE.EVENT_BUS.post(new OnRender3DEvent(partialTicks));
   }

   @Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/client/entity/EntityPlayerSP;setAngles(FF)V"))
   private void elixe$onPlayerAngles(EntityPlayerSP player, float yaw, float pitchTimesI) {
      int i = Minecraft.func_71410_x().field_71474_y.field_74338_d ? -1 : 1;
      float f3 = pitchTimesI / i;
      OnPlayerAnglesEvent event = new OnPlayerAnglesEvent(yaw, f3);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (!event.isCancelled()) {
         player.func_70082_c(event.getYaw(), event.getPitch() * i);
      }
   }

   @Inject(
      method = "setupFog",
      at = @At(
         value = "INVOKE",
         ordinal = 0,
         target = "Lnet/minecraft/entity/EntityLivingBase;getActivePotionEffect(Lnet/minecraft/potion/Potion;)Lnet/minecraft/potion/PotionEffect;"
      )
   )
   private void elixe$onSetupFogBlindness(int startCoords, float partialTicks, CallbackInfo ci) {
      Elixe.INSTANCE.EVENT_BUS.post(new OnBlindnessEvent());
   }

   @ModifyVariable(method = "setupCameraTransform", at = @At("STORE"), ordinal = 1)
   private float elixe$onNauseaScale(float f1) {
      OnNauseaScaleEvent event = new OnNauseaScaleEvent();
      Elixe.INSTANCE.EVENT_BUS.post(event);
      return event.isCancelled() ? 0.0F : f1;
   }

   @Inject(method = "orientCamera", at = @At("HEAD"))
   private void elixe$resetOrientCam(float partialTicks, CallbackInfo ci) {
      this.elixe$orientCamCancelled = false;
   }

   @Inject(method = "orientCamera", at = @At(value = "FIELD", ordinal = 0, target = "Lnet/minecraft/client/renderer/EntityRenderer;thirdPersonDistanceTemp:F"))
   private void elixe$postOrientCamera(float partialTicks, CallbackInfo ci) {
      Entity entity = Minecraft.func_71410_x().func_175606_aa();
      float f = entity.func_70047_e();
      double d0 = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * partialTicks;
      double d1 = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * partialTicks + f;
      double d2 = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * partialTicks;
      OnOrientCameraEvent event = new OnOrientCameraEvent(entity, d0, d1, d2, partialTicks);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      this.elixe$orientCamCancelled = event.isCancelled();
   }

   @Redirect(
      method = "orientCamera",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V"),
      slice = @Slice(
         from = @At(value = "FIELD", ordinal = 0, target = "Lnet/minecraft/client/renderer/EntityRenderer;thirdPersonDistanceTemp:F"),
         to = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ActiveRenderInfo;getBlockAtEntityViewpoint(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;F)Lnet/minecraft/block/Block;"
         )
      )
   )
   private void elixe$thirdPersonTranslate(float x, float y, float z) {
      if (!this.elixe$orientCamCancelled) {
         GlStateManager.func_179109_b(x, y, z);
      }
   }

   @Redirect(
      method = "orientCamera",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;rotate(FFFF)V"),
      slice = @Slice(
         from = @At(value = "FIELD", ordinal = 0, target = "Lnet/minecraft/client/renderer/EntityRenderer;thirdPersonDistanceTemp:F"),
         to = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/ActiveRenderInfo;getBlockAtEntityViewpoint(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;F)Lnet/minecraft/block/Block;"
         )
      )
   )
   private void elixe$thirdPersonRotate(float angle, float x, float y, float z) {
      if (!this.elixe$orientCamCancelled) {
         GlStateManager.func_179114_b(angle, x, y, z);
      }
   }

   @Inject(
      method = "updateCameraAndRender",
      at = @At(value = "INVOKE", shift = At$Shift.AFTER, target = "Lnet/minecraft/client/gui/GuiIngame;renderGameOverlay(F)V")
   )
   private void elixe$onRender2D(float partialTicks, long nanoTime, CallbackInfo ci) {
      GlStateManager.func_179098_w();
      GlStateManager.func_179147_l();
      GlStateManager.func_179097_i();
      GlStateManager.func_179120_a(770, 771, 1, 0);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      Elixe.INSTANCE.EVENT_BUS.post(new OnRender2DEvent(partialTicks));
      GlStateManager.func_179084_k();
      GlStateManager.func_179147_l();
      GlStateManager.func_179090_x();
      GlStateManager.func_179098_w();
      GlStateManager.func_179126_j();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }
}
