package elixe;

import elixe.file.FileManager;
import elixe.modules.ModuleManager;
import elixe.ui.ElixeTheme;
import elixe.utils.player.PlayerConditionals;
import elixe.utils.player.Rotations;
import elixe.utils.render.ToggleAlertManager;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.minecraft.client.Minecraft;

public class Elixe {
   public static Elixe INSTANCE;
   public final EventBus EVENT_BUS = new EventManager();
   public final ModuleManager MODULE_MANAGER;
   public final FileManager FILE_MANAGER;
   public final Rotations ROTATIONS;
   public final PlayerConditionals CONDITIONALS;
   public Minecraft mc;
   public String build = "8.0";

   public Elixe(Minecraft mc) {
      INSTANCE = this;
      this.mc = mc;
      ElixeTheme.useGold();
      this.ROTATIONS = new Rotations();
      this.CONDITIONALS = new PlayerConditionals();
      this.MODULE_MANAGER = new ModuleManager();
      this.FILE_MANAGER = new FileManager();
      this.initialize();
   }

   private void initialize() {
      this.EVENT_BUS.subscribe(this.CONDITIONALS);
      this.EVENT_BUS.subscribe(this.ROTATIONS);
      this.EVENT_BUS.subscribe(this.MODULE_MANAGER);
      this.EVENT_BUS.subscribe(ToggleAlertManager.INSTANCE);
   }
}
