package elixe.events;

public class OnPlayerMoveStateEvent {
   private boolean forward;
   private boolean back;
   private boolean left;
   private boolean right;
   private boolean jump;
   private boolean sneak;

   public OnPlayerMoveStateEvent(boolean forward, boolean back, boolean left, boolean right, boolean jump, boolean sneak) {
      this.forward = forward;
      this.back = back;
      this.left = left;
      this.right = right;
      this.jump = jump;
      this.sneak = sneak;
   }

   public boolean isForward() {
      return this.forward;
   }

   public void setForward(boolean forward) {
      this.forward = forward;
   }

   public boolean isBack() {
      return this.back;
   }

   public void setBack(boolean back) {
      this.back = back;
   }

   public boolean isLeft() {
      return this.left;
   }

   public void setLeft(boolean left) {
      this.left = left;
   }

   public boolean isRight() {
      return this.right;
   }

   public void setRight(boolean right) {
      this.right = right;
   }

   public boolean isJump() {
      return this.jump;
   }

   public void setJump(boolean jump) {
      this.jump = jump;
   }

   public boolean isSneak() {
      return this.sneak;
   }

   public void setSneak(boolean sneak) {
      this.sneak = sneak;
   }
}
