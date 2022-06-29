package com.joinflux.flux.capacitorsegment;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.segment.analytics.Analytics;
import com.segment.analytics.Analytics.Builder;
import com.segment.analytics.ConnectionFactory;
import com.segment.analytics.Options;
import com.segment.analytics.Properties;
import com.segment.analytics.Traits;

import java.io.IOException;
import java.net.HttpURLConnection;

@CapacitorPlugin(name = "CapacitorSegment")
public class CapacitorSegmentPlugin extends Plugin {
    private boolean initialized = false;
    private Segment implementation = new Segment();

    @PluginMethod
    public void initialize(PluginCall call) {
        if (initialized == true) {
            call.reject("Segment is already initialized");
            return;
        }
        String key = call.getString("key");
        if (key == null) {
            call.reject("Write key is required to initialize plugin");
            return;
        }

        Context context = this.getContext();

        Builder builder;
        String proxyHost = call.getString("proxyHost");

        if (proxyHost == null) {
            builder = new Analytics.Builder(context, key);
        } else {
            builder = new Analytics.Builder(context, key).connectionFactory(new ConnectionFactory() {
                @Override
                protected HttpURLConnection openConnection(String url) throws IOException {
                    String path = Uri.parse(url).getPath();
                    return super.openConnection(proxyHost + path);
                }
            });
        }

        Boolean trackLifecycle = call.getBoolean("trackLifecycle", false);
        if (trackLifecycle) {
            builder.trackApplicationLifecycleEvents().experimentalUseNewLifecycleMethods(false);
        }

        Boolean recordScreenViews = call.getBoolean("recordScreenViews", false);
        if (recordScreenViews) {
            builder.recordScreenViews();
        }
        initialized = true;
        implementation.analytics = builder.build();
        call.resolve();
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
        JSObject traits = call.getObject("traits");

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
        JSObject properties = call.getObject("properties");
        JSObject options = call.getObject("options");

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
