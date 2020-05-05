package gerdauf.tconstruct.tools.traits;

import net.minecraft.entity.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import slimeknights.tconstruct.library.traits.*;
import slimeknights.tconstruct.library.utils.ToolHelper;

// apply to uranium sets or pieces
public class Strong extends AbstractTrait {
	public static final Strong strong = new Strong();
	
	public Strong() {
		super("strong", 0xBFFF3F);
	}
	
	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
    // start with +9 resistance score and -10% base speed from ConArm 
    // add -2 resistance score and +2% base speed per 10% delta of durability
    // absolute minimum resistance score is +1 at 60% total durability
    // absolute maximum base speed is 0 at 50% total durability
	
    // start with +5 damage and +15 mining speed from TiCon
    // add -1 damage score and -3 mining speed score per 10% delta of durability
		}
	}
}
