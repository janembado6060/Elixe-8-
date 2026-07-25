package elixe.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Minecraft.class)
public interface AccessorMinecraft {
   @Accessor("leftClickCounter")
   void elixe$setLeftClickCounter(int var1);

   @Invoker("clickMouse")
   void elixe$invokeClickMouse();
}
