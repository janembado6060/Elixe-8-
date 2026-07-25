package elixe.events;

import net.minecraft.entity.Entity;

public class OnAttackEntityEvent {
   private Entity attackedEntity;

   public OnAttackEntityEvent(Entity attackedEntity) {
      this.attackedEntity = attackedEntity;
   }

   public Entity getAttackedEntity() {
      return this.attackedEntity;
   }
}
