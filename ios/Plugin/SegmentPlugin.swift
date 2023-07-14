import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(SegmentPlugin)
public class SegmentPlugin: CAPPlugin {
    private var initialized = false
    private let implementation = Segment()

    @objc func initialize(_ call: CAPPluginCall) {
        if (initialized == true) {
            call.reject("Segment is already initialized")
            return
        }
        guard let key = call.getString("key") else {
            call.reject("Write key is required to initialize plugin")
            return
        }
        let trackLifecycle = call.getBool("trackLifecycle", false)
        let proxyHost = call.getString("proxyHost", "")
        implementation.initialize(key: key, trackLifecycle: trackLifecycle, proxyHost: proxyHost)
        initialized = true
        call.resolve()
    }
    
    @objc func identify(_ call: CAPPluginCall) {
        if (initialized != true) {
            call.reject("Segment is not initialized")
            return
        }
        guard let userId = call.getString("userId") else {
            call.reject("User ID is required for 'identify' but not supplied")
            return
        }
        
        let traits: Dictionary = call.getObject("traits") ?? [:]
        implementation.identify(userId: userId, traits: traits)
        call.resolve()
    }
    
    @objc func track(_ call: CAPPluginCall) {
        if (initialized != true) {
            call.reject("Segment is not initialized")
            return
        }
        guard let eventName = call.getString("eventName") else {
            call.reject("Event name is not supplied")
            return
        }
        
        let properties: Dictionary = call.getObject("properties") ?? [:]
        let options: Dictionary = call.getObject("options") ?? [:]
        implementation.track(eventName: eventName, properties: properties, options: options)
        call.resolve()
    }

    @objc func page(_ call: CAPPluginCall) {
        if (initialized != true) {
            call.reject("Segment is not initialized")
            return
        }
        guard let pathname = call.getString("pathname") else {
            call.reject("Pathname was not supplied")
            return
        }
        
        implementation.page(pathname: pathname)
        call.resolve()
    }
    
    @objc func reset(_ call: CAPPluginCall) {
        if (initialized != true) {
            call.reject("Segment is not initialized")
            return
        }
        
        implementation.reset()
        call.resolve()
    }
}
