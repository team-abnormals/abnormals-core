package com.minecraftabnormals.abnormals_core.common.items;

import javax.annotation.Nullable;

import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import com.minecraftabnormals.abnormals_core.common.tileentity.AbnormalsSignTileEntity;

import com.minecraftabnormals.abnormals_core.core.util.item.filling.TargetedItemGroupFiller;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AbnormalsSignItem extends WallOrFloorItem {
	private static final TargetedItemGroupFiller FILLER = new TargetedItemGroupFiller(() -> Items.WARPED_SIGN);

	public AbnormalsSignItem(Block floorBlockIn, Block wallBlockIn, Item.Properties propertiesIn) {
		super(floorBlockIn, wallBlockIn, propertiesIn);
	}

	protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
		boolean flag = super.onBlockPlaced(pos, world, player, stack, state);
		if (!world.isRemote && !flag && player != null) {
			NetworkUtil.openSignEditor(player, (AbnormalsSignTileEntity) world.getTileEntity(pos));
		}
		return flag;
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		FILLER.fillItem(this, group, items);
	}
}