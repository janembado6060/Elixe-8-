package elixe.file;

import java.io.IOException;

public interface FileConfig {
   void loadConfig() throws IOException;

   void saveConfig() throws IOException;
}
