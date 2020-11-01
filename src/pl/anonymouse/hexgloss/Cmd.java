package pl.anonymouse.hexgloss;


import static net.kyori.adventure.text.event.HoverEvent.showText;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Cmd implements CommandExecutor{
    private final JavaPlugin javaPlugin;
	public Cmd(JavaPlugin javaPlugin) {
		this.javaPlugin = javaPlugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("hexcolor")||cmd.getName().equalsIgnoreCase(Main.getConf().getString("alias"))) {
			List<Color> cc=Main.colors;
			if(cc==null) {
				return false;
			}
			List<List<Color>> colorTable = new ArrayList<List<Color>>();
		    float ss = 100;

		    for (float b = 0; b < 100; b += 100 / 15) {
		      List<Color> row = new ArrayList<Color>();

		      for (float h = 0; h < 359; h += 359 / 32) {
		        row.add(Color.getHSBColor(h / 359f, ss / 100f, b / 100f));
		      }

		      colorTable.add(row);
		    }
		    //sender.sendMessage(prefix);
			 for(int i=0;i<colorTable.size();i+=1){
                     //TextComponent x=TextComponent.empty();
				 	TextComponent x=Component.empty();
                       for(int j=0;j<colorTable.get(i).size();j+=1){
                         
                    	   TextComponent message = Component.text()
                                   .content("\u2588")
                                   .color(TextColor.fromHexString("#"+Integer.toHexString(colorTable.get(i).get(j).getRGB()).substring(2)))
                                   .clickEvent( net.kyori.adventure.text.event.ClickEvent.clickEvent(net.kyori.adventure.text.event.ClickEvent.Action.SUGGEST_COMMAND, ""+((Main.sprawdz("#"+Integer.toHexString(colorTable.get(i).get(j).getRGB()).substring(2)))[1])))
                                   .insertion(((Main.sprawdz("#"+Integer.toHexString(colorTable.get(i).get(j).getRGB()).substring(2)))[1]))
                                   .hoverEvent(
                                           showText(Component.text("\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\n"+Main.getConf().getString("example")+"\n"+Main.getConf().getString("desc")+"\n\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588").color(TextColor.fromHexString("#"+Integer.toHexString(colorTable.get(i).get(j).getRGB()).substring(2))))
                                    )
                                   .build();
                          x = x.append(message);
                         
                       }
                       //Main.audiences.audience(sender).sendMessage(x);
                       Main.audiences.player((Player) sender).sendMessage(x);
              }
		}
			
			
		
		return false;
	}
}