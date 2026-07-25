package elixe.events;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public class OnRenderLayersEvent extends Cancellable {
   RendererLivingEntity rendererLivingEntity;
   EntityLivingBase entitylivingbaseIn;
   float partialTicks;
   float limbSwing;
   float limbSwingAmount;
   float renderAgeInTicks;
   float renderHeadYaw;
   float renderHeadPitch;
   float scale;

   public OnRenderLayersEvent(
      RendererLivingEntity rendererLivingEntity,
      EntityLivingBase entitylivingbaseIn,
      float partialTicks,
      float limbSwing,
      float limbSwingAmount,
      float renderAgeInTicks,
      float renderHeadYaw,
      float renderHeadPitch,
      float scale
   ) {
      this.rendererLivingEntity = rendererLivingEntity;
      this.entitylivingbaseIn = entitylivingbaseIn;
      this.partialTicks = partialTicks;
      this.limbSwing = limbSwing;
      this.limbSwingAmount = limbSwingAmount;
      this.renderAgeInTicks = renderAgeInTicks;
      this.renderHeadYaw = renderHeadYaw;
      this.renderHeadPitch = renderHeadPitch;
      this.scale = scale;
   }

   public RendererLivingEntity getRendererLivingEntity() {
      return this.rendererLivingEntity;
   }

   public EntityLivingBase getEntitylivingbaseIn() {
      return this.entitylivingbaseIn;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public float getLimbSwing() {
      return this.limbSwing;
   }

   public float getLimbSwingAmount() {
      return this.limbSwingAmount;
   }

   public float getRenderAgeInTicks() {
      return this.renderAgeInTicks;
   }

   public float getRenderHeadYaw() {
      return this.renderHeadYaw;
   }

   public float getRenderHeadPitch() {
      return this.renderHeadPitch;
   }

   public float getScale() {
      return this.scale;
   }
}
