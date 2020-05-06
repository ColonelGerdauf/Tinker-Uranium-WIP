package gerdauf.tconstruct.tools.traits;

import net.minecraft.entity.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import slimeknights.tconstruct.library.traits.*;
import slimeknights.tconstruct.library.utils.*;

// apply to uranium sets or pieces
public class Irradiation extends AbstractTrait {
	
	private byte dmgcounter = 0; 
	private short effectcounter1 = 0; 
	private short effectcounter2 = 0; 
	
	public static final Irradiation irradiation = new Irradiation();
	
	public Irradiation() {
		super("irradiation", 0xBFFF3F);
	}
	
	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
		
		int curDurability = ToolHelper.getCurrentDurability(tool);		
		int maxDurability = ToolHelper.getMaxDurability(tool);

		double DurabilityRemaining = curDurability / maxDurability;

		// state below 50% total durability, where the uranium initiates a unstable nuclear chain reaction
		if (DurabilityRemaining < 0.5f && DurabilityRemaining >= 0.25f) {
			supercritical(tool);
		}
		// state below 25% total of durability, where the uranium initiates a stable nuclear chain reaction
		else if (DurabilityRemaining < 0.25f) {
			supercritical_ii(tool);
		}
	}	
	private void durabilityDecay(byte dmg, byte sec) {
		short tickTimer = (sec * 20) / dmg;
		
		if (!TagUtil.getTagSafe(tool).getBoolean(ModReinforced.TAG_UNBREAKABLE)
			if (dmgCounter >= tickTimer) {
    		tool.setItemDamage(tool.getItemDamage() + 1);
				dmgCounter = 0;
			}
			else
				dmgCounter++;		    
	} 
	private void hurtUser(boolean wither, byte dmg) {
	}
	private void hurtOther(boolean wither, byte blockProx, byte dmg, byte sec) {
	}
	public void supercritical(ItemStack tool) {
		// inflict the equivalent of Poison I to the users
		hurtUser(false,1);
		// inflict the equivalent of Poison I to people within 0 blocks of proximity to the users for 2s
		hurtOther(false,0,1,2);
		// drop durability by 1 point per 6 seconds
		durabilityDecay(1,6);
	}
	public void supercritical_ii(ItemStack tool) {  
		// inflict the equivalent of Wither II to the users		
		hurtUser(true,2);
		// inflict the equivalent of Poison I to people within 5 blocks of proximity to the users for 2s
		hurtOther(false,5,1,2);
		// inflict the equivalent of Poison II to people within 2 blocks of proximity to the users for 5s
		hurtOther(false,2,1,5);
		// inflict the equivalent of Poison IV to people within 0 blocks of proximity to the users for 25s
		hurtOther(false,0,1,25);
		// drop durability by 1 point per second
		durabilityDecay(1,1);
	}
}
