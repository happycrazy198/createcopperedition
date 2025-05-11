package mod.happycrazy198.createcopperaddon.register.blocks.flask;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class Flask extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public Flask(Properties properties) {
        super(properties);
        // Set the default block state with FACING = UP
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Place the block facing the side it was clicked on
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
