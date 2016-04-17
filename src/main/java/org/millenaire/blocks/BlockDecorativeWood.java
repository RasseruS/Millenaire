package org.millenaire.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BlockDecorativeWood extends Block
{
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDecorativeWood.EnumType.class);
	
	public BlockDecorativeWood() 
	{
		super(Material.wood);
	}
	
	@Override
    public int damageDropped(IBlockState state)
    {
        return ((BlockDecorativeWood.EnumType)state.getValue(VARIANT)).getMetadata();
    }

	public IProperty getVariantProperty()
    {
        return VARIANT;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        if (Block.getBlockFromItem(itemIn) == this)
        {
            BlockDecorativeWood.EnumType[] aenumtype = BlockDecorativeWood.EnumType.values();
            int i = aenumtype.length;

            for (int j = 0; j < i; ++j)
            {
            	BlockDecorativeWood.EnumType enumtype = aenumtype[j];
                list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
            }
        }
    }

    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + BlockDecorativeWood.EnumType.byMetadata(meta).getUnlocalizedName();
    }

	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(VARIANT, BlockDecorativeWood.EnumType.byMetadata(meta));
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
        return ((BlockDecorativeWood.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT});
    }

    //////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public static enum EnumType implements IStringSerializable
    {
        PLAINTIMBERFRAME(0, "plainTimberFrame"),
        CROSSTIMBERFRAME(1, "crossTimberFrame"),
        THATCH(2, "thatch"),
    	SERICULTURE(3, "sericulture");
        
        private static final BlockDecorativeWood.EnumType[] META_LOOKUP = new BlockDecorativeWood.EnumType[values().length];
        private final int meta;
        private final String name;

        private EnumType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockDecorativeWood.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.name;
        }

        static
        {
        	BlockDecorativeWood.EnumType[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2)
            {
            	BlockDecorativeWood.EnumType var3 = var0[var2];
                META_LOOKUP[var3.getMetadata()] = var3;
            }
        }
    }
}
