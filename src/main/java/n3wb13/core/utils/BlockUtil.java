package n3wb13.core.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

public final class BlockUtil {

    public static List<Block> getBlockAllRelative(Block block) {
        List<Block> blocks = new ArrayList<>();
        for (BlockFace face : BlockFace.values()) blocks.add(block.getRelative(face));
        return blocks;
    }

    public static List<Block> getBlockAllRelative(Block block, Material checkMaterial) {
        List<Block> blocks = new ArrayList<>();
        for (BlockFace face : BlockFace.values())
            if (block.getRelative(face).getType() == checkMaterial) blocks.add(block.getRelative(face));
        return blocks;
    }
}