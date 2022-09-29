package me.pineapple.opponent.client.module.modules.other;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.api.utils.ItemUtil;
import me.pineapple.opponent.api.utils.TickRate;
import me.pineapple.opponent.client.events.Render2DEvent;
import me.pineapple.opponent.client.events.ResizeEvent;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import me.pineapple.opponent.client.other.ColorHandler;
import me.pineapple.opponent.client.other.ServerManager;
import me.pineapple.opponent.client.other.SpeedManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.*;
import java.util.List;

@ModuleManifest(label = "HUD", category = Module.Category.OTHER, enabled = true)
public class HUD extends Module {

    private final Value<Boolean> watermark = new Value<>("Watermark", true);
    private final Value<Boolean> arrayList = new Value<>("Arraylist", true);
    private final Value<Boolean> armor = new Value<>("Armor", true);
    private final Value<Boolean> renderArmor = new Value<>("RenderArmor", true);
    private final Value<Boolean> coordinates = new Value<>("Coordinates", true);
    private final Value<Boolean> welcomer = new Value<>("Welcomer", true);
    private final Value<Boolean> tps = new Value<>("TPS", true);
    private final Value<Boolean> ping = new Value<>("Ping", true);
    private final Value<Boolean> speed = new Value<>("Speed", true);
    private final Value<Boolean> lag = new Value<>("Lag", true);
    public final Value<Integer> serverNotResponding = new Value<>("ServerNotResponding", Integer.valueOf(2500), Integer.valueOf(500), Integer.valueOf(5000));
    private final Value<Boolean> framesPerSecond = new Value<>("FPS", true);
    private final Value<Boolean> potionEffects = new Value<>("Effects", true);
    private final Value<Boolean> direction = new Value<>("Direction", true);
    public final Value<Boolean> customFont = new Value<>("ChatFont", true);
    public final Value<Boolean> noRect = new Value<>("No Rect", true);
    private final Value<Boolean> watermark2 = new Value<>("Watermark2", true);
    public final Value<Boolean> chatTweaks = new Value<>("ChatTweaks", true);
    private final Value<Integer> watermark2y = new Value<>("Watermark2ylevel", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(600));
    public final Value<Boolean> capes = new Value<>("Capes", true);

    private final Map<Module, Float> extendedAmount = new HashMap<>();

    private List<Module> moduleList = new ArrayList<>();
    private ScaledResolution resolution = new ScaledResolution(mc);

    public static HUD INSTANCE;

    {
        INSTANCE = this;
    }


    public static HUD getInstance() {
        return INSTANCE;
    }

    public void init() {
        moduleList = Opponent.INSTANCE.getModuleManager().getModules();
    }

    @SubscribeEvent
    public void onResize(ResizeEvent event) {
        resolution = new ScaledResolution(mc);
    }

