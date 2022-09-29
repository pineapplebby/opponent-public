package me.pineapple.opponent.client.module.manage;

import me.pineapple.opponent.Opponent;
import me.pineapple.opponent.api.property.Value;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.modules.movement.*;
import me.pineapple.opponent.client.module.modules.other.ClickGuiMod;
import me.pineapple.opponent.client.module.modules.other.FontMod;
import me.pineapple.opponent.client.module.modules.other.Colors;
import me.pineapple.opponent.client.module.modules.other.HUD;
import me.pineapple.opponent.client.module.modules.combat.*;
import me.pineapple.opponent.client.module.modules.misc.*;
import me.pineapple.opponent.client.module.modules.player.*;
import me.pineapple.opponent.client.module.modules.visual.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {

    private final List<Module> modules = new ArrayList<>();
    private int size;

    public void init() {
        //CLIENT
        register(new FontMod());
        register(new HUD());
        register(new ClickGuiMod());
        register(new Colors());

        //COMBAT
        register(new AutoArmor());
        register(new AutoTotem());
        register(new Criticals());
        register(new Offhand());
        register(new HoleFiller());
        register(new AutoTrap());

        //MOVEMENT
        register(new Sprint());
        register(new LiquidTweaks());
        register(new FastDrop());
        register(new Speed());
        register(new NoWeb());
        register(new Step());

        //MISC
        register(new AntiPush());
        register(new AutoGG());
        register(new Announcer());
        register(new PearlViewer());
        register(new ChorusViewer());
        register(new ChatTimeStamps());
        register(new MiddleClick());
        register(new PopCounter());
        register(new EntityControl());

        //VISUAL
        register(new Crosshair());
        register(new HoleESP());
        register(new Nametags());
        register(new CustomWeather());
        register(new GlintModifier());
        register(new BlockHighlight());

        //PLAYER
        register(new Velocity());
        register(new FastPlace());
        register(new NoFall());
        register(new FastBreak());
        register(new InstaMine());

        size = modules.size();
        System.out.println(setCapacity(modules));
        System.out.println(modules.size() + " Modules found.");
        FontMod.init();
        HUD.INSTANCE.init();
    }

    //pasted straight from stackoverflow
    //gets the capacity of the list
    private int setCapacity(List<?> l)  {
        try {
            Field dataField = ArrayList.class.getDeclaredField("elementData");
            dataField.setAccessible(true);
            dataField.setInt(modules, modules.size());
            return ((Object[]) dataField.get(l)).length;
        } catch (Exception e) {
            return 0;
        }
    }

    public void register(Module mod) {
        try {
            for (final Field field : mod.getClass().getDeclaredFields()) {
                if (Value.class.isAssignableFrom(field.getType())) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    final Value val = (Value) field.get(mod);
                    mod.register(val);
                }
            }
            modules.add(mod);
        } catch (Exception e) {
            exit();
        }
    }

    public static void exit() {
        Opponent.LOGGER.info("Loading failed... quitting.");
        Runtime.getRuntime().exit(0);
    }

    public int getSize() {
        return size;
    }

    public List<Module> getModules() {
        return modules;
    }

    public List<Module> findByCategory(Module.Category category) {
        return modules
                .stream()
                .filter(mod -> mod.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Module findByLabel(String label) {
        return modules
                .stream()
                .filter(mod -> mod.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElse(null);
    }

}
