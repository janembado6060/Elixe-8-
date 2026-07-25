package elixe.modules;

import elixe.ui.IElixeButton;

public abstract class AModuleOption {
   private boolean show = true;
   private boolean updateOnChange = false;
   protected String name;
   private IElixeButton bt;

   public boolean shouldShow() {
      return this.show;
   }

   public void setShow(boolean b) {
      this.show = b;
   }

   public boolean shouldUpdate() {
      return this.updateOnChange;
   }

   public void setShouldUpdate(boolean b) {
      this.updateOnChange = b;
   }

   public void valueChanged() {
   }

   public abstract Object getValue();

   public abstract void setValue(Object var1);

   public String getName() {
      return this.name;
   }

   public void setButton(IElixeButton button) {
      this.bt = button;
   }

   public IElixeButton getButton() {
      return this.bt;
   }
}
