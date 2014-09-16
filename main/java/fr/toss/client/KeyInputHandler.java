package fr.toss.client;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.toss.client.gui.GuiCreateGroup;
import fr.toss.client.gui.GuiGroup;
import fr.toss.client.gui.GuiSelectClass;
import fr.toss.client.gui.GuiStats;
import fr.toss.common.Main;
import fr.toss.common.command.ChatColor;
import fr.toss.common.player.ClientPlayerBaseMagic;
import fr.toss.common.player.spells.Spell;
import fr.toss.common.world.bioms.dungeon_arkavon.ThreadGenerator;
import fr.toss.common.world.bioms.dungeon_arkavon.WorldGenArkavon;

public class KeyInputHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
    	ClientPlayerBaseMagic player = Main.getPlayerClient();
    	
        if(KeyBindings.KEY_GENERATE.isPressed())
        {        	
        }
        else if(KeyBindings.KEY_SELECT_CLASSE.isPressed())
        	Minecraft.getMinecraft().displayGuiScreen(new GuiSelectClass());
        else if(KeyBindings.KEY_STATS.isPressed())
        	Minecraft.getMinecraft().displayGuiScreen(new GuiStats());
        else if(KeyBindings.KEY_GROUP.isPressed())
        	if (player.getPlayer().getTeam() != null)
            	Minecraft.getMinecraft().displayGuiScreen(new GuiGroup());
        	else
            	Minecraft.getMinecraft().displayGuiScreen(new GuiCreateGroup());
        else
        {
        	if (player != null && player.getClasse() != null)
        	{
	        	for (int i = 0; i < player.getClasse().CLASSE_SPELL.size(); i++)
	        	{
	        		Spell spell;
	        		
	        		spell = player.getClasse().CLASSE_SPELL.get(i);
	        		if (KeyBindings.KEY_SPELLS[i].isPressed())
	        		{
	        			if (player.getLevel() >= spell.getLevel())
	        				player.getClasse().CLASSE_SPELL.get(i).use();
	        			else
	        				player.getPlayer().addChatComponentMessage(new ChatComponentText(ChatColor.RED + "Level " + spell.getLevel() + " is required to use " + spell.getName() + ChatColor.RESET));
	        			break ;
	        		}
	        	}
        	}
        }
    }

}