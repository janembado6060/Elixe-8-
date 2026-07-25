package elixe.mixin;

import elixe.Elixe;
import elixe.events.OnPacketReceiveEvent;
import elixe.events.OnPacketSendEvent;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {
   @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
   private void elixe$onPacketReceive(ChannelHandlerContext ctx, Packet packet, CallbackInfo ci) {
      OnPacketReceiveEvent event = new OnPacketReceiveEvent(packet);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         ci.cancel();
      }
   }

   @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
   private void elixe$onPacketSend(Packet packetIn, CallbackInfo ci) {
      OnPacketSendEvent event = new OnPacketSendEvent(packetIn);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         ci.cancel();
      }
   }

   @Inject(
      method = "sendPacket(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;[Lio/netty/util/concurrent/GenericFutureListener;)V",
      at = @At("HEAD"),
      cancellable = true
   )
   private void elixe$onPacketSendWithListeners(Packet packetIn, GenericFutureListener<?> listener, GenericFutureListener<?>[] listeners, CallbackInfo ci) {
      OnPacketSendEvent event = new OnPacketSendEvent(packetIn);
      Elixe.INSTANCE.EVENT_BUS.post(event);
      if (event.isCancelled()) {
         ci.cancel();
      }
   }
}
