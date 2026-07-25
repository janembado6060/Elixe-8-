package elixe.events;

public class OnScoreboardPlayerNameEvent {
   private String line;

   public OnScoreboardPlayerNameEvent(String line) {
      this.line = line;
   }

   public String getLine() {
      return this.line;
   }

   public void setLine(String line) {
      this.line = line;
   }
}
