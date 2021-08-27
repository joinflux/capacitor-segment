import Foundation
import Analytics

@objc public class Segment: NSObject {
    @objc public func initialize(key: String, trackLifecycle: Bool) {
        let config: SEGAnalyticsConfiguration = SEGAnalyticsConfiguration.init(writeKey: key)
        config.trackApplicationLifecycleEvents = trackLifecycle;
        
        SEGAnalytics.setup(with: config)
        print("CapacitorSegment: initialized")
    }
    
    @objc public func identify(userId: String, traits: Dictionary<String, Any>) {
        SEGAnalytics.shared().identify(userId, traits: traits)
        return
    }

    @objc public func track(eventName: String, properties: Dictionary<String, Any>, options: Dictionary<String, Any>) {
        SEGAnalytics.shared().track(eventName, properties: properties, options: options)
        return
    }

    @objc public func page(pathname: String) {
        SEGAnalytics.shared().screen(pathname)
        return
    }
    
    @objc func reset() {
        SEGAnalytics.shared().reset()
        return
    }
}
