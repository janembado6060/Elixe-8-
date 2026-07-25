package elixe.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import elixe.Elixe;
import elixe.file.config.ConfigManager;
import elixe.file.config.ModuleConfig;
import elixe.file.config.ModulePersonal;
import elixe.utils.misc.LoggingUtils;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.Minecraft;

public class FileManager {
   private Minecraft mc;
   private File dir;
   public ModuleConfig MODULE_CONFIG;
   public ModulePersonal MODULE_PERSONAL;
   public ConfigManager CONFIG_MANAGER;
   public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

   public FileManager() {
      this.mc = Elixe.INSTANCE.mc;
      this.dir = new File(this.mc.field_71412_D, "elixe");
      this.initialize();

      try {
         this.MODULE_CONFIG = new ModuleConfig(new File(this.dir, "modules.json"));
         this.MODULE_PERSONAL = new ModulePersonal(new File(this.dir, "modules_personal.json"));
         this.CONFIG_MANAGER = new ConfigManager(this.dir);
      } catch (IOException e) {
         LoggingUtils.out("error trying to initialize module configs");
      }
   }

   public void initialize() {
      if (!this.dir.exists()) {
         this.dir.mkdir();
      }
   }
}
