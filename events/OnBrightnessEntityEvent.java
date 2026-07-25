package elixe.events;

import net.minecraft.entity.Entity;

public class OnBrightnessEntityEvent {
   private int light;
   private Entity ent;

   public OnBrightnessEntityEvent(Entity ent, int light) {
      this.light = light;
      this.ent = ent;
   }

   public int getLight() {
      return this.light;
   }

   public void setLight(int light) {
      this.light = light;
   }

   public Entity getEnt() {
      return this.ent;
   }
}
