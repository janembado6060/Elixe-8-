package elixe.events;

public class OnDrawTitleEvent {
   private String title;
   private String subtitle;

   public OnDrawTitleEvent(String title, String subtitle) {
      this.title = title;
      this.subtitle = subtitle;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getSubtitle() {
      return this.subtitle;
   }

   public void setSubtitle(String subtitle) {
      this.subtitle = subtitle;
   }
}
