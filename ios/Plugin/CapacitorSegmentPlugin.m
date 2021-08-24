#import <Foundation/Foundation.h>
#import <Capacitor/Capacitor.h>

// Define the plugin using the CAP_PLUGIN Macro, and
// each method the plugin supports using the CAP_PLUGIN_METHOD macro.
CAP_PLUGIN(CapacitorSegmentPlugin, "CapacitorSegment",
       CAP_PLUGIN_METHOD(initialize, CAPPluginReturnPromise);
       CAP_PLUGIN_METHOD(identify, CAPPluginReturnPromise);
       CAP_PLUGIN_METHOD(track, CAPPluginReturnPromise);
       CAP_PLUGIN_METHOD(page, CAPPluginReturnPromise);
       CAP_PLUGIN_METHOD(reset, CAPPluginReturnPromise);
)
