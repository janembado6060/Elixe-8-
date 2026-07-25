package elixe.mixin;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ItemRenderer.class)
public interface AccessorItemRenderer {
   @Accessor("itemToRender")
   ItemStack getItemToRender();

   @Accessor("equippedProgress")
   float getEquippedProgress();

   @Accessor("prevEquippedProgress")
   float getPrevEquippedProgress();

   @Invoker("transformFirstPersonItem")
   void invokeTransformFirstPersonItem(float var1, float var2);

   @Invoker("renderItemMap")
   void invokeRenderItemMap(AbstractClientPlayer var1, float var2, float var3, float var4);

   @Invoker("rotateArroundXAndY")
   void invokeRotateArroundXAndY(float var1, float var2);

   @Invoker("setLightMapFromPlayer")
   void invokeSetLightMapFromPlayer(AbstractClientPlayer var1);

   @Invoker("rotateWithPlayerRotations")
   void invokeRotateWithPlayerRotations(EntityPlayerSP var1, float var2);

   @Invoker("performDrinking")
   void invokePerformDrinking(AbstractClientPlayer var1, float var2);

   @Invoker("doBlockTransformations")
   void invokeDoBlockTransformations();

   @Invoker("doBowTransformations")
   void invokeDoBowTransformations(float var1, AbstractClientPlayer var2);

   @Invoker("doItemUsedTransformations")
   void invokeDoItemUsedTransformations(float var1);

   @Invoker("renderPlayerArm")
   void invokeRenderPlayerArm(AbstractClientPlayer var1, float var2, float var3);
}
