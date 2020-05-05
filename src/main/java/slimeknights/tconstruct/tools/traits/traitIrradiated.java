package landmaster.plustic.traits;

import net.minecraft.entity.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import slimeknights.tconstruct.library.traits.*;
import slimeknights.tconstruct.library.utils.ToolHelper;

private byte dmgcounter = 0; 
private short effectcounter1 = 0; 
private short effectcounter2 = 0; 

// apply to uranium sets or pieces
public class Irradiation extends AbstractTrait {
	public static final Irradiation irradiation = new Irradiation();
	
	public Irradiation() {
		super("irradiation", 0xBFFF3F);
	}
	
	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		
		double curDurability = ToolHelper.getCurrentDurability(tool);		
		double maxDurability = ToolHelper.getMaxDurability(tool);

		double DurabilityRemaining = curDurability / maxDurability;

		// state below 50% total durability, where the uranium initiates a unstable nuclear chain reaction
		if (DurabilityRemaining < 0.5f && DurabilityRemaining >= 0.25f) {
			supercritical();
		}
		// state below 25% total of durability, where the uranium initiates a stable nuclear chain reaction
		else if (DurabilityRemaining < 0.25f) {
			supercritical_ii();
		}
	}
  
	public void supercritical() {
		// inflict the equivalent of Poison I to the wearers of the armor
		if (effectcounter1 >= 25) {
			//super.onPlayerHurt(1);
			effectcounter1 = 0;
		}
		else
			effectcounter1++;
		// inflict the equivalent of Poison I to people within 0 blocks of proximity to the wearers of the armor for 2s
		
		// drop durability by 1 point per 120 ticks		
		if (dmgCounter >= 120) {
			//super.onToolDamage(1);
			dmgCounter = 0;
		}
		else
			dmgCounter++;
	}
	public void supercritical_ii() {  
		// inflict the equivalent of Wither II to the wearers of the armor
		// inflict the equivalent of Poison I to people within 5 blocks of proximity to the wearers of the armor for 2s
		// inflict the equivalent of Poison II to people within 2 blocks of proximity to the wearers of the armor for 5s
		// inflict the equivalent of Poison IV to people within 0 blocks of proximity to the wearers of the armor for 25s
		
		// drop durability by 1 point per 20 ticks
		if (dmgCounter >= 20) {
			//super.onToolDamage(1);
			dmgCounter = 0;
		}
		else
			dmgCounter++;
	}
}
