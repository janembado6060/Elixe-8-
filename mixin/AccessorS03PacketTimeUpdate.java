package elixe.mixin;

import net.minecraft.network.play.server.S03PacketTimeUpdate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(S03PacketTimeUpdate.class)
public interface AccessorS03PacketTimeUpdate {
   @Accessor("worldTime")
   void setWorldTime(long var1);
}