    @SubscribeEvent
    public void onRender(final Render2DEvent event) {
        if (watermark.getValue()) {
            Opponent.INSTANCE.getFontManager().drawString("o\u00A7fpponent \u00a77v1.0.3", 2, 2, ColorHandler.getColorInt());
        }
        final float speedRatio = (144.0F / Minecraft.getDebugFPS());
        if (arrayList.getValue()) {
            int offsetY = -8;
            moduleList.sort(Comparator.comparingDouble(mod -> -Opponent.INSTANCE.getFontManager().getStringWidth(mod.getLabel() + mod.getSuffix())));
            final int size = moduleList.size();
            for (int i = 0; i < size; i++) {
                final Module module = moduleList.get(i);
                extendedAmount.putIfAbsent(module, -3f);
                if (!module.isDrawn())
                    continue;
                if (module.isEnabled() || extendedAmount.get(module) > -3f) {
                    final String fullLabel = new StringBuilder()
                            .append(module.getLabel())
                            .append(module.getSuffix())
                            .toString();
                    final float openingTarget = Opponent.INSTANCE.getFontManager().getStringWidth(fullLabel);
                    final float target = module.isEnabled() ? openingTarget : -3F;
                    float newAmount = extendedAmount.get(module);

                    newAmount += 1.5 * speedRatio * (module.isEnabled() ? 1 : -1);

                    newAmount = module.isEnabled() ? Math.min(target, newAmount) : Math.max(target, newAmount);

                    if (!module.isEnabled() && newAmount < 0) {
                        newAmount = -3F;
                    }
                    if (module.isEnabled() && target - newAmount < 1) {
                        newAmount = target;
                    }

                    float percent = newAmount / openingTarget;
                    extendedAmount.put(module, newAmount);
                    Opponent.INSTANCE.getFontManager().drawString(fullLabel, resolution.getScaledWidth() - extendedAmount.get(module) - 2, offsetY += 12 * percent, ColorHandler.getColorInt());
                }
            }
        }

        if (this.watermark2.getValue().booleanValue()) {
            Opponent.INSTANCE.getFontManager().drawString("opp.su", 2.0f, (float) this.watermark2y.getValue().intValue(), ColorHandler.getColorInt());
        }
        if (this.welcomer.getValue().booleanValue()) {
            String welcomerString = "welcome to \u00A7fopp\u00A77on\u00A78ent\u00A7r" + " (^:";
            Opponent.INSTANCE.getFontManager().drawString(welcomerString, (float) resolution.getScaledWidth() / 2.0f - Opponent.INSTANCE.getFontManager().getStringWidth(welcomerString) / 2.0f, 2.0f, ColorHandler.getColorInt());
        }

        int offset = 10;
        if (potionEffects.getValue()) {
            for (PotionEffect effect : mc.player.getActivePotionEffects()) {
                final Potion potion = effect.getPotion();
                String fullName = I18n.format(effect.getPotion().getName());

                if (effect.getAmplifier() == 1) {
                    fullName = fullName + " " + I18n.format("enchantment.level.2");
                } else if (effect.getAmplifier() == 2) {
                    fullName = fullName + " " + I18n.format("enchantment.level.3");
                } else if (effect.getAmplifier() == 3) {
                    fullName = fullName + " " + I18n.format("enchantment.level.4");
                }
                String s = Potion.getPotionDurationString(effect, 1.0F);
                fullName = fullName + " " + s;
                Opponent.INSTANCE.getFontManager().drawString(fullName, resolution.getScaledWidth() - Opponent.INSTANCE.getFontManager().getStringWidth(fullName) - 2, resolution.getScaledHeight() - offset, potion.getLiquidColor());
                offset += 12;
            }
        }

        if (tps.getValue()) {
            final String tps = "TPS:\u00a7f " + String.format("%.2f", TickRate.TPS);
            Opponent.INSTANCE.getFontManager().drawString(tps, resolution.getScaledWidth() - Opponent.INSTANCE.getFontManager().getStringWidth(tps) - 2, resolution.getScaledHeight() - offset, ColorHandler.getColorInt());
            offset += 12;
        }

        if (speed.getValue()) {
            final String speed = "Speed:\u00a7f " + String.format("%.2f", SpeedManager.getSpeedKMH()) + " bp/s";
            Opponent.INSTANCE.getFontManager().drawString(speed, resolution.getScaledWidth() - Opponent.INSTANCE.getFontManager().getStringWidth(speed) - 2, resolution.getScaledHeight() - offset, ColorHandler.getColorInt());
            offset += 12;
        }

        if (framesPerSecond.getValue()) {
            final String fps = "FPS:\u00a7f " + Minecraft.getDebugFPS();
            Opponent.INSTANCE.getFontManager().drawString(fps, resolution.getScaledWidth() - Opponent.INSTANCE.getFontManager().getStringWidth(fps) - 2, resolution.getScaledHeight() - offset, ColorHandler.getColorInt());
            offset += 12;
        }

        if (this.lag.getValue().booleanValue() && ServerManager.getInstance().isServerNotResponding()) {
            String lagString = "Server hasn't responded in " + String.format("%.2f", Float.valueOf((float) ServerManager.getInstance().getTimer().getTimePassed() / 1000.0f)) + "s";
            Opponent.INSTANCE.getFontManager().drawString(lagString, (float) resolution.getScaledWidth() / 2.0f - (float) Opponent.INSTANCE.getFontManager().getStringWidth(lagString) / 2.0f + 2.0f, this.welcomer.getValue() != false ? 12.0f : 2.0f, ColorHandler.getColorInt());
        }

        if (ping.getValue()) {
            if (mc.getConnection().getPlayerInfo(mc.player.getName()) != null) {
                final String ping = "Ping:\u00a7f " + mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime();
                Opponent.INSTANCE.getFontManager().drawString(ping, resolution.getScaledWidth() - Opponent.INSTANCE.getFontManager().getStringWidth(ping) - 2, resolution.getScaledHeight() - offset, ColorHandler.getColorInt());
            }
        }

        boolean openChat = mc.ingameGUI.getChatGUI().getChatOpen();

        if (coordinates.getValue()) {
            Opponent.INSTANCE.getFontManager().drawString("\u00a7f" + (int) mc.player.posX + " \u00a77[\u00a77" + (int) getDimensionCoord(mc.player.posX) + "\u00a77]" + "\u00a77,\u00a7f " + (int) mc.player.posY + "\u00a77,\u00a7f " + (int) mc.player.posZ + " \u00a77[\u00a77" + (int) getDimensionCoord(mc.player.posZ) + "\u00a77]", 2, resolution.getScaledHeight() - (openChat ? 22 : 10), ColorHandler.getColorInt());
        }

        if (direction.getValue()) {
            String facing = "";
            switch (mc.getRenderViewEntity().getHorizontalFacing()) {
                case NORTH:
                    facing = "North \u00a77[\u00a7f-Z\u00a77]";
                    break;
                case SOUTH:
                    facing = "South \u00a77[\u00a7f+Z\u00a77]";
                    break;
                case WEST:
                    facing = "West \u00a77[\u00a7f-X\u00a77]";
                    break;
                case EAST:
                    facing = "East \u00a77[\u00a7f+X\u00a77]";
            }
            Opponent.INSTANCE.getFontManager().drawString(facing, 2, resolution.getScaledHeight() - (openChat ? 22 : 12) - (coordinates.getValue() ? 10 : 0), ColorHandler.getColorInt());
        }

        if (this.armor.getValue().booleanValue()) {
            GlStateManager.enableTexture2D();
            int width = resolution.getScaledWidth() >> 1;
            int height = resolution.getScaledHeight() - 55 - (mc.player.isInWater() && mc.playerController.gameIsSurvivalOrAdventure() ? 10 : 0);
            for (int index = 0; index < 4; ++index) {
                ItemStack is = (ItemStack) mc.player.inventory.armorInventory.get(index);
                if (is.isEmpty()) continue;
                int x = width - 90 + (9 - index - 1) * 20 + 2;
                if (this.armor.getValue().booleanValue()) {
                    GlStateManager.enableDepth();
                    mc.getRenderItem().zLevel = 200.0f;
                    mc.getRenderItem().renderItemAndEffectIntoGUI(is, x, height);
                    mc.getRenderItem().renderItemOverlayIntoGUI(mc.fontRenderer, is, x, height, "");
                    mc.getRenderItem().zLevel = 0.0f;
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableLighting();
                    GlStateManager.disableDepth();
                }
                int dmg = (int) ItemUtil.getDamageInPercent(is);
                Opponent.INSTANCE.getFontManager().drawString(dmg + "", (float) (x + 8) - (float) Opponent.INSTANCE.getFontManager().getStringWidth(dmg + "") / 2.0f, height - 8, is.getItem().getRGBDurabilityForDisplay(is));
            }
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    public double getDimensionCoord(double coord) {
        return mc.player.dimension == 0 ? coord / 8 : coord * 8;
    }
}