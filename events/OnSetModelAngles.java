package elixe.events;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;

public class OnSetModelAngles {
   private float scale;
   private ModelBiped model;
   private Entity entity;

   public OnSetModelAngles(float scale, ModelBiped model, Entity entity) {
      this.scale = scale;
      this.model = model;
      this.entity = entity;
   }

   public float getScale() {
      return this.scale;
   }

   public ModelBiped getModel() {
      return this.model;
   }

   public Entity getEntity() {
      return this.entity;
   }
}
