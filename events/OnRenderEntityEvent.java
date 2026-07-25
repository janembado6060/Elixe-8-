package elixe.events;

import elixe.mixin.AccessorRendererLivingEntity;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public class OnRenderEntityEvent {
   private RendererLivingEntity renderer;
   private EntityLivingBase entity;
   private boolean alreadyRendered = false;
   private float renderLimbSwing;
   private float renderLimbSwingAmount;
   private float renderAgeInTicks;
   private float renderHeadYaw;
   private float renderHeadPitch;
   private float renderScaleFactor;
   private double x;
   private double y;
   private double z;
   private float entityYaw;
   private float partialTicks;
   private int state;

   public boolean didAlreadyRender() {
      return this.alreadyRendered;
   }

   public void setAlreadyRendered(boolean alreadyRendered) {
      this.alreadyRendered = alreadyRendered;
   }

   public RendererLivingEntity getRenderer() {
      return this.renderer;
   }

   public EntityLivingBase getEntity() {
      return this.entity;
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public float getEntityYaw() {
      return this.entityYaw;
   }

   public float getPartialTicks() {
      return this.partialTicks;
   }

   public int getState() {
      return this.state;
   }

   public void setState(int state) {
      this.state = state;
   }

   public void unsetBrightness() {
      ((AccessorRendererLivingEntity)this.renderer).invokeUnsetBrightness();
   }

   public boolean setBrightness() {
      return ((AccessorRendererLivingEntity)this.renderer).invokeSetDoRenderBrightness(this.entity, this.partialTicks);
   }

   public void renderModel() {
      ((AccessorRendererLivingEntity)this.renderer)
         .invokeRenderModel(
            this.entity,
            this.renderLimbSwing,
            this.renderLimbSwingAmount,
            this.renderAgeInTicks,
            this.renderHeadYaw,
            this.renderHeadPitch,
            this.renderScaleFactor
         );
   }

   public OnRenderEntityEvent(RendererLivingEntity renderer, EntityLivingBase entity) {
      this.renderer = renderer;
      this.entity = entity;
   }

   public OnRenderEntityEvent(RendererLivingEntity renderer, EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.renderer = renderer;
      this.entity = entity;
      this.x = x;
      this.y = y;
      this.z = z;
      this.entityYaw = entityYaw;
      this.partialTicks = partialTicks;
   }

   public OnRenderEntityEvent(
      RendererLivingEntity renderer,
      EntityLivingBase entity,
      float renderLimbSwing,
      float renderLimbSwingAmount,
      float renderAgeInTicks,
      float renderHeadYaw,
      float renderHeadPitch,
      float renderScaleFactor,
      float partialTicks
   ) {
      this.renderer = renderer;
      this.entity = entity;
      this.renderLimbSwing = renderLimbSwing;
      this.renderLimbSwingAmount = renderLimbSwingAmount;
      this.renderAgeInTicks = renderAgeInTicks;
      this.renderHeadYaw = renderHeadYaw;
      this.renderHeadPitch = renderHeadPitch;
      this.renderScaleFactor = renderScaleFactor;
      this.partialTicks = partialTicks;
   }

   public boolean isAlreadyRendered() {
      return this.alreadyRendered;
   }

   public float getRenderLimbSwing() {
      return this.renderLimbSwing;
   }

   public float getRenderLimbSwingAmount() {
      return this.renderLimbSwingAmount;
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

   public float getRenderScaleFactor() {
      return this.renderScaleFactor;
   }
}
