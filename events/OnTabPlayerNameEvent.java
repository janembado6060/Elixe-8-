package elixe.events;

import java.util.UUID;

public class OnTabPlayerNameEvent {
   private String name;
   private final UUID uuid;

   public OnTabPlayerNameEvent(String name, UUID uuid) {
      this.name = name;
      this.uuid = uuid;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public UUID getUuid() {
      return this.uuid;
   }
}
