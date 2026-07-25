package elixe.mixin;

import java.nio.FloatBuffer;
import java.util.List;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RendererLivingEntity.class)
public interface AccessorRendererLivingEntity {
   @Accessor("brightnessBuffer")
   FloatBuffer getBrightnessBuffer();

   @Accessor("layerRenderers")
   List<LayerRenderer<EntityLivingBase>> getLayerRenderers();

   @Accessor(value = "textureBrightness", remap = true)
   DynamicTexture getTextureBrightness();

   @Invoker("unsetBrightness")
   void invokeUnsetBrightness();

   @Invoker("getColorMultiplier")
   int invokeGetColorMultiplier(EntityLivingBase var1, float var2, float var3);

   @Invoker("setDoRenderBrightness")
   boolean invokeSetDoRenderBrightness(EntityLivingBase var1, float var2);

   @Invoker("renderModel")
   void invokeRenderModel(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7);
}
