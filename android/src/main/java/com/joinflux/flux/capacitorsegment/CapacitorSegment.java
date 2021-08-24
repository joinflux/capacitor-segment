package com.joinflux.flux.capacitorsegment;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import android.content.Context;
import android.util.Log;
import com.segment.analytics.Analytics;
import com.segment.analytics.Analytics.Builder;
import com.segment.analytics.Options;
import com.segment.analytics.Properties;
import com.segment.analytics.Traits;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CapacitorSegment {
    private static final String PLUGIN_TAG = "CapacitorSegment";
    public Analytics analytics;

    public void identify(String userId, JSObject traits) {
        this.analytics.identify(userId, makeTraitsFromMap(makeMapFromJSON(traits)), null);
    }

    public void track(String eventName, JSObject properties, JSObject options) {
        this.analytics.track(
                eventName,
                makePropertiesFromMap(makeMapFromJSON(properties)),
                makeOptionsFromJSON(options)
        );
    }

    public void page(String pathname) {
        this.analytics.screen(pathname);
    }

    public void reset() {
        this.analytics.reset();
    }

    private Map<String, Object> makeMapFromJSON(JSObject obj) {
        Iterator<String> keys = obj.keys();
        Map<String, Object> map = new HashMap<String, Object>();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                Object value = obj.get(key);
                map.put(key, value);
            } catch (JSONException e) {
                Log.d(PLUGIN_TAG, "could not get value for key " + key);
            }
        }
        return map;
    }

    private Traits makeTraitsFromMap(Map<String, Object> map) {
        Traits traits = new Traits();
        traits.putAll(map);
        return traits;
    }

    private Properties makePropertiesFromMap(Map<String, Object> map) {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }

    private Options makeOptionsFromJSON(JSObject obj) {
        Options options = new Options();
        Iterator<String> keys = obj.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            try {
                boolean enabled = obj.getBool(key);
                options.setIntegration(key, enabled);
            } catch (Exception e) {
                Log.d(PLUGIN_TAG, "could not get boolean for key " + key);
            }
        }
        return options;
    }
}
