package elixe.events;

public class OnDrawChatLineEvent {
   private String chatLine;

   public OnDrawChatLineEvent(String chatLine) {
      this.chatLine = chatLine;
   }

   public String getChatLine() {
      return this.chatLine;
   }

   public void setChatLine(String chatLine) {
      this.chatLine = chatLine;
   }
}
