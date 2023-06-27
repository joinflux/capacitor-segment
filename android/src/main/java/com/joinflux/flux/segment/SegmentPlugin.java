package com.joinflux.flux.segment;

import android.content.Context;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.segment.analytics.Analytics;
import com.segment.analytics.Analytics.Builder;

@CapacitorPlugin(name = "Segment")
public class SegmentPlugin extends Plugin {

    private static boolean initialized = false;
    private static final Segment implementation = new Segment();

    @PluginMethod
    public void initialize(PluginCall call) {
        synchronized(implementation) {
            // No-op
            if (initialized) {
                call.resolve();
                return;
            }

            String key = call.getString("key");
            if (key == null) {
                call.reject("Write key is required to initialize plugin");
                return;
            }

            Context context = this.getContext();
            Builder builder = new Analytics.Builder(context, key);
            boolean trackLifecycle = Boolean.TRUE.equals(call.getBoolean("trackLifecycle", false));
            if (trackLifecycle) {
                builder.trackApplicationLifecycleEvents().experimentalUseNewLifecycleMethods(false);
            }

            boolean recordScreenViews = Boolean.TRUE.equals(call.getBoolean("recordScreenViews", false));
            if (recordScreenViews) {
                builder.recordScreenViews();
            }
            initialized = true;
            implementation.analytics = builder.build();
            call.resolve();
        }
    }

    @PluginMethod
    public void identify(PluginCall call) {
        if (!initialized) {
            call.reject("Segment is not initialized");
            return;
        }
        String userId = call.getString("userId");
        if (userId == null) {
            call.reject("User ID is required for 'identify' but not supplied");
            return;
        }
        JSObject traits = call.getObject("traits", new JSObject());

        implementation.identify(userId, traits);
        call.resolve();
    }

    @PluginMethod
    public void track(PluginCall call) {
        if (!initialized) {
            call.reject("Segment is not initialized");
            return;
        }
        String eventName = call.getString("eventName");
        if (eventName == null) {
            call.reject("Event name is not supplied");
            return;
        }
        JSObject properties = call.getObject("properties", new JSObject());
        JSObject options = call.getObject("options", new JSObject());

        implementation.track(eventName, properties, options);
        call.resolve();
    }

    @PluginMethod
    public void page(PluginCall call) {
        if (!initialized) {
            call.reject("Segment is not initialized");
            return;
        }
        String pathname = call.getString("pathname");
        if (pathname == null) {
            call.reject("Pathname was not supplied");
            return;
        }

        implementation.page(pathname);
        call.resolve();
    }

    @PluginMethod
    public void reset(PluginCall call) {
        if (!initialized) {
            call.reject("Segment is not initialized");
            return;
        }
        implementation.reset();
        call.resolve();
    }
}
