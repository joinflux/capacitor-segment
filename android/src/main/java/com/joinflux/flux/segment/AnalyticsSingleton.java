package com.joinflux.flux.segment;

import android.content.Context;
import com.segment.analytics.Analytics;

public class AnalyticsSingleton {
    private AnalyticsSingleton() { }
    private static Analytics instance = null;

    public static Analytics get() {
        return instance;
    }

    public static void set(Analytics newInstance) {
        if (instance != null) {
            throw new Error("Analytics instance is already set!");
        }
        instance = newInstance;
    }
}
