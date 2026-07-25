package elixe.file.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import elixe.Elixe;
import elixe.file.FileConfig;
import elixe.file.FileManager;
import elixe.modules.AModuleOption;
import elixe.modules.Module;
import elixe.modules.option.ModuleArrayMultiple;
import elixe.modules.option.ModuleBoolean;
import elixe.modules.option.ModuleFloat;
import elixe.modules.option.ModuleKey;
import elixe.modules.option.ModuleLabel;
import elixe.modules.option.ModuleString;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map$Entry;

public class ModuleConfig implements FileConfig {
   private File dir;

   public ModuleConfig(File dir) throws IOException {
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
      this.loadFrom(this.dir);
   }

   public void loadFrom(File file) throws IOException {
      this.loadFrom(file, false);
   }

   public void loadFrom(File file, boolean full) throws IOException {
      if (file.exists()) {
         JsonObject jsonObject = (JsonObject)new JsonParser().parse(new BufferedReader(new FileReader(file)));

         for (Map$Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            Module module = Elixe.INSTANCE.MODULE_MANAGER.getModuleByName(entry.getKey());
            if (module != null) {
               JsonObject jsonModule = (JsonObject)entry.getValue();

               for (AModuleOption moduleOpt : module.getOptions()) {
                  if (!(moduleOpt instanceof ModuleLabel) && (full || !(moduleOpt instanceof ModuleKey))) {
                     JsonElement element = jsonModule.get(moduleOpt.getName());
                     if (element != null) {
                        try {
                           if (moduleOpt instanceof ModuleBoolean) {
                              moduleOpt.setValue(element.getAsBoolean());
                           } else if (moduleOpt instanceof ModuleString) {
                              moduleOpt.setValue(element.getAsString());
                           } else if (moduleOpt instanceof ModuleFloat) {
                              moduleOpt.setValue(element.getAsFloat());
                           } else if (!(moduleOpt instanceof ModuleArrayMultiple)) {
                              moduleOpt.setValue(element.getAsInt());
                           } else {
                              JsonArray indexesArray = element.getAsJsonArray();
                              boolean[] selectedIndexes = (boolean[])moduleOpt.getValue();

                              for (int i = 0; i < selectedIndexes.length; i++) {
                                 JsonElement optEle = indexesArray.get(i);
                                 boolean optState = false;
                                 if (optEle != null) {
                                    optState = optEle.getAsBoolean();
                                 }

                                 selectedIndexes[i] = optState;
                              }

                              moduleOpt.setValue(selectedIndexes);
                           }
                        } catch (NumberFormatException var16) {
                        }
                     }
                  }
               }

               if (full && !module.getName().equals("ClickGUI")) {
                  JsonElement enabled = jsonModule.get("__enabled");
                  if (enabled != null && enabled.getAsBoolean() != module.isToggled()) {
                     module.toggle();
                  }
               }
            }
         }
      }
   }

   @Override
   public void saveConfig() throws IOException {
      this.saveTo(this.dir);
   }

   public void saveTo(File file) throws IOException {
      this.saveTo(file, false);
   }

   public void saveTo(File file, boolean full) throws IOException {
      JsonObject jsonObject = new JsonObject();

      for (Module module : Elixe.INSTANCE.MODULE_MANAGER.getModules()) {
         JsonObject jsonMod = new JsonObject();

         for (AModuleOption moduleOpt : module.getOptions()) {
            if (!(moduleOpt instanceof ModuleLabel) && (full || !(moduleOpt instanceof ModuleKey))) {
               if (moduleOpt instanceof ModuleBoolean) {
                  jsonMod.addProperty(moduleOpt.getName(), (Boolean)moduleOpt.getValue());
               } else if (moduleOpt instanceof ModuleString) {
                  jsonMod.addProperty(moduleOpt.getName(), (String)moduleOpt.getValue());
               } else if (!(moduleOpt instanceof ModuleArrayMultiple)) {
                  jsonMod.addProperty(moduleOpt.getName(), (Number)moduleOpt.getValue());
               } else {
                  JsonArray selectedArray = new JsonArray();
                  boolean[] optSelected = (boolean[])moduleOpt.getValue();

                  for (int i = 0; i < optSelected.length; i++) {
                     JsonElement selectedIndexState = new JsonPrimitive(optSelected[i]);
                     selectedArray.add(selectedIndexState);
                  }

                  jsonMod.add(moduleOpt.getName(), selectedArray);
               }
            }
         }

         if (full) {
            jsonMod.addProperty("__enabled", module.isToggled());
         }

         jsonObject.add(module.getName(), jsonMod);
      }

      PrintWriter printWriter = new PrintWriter(new FileWriter(file));
      printWriter.println(FileManager.GSON.toJson(jsonObject));
      printWriter.close();
   }
}
