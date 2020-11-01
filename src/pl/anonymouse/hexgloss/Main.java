package pl.anonymouse.hexgloss;


import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.event.Listener;
public class Main extends JavaPlugin implements Listener {
	private static File mFile;
	private static FileConfiguration conf;
    private static final Logger log = Logger.getLogger("Minecraft");
    public static BukkitAudiences audiences;
    public static List<Color> colors = new ArrayList<Color>();
    public void onLoad() {
        log.log(Level.SEVERE, "HEX GLOSS COLORS LOADING PLUGIN...");
    }
	 public Plugin getPlugin() {
		 return this;
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
    public void onEnable(){
		colors.clear();
		createConfig();
		for (int r=0; r<100; r++) colors.add(new Color(r*255/100,       255,         0));
		for (int g=100; g>0; g--) colors.add(new Color(      255, g*255/100,         0));
		for (int b=0; b<100; b++) colors.add(new Color(      255,         0, b*255/100));
		for (int r=100; r>0; r--) colors.add(new Color(r*255/100,         0,       255));
		for (int g=0; g<100; g++) colors.add(new Color(        0, g*255/100,       255));
		for (int b=100; b>0; b--) colors.add(new Color(        0,       255, b*255/100));
		                          colors.add(new Color(        0,       255,         0));
		audiences=BukkitAudiences.create(this);
		getServer().getPluginManager().registerEvents(new Events(),this);
		try {
			Thread.sleep(1000);
			//List<String> a=new ArrayList<String>();
			//a.add(conf.getString("alias"));
			Command cmd=getCommand("hexcolor");
			//cmd.setAliases(a);
	          ((PluginCommand) cmd).setExecutor(new Cmd(this));
	        log.log(Level.SEVERE, "HEX GLOSS COLORS LOADED");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static String[] sprawdz(String m) {
		String kod="";
		if(m.contains("#")) {
			String[] s=m.split("#");
			kod+=s[1].charAt(0);
			kod+=s[1].charAt(1);
			kod+=s[1].charAt(2);
			kod+=s[1].charAt(3);
			kod+=s[1].charAt(4);
			kod+=s[1].charAt(5);
			String[] odp = new String[2];
			odp[0]=kod;
			odp[1]="&x&"+s[1].charAt(0)+"&"+s[1].charAt(1)+"&"+s[1].charAt(2)+"&"+s[1].charAt(3)+"&"+s[1].charAt(4)+"&"+s[1].charAt(5);
			return odp;
		}
		return null;
	}
    public static FileConfiguration getConf() {
    	return conf;
    }
	public void createConfig() {
        mFile = new File(getDataFolder(), "config.yml");
        boolean odp=false;
        if (!mFile.exists()) {
        	mFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
            odp=true;
         }
   
        conf= new YamlConfiguration();
       
        try {
        	conf.load(mFile);
        	if(odp) {
        		//conf.set("alias", "hexcol");
        		conf.set("example", "EXAMPLE TEXT FROM CONFIG");
        		conf.set("desc", "SHIFT + CLICK = INSERT CHAT \nSHIFT = REPLACE CHAT");
        		conf.save(mFile);
        	}
        	
        } catch (IOException | InvalidConfigurationException e) {
            ((Throwable) e).printStackTrace();
        }
    }
}
