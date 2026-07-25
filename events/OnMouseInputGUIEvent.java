package elixe.events;

public class OnMouseInputGUIEvent {
   int eventButton;
   boolean eventButtonState;

   public OnMouseInputGUIEvent(int eventButton, boolean eventButtonState) {
      this.eventButton = eventButton;
      this.eventButtonState = eventButtonState;
   }

   public int getEventButton() {
      return this.eventButton;
   }

   public void setEventButton(int eventButton) {
      this.eventButton = eventButton;
   }

   public boolean getEventButtonState() {
      return this.eventButtonState;
   }

   public void setEventButtonState(boolean eventButtonState) {
      this.eventButtonState = eventButtonState;
   }
}
