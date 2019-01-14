package com.terwergreen.vue;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class VueRenderer {
    private Object renderServerFunction;

    public VueRenderer() {
        NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        try {
            CompiledScript compiled = engine.compile(read("static/server-bundle.js"));
            this.renderServerFunction = compiled.eval();
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    public String renderContent() {
        NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        try {
            ScriptContext newContext = new SimpleScriptContext();
            newContext.setBindings(engine.createBindings(), ScriptContext.ENGINE_SCOPE);
            Bindings engineScope = newContext.getBindings(ScriptContext.ENGINE_SCOPE);
            engineScope.put("renderServer", this.renderServerFunction);
            engine.setContext(newContext);
            Object html = engine.invokeFunction("renderServer");
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("failed to render vue component", e);
        }
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
