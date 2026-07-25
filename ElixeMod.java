package elixe;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "elixe", name = "Elixe", version = "8.0", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
public class ElixeMod {
   public static final String MODID = "elixe";
   public static final String NAME = "Elixe";
   public static final String VERSION = "8.0";

   @EventHandler
   public void init(FMLInitializationEvent event) {
      new Elixe(Minecraft.func_71410_x());
   }
}
