package elixe.events;

import me.zero.alpine.event.type.Cancellable;
import net.minecraft.network.Packet;

public class OnPacketSendEvent extends Cancellable {
   private Packet packet;

   public OnPacketSendEvent(Packet packet) {
      this.packet = packet;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public void setPacket(Packet packet) {
      this.packet = packet;
   }
}
