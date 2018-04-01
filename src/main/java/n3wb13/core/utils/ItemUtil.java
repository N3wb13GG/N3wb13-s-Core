package n3wb13.core.utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {

    public static NBTTagCompound getNTB(ItemStack itemStack) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        return (nmsItem != null && nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
    }

    public static ItemStack setNTB(ItemStack itemStack, NBTTagCompound nbttag) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        nmsItem.setTag(nbttag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }
}
