package elixe.file.config;

import elixe.Elixe;
import elixe.utils.misc.LoggingUtils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigManager {
   private final File dir;

   public ConfigManager(File parent) {
      this.dir = new File(parent, "configs");
      if (!this.dir.exists()) {
         this.dir.mkdirs();
      }
   }

   public List<String> list() {
      List<String> names = new ArrayList<>();
      File[] files = this.dir.listFiles();
      if (files != null) {
         for (File f : files) {
            String n = f.getName();
            if (f.isFile() && n.toLowerCase().endsWith(".json")) {
               names.add(n.substring(0, n.length() - 5));
            }
         }
      }

      Collections.sort(names);
      return names;
   }

   public void save(String name) {
      try {
         Elixe.INSTANCE.FILE_MANAGER.MODULE_CONFIG.saveTo(this.file(name), true);
      } catch (IOException e) {
         LoggingUtils.out("error saving config: " + name);
      }
   }

   public void load(String name) {
      try {
         Elixe.INSTANCE.FILE_MANAGER.MODULE_CONFIG.loadFrom(this.file(name), true);
      } catch (IOException e) {
         LoggingUtils.out("error loading config: " + name);
      }
   }

   public void delete(String name) {
      File f = this.file(name);
      if (f.exists()) {
         f.delete();
      }
   }

   public boolean exists(String name) {
      return this.file(name).exists();
   }

   public void openFolder() {
      try {
         Desktop.getDesktop().open(this.dir);
      } catch (Exception e) {
         LoggingUtils.out("could not open the configs folder");
      }
   }

   private File file(String name) {
      return new File(this.dir, name + ".json");
   }
}
