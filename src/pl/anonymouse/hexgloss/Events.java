package pl.anonymouse.hexgloss;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Events implements Listener
{
		@EventHandler
		public void playerChat(AsyncPlayerChatEvent e){
		    String o = e.getMessage();
		    String f = ChatColor.translateAlternateColorCodes('&', o);
		    e.setMessage(f);
		}
}