package mcfx;

import mcfx.dengine.basic.BasicMCFXDecoratorEngine;
import mcfx.ui.MComponent;
import truetyper.TrueTypeFont;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class MCFXDecoratorEngine{
    private static final ThreadLocal<MCFXDecoratorEngine> engine = new ThreadLocal<>();
    private static final Map<String, MCFXDecoratorEngine> engines = new HashMap<>();

    static{
        register(BasicMCFXDecoratorEngine.class);
        set("Basic");
    }

    public static void register(Class<? extends MCFXDecoratorEngine> engineClass){
        if(!engineClass.isAnnotationPresent(Named.class)){
            throw new IllegalStateException(engineClass.getSimpleName() + " need @Named above it");
        }

        if(engines.containsKey(engineClass.getAnnotation(Named.class).value())){
            throw new IllegalStateException("Engine \"" + engineClass.getAnnotation(Named.class).value() + "\" already registered");
        }

        try {
            Constructor<?> constructor = engineClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            MCFXDecoratorEngine engine = (MCFXDecoratorEngine) constructor.newInstance();
            engines.put(engine.name(), engine);
            engine.init();
        } catch (Exception e) {
            throw new RuntimeException("Engine classes need a default constructor", e);
        }
    }

    public static void set(String name){
        if(!engines.containsKey(name)) throw new IllegalStateException("Engine \"" + name + "\" not registered");
        engine.set(engines.get(name));
    }

    private final Map<String, Object> properties = new HashMap<>();
    private final Map<String, MCFXDecorator<?>> decorators = new HashMap<>();

    public final MCFXDecoratorEngine addProperty(String name, Object value){
        this.properties.put(name, value);
        return this;
    }

    public final MCFXDecoratorEngine addDecorator(Class<? extends MCFXDecorator<?>> decClass){
        if(!decClass.isAnnotationPresent(Named.class)){
            throw new IllegalStateException("Decorator " + decClass + " needs @Named above it");
        }

        try{
            MCFXDecorator<?> decorator;
            try {
                decorator = (MCFXDecorator<?>) decClass.getDeclaredField("instance").get(null);
            } catch (NoSuchFieldException e) {
                Constructor<?> constructor = decClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                decorator = (MCFXDecorator<?>) constructor.newInstance();
            }

            this.decorators.put(decClass.getAnnotation(Named.class).value(), decorator);
            return this;
        } catch(Exception e){
            throw new RuntimeException("Decorator class needs default constructor or instance field", e);
        }
    }

    public static MCFXDecoratorEngine get(){
        return engine.get();
    }

    public final String name(){
        return this.getClass().getAnnotation(Named.class).value();
    }

    public final String version(){
        return this.getClass().isAnnotationPresent(Versioned.class) ? this.getClass().getAnnotation(Versioned.class).value() : "Unknown";
    }

    public final TrueTypeFont font(){
        return this.getProperty("mcfx.font");
    }

    public final <T> T getProperty(String name){
        return (T) this.properties.get(name);
    }

    public final <T> T getProperty(String name, T def){
        return this.properties.containsKey(name) ? (T) this.properties.get(name) : def;
    }

    public final <T extends MComponent> MCFXDecorator<T> getDecorator(String name, Class<? extends T> tClasss){
        return (MCFXDecorator<T>) this.decorators.get(name);
    }

    public abstract void init();
}