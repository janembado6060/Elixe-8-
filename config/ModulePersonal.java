package elixe.file.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import elixe.Elixe;
import elixe.file.FileConfig;
import elixe.file.FileManager;
import elixe.modules.AModuleOption;
import elixe.modules.Module;
import elixe.modules.option.ModuleKey;
import elixe.modules.render.ClickGUI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map$Entry;

public class ModulePersonal implements FileConfig {
   private File dir;

   public ModulePersonal(File dir) throws IOException {
      this.dir = dir;
      this.initialize();
   }

   public void initialize() throws IOException {
      if (!this.dir.exists()) {
         this.dir.createNewFile();
         this.saveConfig();
      } else {
         this.loadConfig();
      }
   }

   @Override
   public void loadConfig() throws IOException {
      JsonObject jsonObject = (JsonObject)new JsonParser().parse(new BufferedReader(new FileReader(this.dir)));

      for (Map$Entry<String, JsonElement> entry : jsonObject.entrySet()) {
         Module module = Elixe.INSTANCE.MODULE_MANAGER.getModuleByName(entry.getKey());
         if (module != null) {
            JsonObject jsonModule = (JsonObject)entry.getValue();
            if (!(module instanceof ClickGUI)) {
               JsonElement elementState = jsonModule.get("state");
               if (elementState != null && elementState.getAsBoolean()) {
                  module.toggle();
               }
            }

            for (AModuleOption moduleOpt : module.getOptions()) {
               if (moduleOpt instanceof ModuleKey) {
                  JsonElement element = jsonModule.get(moduleOpt.getName());
                  if (element != null) {
                     moduleOpt.setValue(element.getAsInt());
                  }
               }
            }
         }
      }
   }

   @Override
   public void saveConfig() throws IOException {
      JsonObject jsonObject = new JsonObject();

      for (Module module : Elixe.INSTANCE.MODULE_MANAGER.getModules()) {
         JsonObject jsonMod = new JsonObject();
         jsonMod.addProperty("state", module.isToggled());

         for (AModuleOption moduleOpt : module.getOptions()) {
            if (moduleOpt instanceof ModuleKey) {
               jsonMod.addProperty(moduleOpt.getName(), (Number)moduleOpt.getValue());
            }
         }

         jsonObject.add(module.getName(), jsonMod);
      }

      PrintWriter printWriter = new PrintWriter(new FileWriter(this.dir));
      printWriter.println(FileManager.GSON.toJson(jsonObject));
      printWriter.close();
   }
}
