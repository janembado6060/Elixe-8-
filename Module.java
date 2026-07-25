package elixe.modules;

import elixe.Elixe;
import elixe.modules.option.ModuleKey;
import elixe.utils.player.PlayerConditionals;
import elixe.utils.render.ToggleAlertManager;
import java.util.ArrayList;
import me.zero.alpine.listener.Listenable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;

public class Module implements Listenable, Comparable<Module> {
   private String name;
   private ModuleCategory category;
   private boolean toggled;
   private int key = 0;
   private ModuleKey keyOption = new ModuleKey(0) {
      @Override
      public void valueChanged() {
         Module.this.key = (Integer)this.getValue();
      }
   };
   protected PlayerConditionals conditionals = Elixe.INSTANCE.CONDITIONALS;
   protected Minecraft mc;
   protected RenderManager renderManager;
   protected RenderItem renderItem;
   protected TextureManager textureManager;
   protected ArrayList<AModuleOption> moduleOptions;

   protected void onEnable() {
      Elixe.INSTANCE.EVENT_BUS.subscribe(this);
   }

   protected void onDisable() {
      Elixe.INSTANCE.EVENT_BUS.unsubscribe(this);
   }

   public Module(String name, ModuleCategory category) {
      this.mc = Elixe.INSTANCE.mc;
      this.renderManager = this.mc.func_175598_ae();
      this.renderItem = this.mc.func_175599_af();
      this.textureManager = this.mc.func_110434_K();
      this.moduleOptions = new ArrayList<>();
      this.name = name;
      this.category = category;
      this.toggled = false;
      this.moduleOptions.add(this.keyOption);
   }

   public Module(String name, ModuleCategory category, int keyMod) {
      this.mc = Elixe.INSTANCE.mc;
      this.renderManager = this.mc.func_175598_ae();
      this.renderItem = this.mc.func_175599_af();
      this.textureManager = this.mc.func_110434_K();
      this.moduleOptions = new ArrayList<>();
      this.name = name;
      this.category = category;
      this.toggled = false;
      this.keyOption.setValue(keyMod);
      this.moduleOptions.add(this.keyOption);
   }

   public void toggle() {
      this.toggled = !this.toggled;
      if (this.toggled) {
         this.onEnable();
      } else {
         this.onDisable();
      }

      ToggleAlertManager.push(this.name, this.toggled);
   }

   public String getName() {
      return this.name;
   }

   public ModuleCategory getCategory() {
      return this.category;
   }

   public ArrayList<AModuleOption> getOptions() {
      return this.moduleOptions;
   }

   public int getKey() {
      return this.key;
   }

   public void setKey(int key) {
      this.keyOption.setValue(key);
   }

   public boolean isToggled() {
      return this.toggled;
   }

   public void updateVisibilityOfOptions(AModuleOption[][] multipleControls, int index) {
      for (int i = 0; i < multipleControls.length; i++) {
         for (int j = 0; j < multipleControls[i].length; j++) {
            if (i == index) {
               multipleControls[i][j].setShow(true);
            } else {
               multipleControls[i][j].setShow(false);
            }
         }
      }
   }

   public void updateVisibilityOfOptions(AModuleOption[] controls, boolean state) {
      for (int i = 0; i < controls.length; i++) {
         controls[i].setShow(state);
      }
   }

   public int compareTo(Module arg0) {
      FontRenderer fontR = this.mc.field_71466_p;
      return fontR.func_78256_a(arg0.getName().toLowerCase()) - fontR.func_78256_a(this.name.toLowerCase());
   }
}
