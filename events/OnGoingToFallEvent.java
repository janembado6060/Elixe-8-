package elixe.events;

public class OnGoingToFallEvent {
   private int state;
   private boolean shouldBlock = false;
   private boolean willFall = false;

   public OnGoingToFallEvent(int state) {
      this.state = state;
   }

   public int getState() {
      return this.state;
   }

   public boolean shouldBlockFall() {
      return this.shouldBlock;
   }

   public void setShouldBlock(boolean shouldBlock) {
      this.shouldBlock = shouldBlock;
   }

   public void setState(int state) {
      this.state = state;
   }

   public boolean isGoingToFall() {
      return this.willFall;
   }

   public void setWillFall(boolean willFall) {
      this.willFall = willFall;
   }
}
