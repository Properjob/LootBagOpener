package com.properjob.lootbagopener.commands;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;
public class getPlayerInv
        implements ICommand
{
    private List aliases = new ArrayList();

    public getPlayerInv()
    {
        this.aliases.add("getPlayerInv");
    }

    public String getCommandName()
    {
        return "getPlayerInv";
    }

    public String getCommandUsage(ICommandSender p_71518_1_)
    {
        return "/getPlayerInv";
    }

    public List getCommandAliases()
    {
        return this.aliases;
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 2) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Not Enough Arguments"));
            return;
        }
        EntityPlayer player;
        ItemStack item;
        player = sender.getEntityWorld().getPlayerEntityByName(args[0]);
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            item = player.inventory.getStackInSlot(i);
            if (item != null) {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + item.getUnlocalizedName().toString() + " in slot " + i));
                System.out.println(item.getUnlocalizedName() + " in slot " + i);
            }
        }
        if (args[1] != null) player.inventory.setInventorySlotContents(Integer.parseInt(args[1]), null);
    }


    public boolean canCommandSenderUseCommand(ICommandSender p_71519_1_)
    {
        return true;
    }

    public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_)
    {
        return null;
    }

    public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_)
    {
        return false;
    }

    public int compareTo(Object o)
    {
        return 0;
    }
}
