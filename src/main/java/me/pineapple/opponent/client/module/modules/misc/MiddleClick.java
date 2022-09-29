package me.pineapple.opponent.client.module.modules.misc;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.api.utils.ItemUtil;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;

@ModuleManifest(label = "MiddleClick", category = Module.Category.MISC, listenable = false)
public class MiddleClick extends Module {

    private final Value<Boolean> friend = new Value<>("Friend", true);
    private final Value<Boolean> pearl = new Value<>("Pearl", true);

    public static MiddleClick INSTANCE;

    {
        INSTANCE = this;
    }

    public void onMouse() {
        boolean pearled = false;
        if (pearl.getValue()) {
            if (doPearl()) {
                pearled = true;
            }
        }
        if (!pearled && mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY && friend.getValue()) {
            doFriend(mc.objectMouseOver.entityHit);
        }
    }

    private void doFriend(Entity entity) {
        if (entity instanceof EntityPlayer) {
            if (Opponent.INSTANCE.getFriendManager().isFriend(entity.getName())) {
                Opponent.INSTANCE.getFriendManager().removeFriend(entity.getName());
            } else {
                Opponent.INSTANCE.getFriendManager().addFriend(entity.getName());
            }
        }
    }

    private boolean doPearl() {
        if (mc.player.getHeldItemMainhand().getItem() != Items.ENDER_PEARL) {
            final int slot = ItemUtil.getItemFromHotbar(Items.ENDER_PEARL);
            final int lastSlot = mc.player.inventory.currentItem;
            if (slot == -1)
                return false;

            mc.player.inventory.currentItem = slot;
            mc.playerController.updateController();
            mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
            mc.player.inventory.currentItem = lastSlot;
            mc.playerController.updateController();
            return true;
        }
        return false;
    }

}
