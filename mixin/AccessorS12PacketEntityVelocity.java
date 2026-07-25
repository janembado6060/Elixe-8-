package elixe.mixin;

import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(S12PacketEntityVelocity.class)
public interface AccessorS12PacketEntityVelocity {
   @Accessor("motionX")
   void setMotionX(int var1);

   @Accessor("motionY")
   void setMotionY(int var1);

   @Accessor("motionZ")
   void setMotionZ(int var1);
}
