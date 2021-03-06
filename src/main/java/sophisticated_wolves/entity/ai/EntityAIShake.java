package sophisticated_wolves.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import net.minecraft.entity.passive.*;
import sophisticated_wolves.entity.EntitySophisticatedWolf;

/**
 * Sophisticated Wolves
 *
 * @author metroidfood
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityAIShake extends EntityAIBase {
    private EntitySophisticatedWolf theWolf;
    private World theWorld;
    private PathNavigate petPathfinder;

    public EntityAIShake(EntitySophisticatedWolf wolf) {
        this.theWolf = wolf;
        this.theWorld = wolf.worldObj;
        this.petPathfinder = theWolf.getNavigator();
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        if (!this.theWolf.isTamed()) {
            return false;
        }

        if (!this.theWolf.onGround) {
            return false;
        }

        if (this.theWolf.isWolfWet()) {
            if (this.theWorld.isFlammableWithin(this.theWolf.getEntityBoundingBox().addCoord(0.001D, 0.001D, 0.001D))) {
                return false;
            }
            if (this.theWolf.isWet()) {
                return false;
            }
            if (this.theWolf.isBurning()) {
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean continueExecuting() {
        if (this.theWolf.isWolfWet()) {
            if (this.theWorld.isFlammableWithin(this.theWolf.getEntityBoundingBox().addCoord(0.001D, 0.001D, 0.001D))) {
                return false;
            }
            if (this.theWolf.isWet()) {
                return false;
            }
            if (this.theWolf.isBurning()) {
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        this.petPathfinder.clearPathEntity();
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {

    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {

    }

}
