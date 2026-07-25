package elixe.events;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.network.Packet;

public class OnPacketReceiveEvent extends Cancellable {
   private Packet packet;

   public OnPacketReceiveEvent(Packet packet) {
      this.packet = packet;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public void setPacket(Packet packet) {
      this.packet = packet;
   }
}
