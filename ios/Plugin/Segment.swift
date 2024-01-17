import Foundation
import Segment

@objc public class Segment: NSObject {
    @objc public func initialize(key: String, trackLifecycle: Bool) {
        let config = AnalyticsConfiguration.init(writeKey: key)
        config.trackApplicationLifecycleEvents = trackLifecycle;
        Analytics.debug(true)
        Analytics.setup(with: config)
        print("CapacitorSegment: initialized")
    }
    
    @objc public func identify(userId: String, traits: Dictionary<String, Any>) {
        Analytics.shared().identify(userId, traits: traits)
        return
    }

    @objc public func track(eventName: String, properties: Dictionary<String, Any>, options: Dictionary<String, Any>) {
        Analytics.shared().track(eventName, properties: properties, options: options)
        return
    }


    @objc public func page(pathname: String, properties: Dictionary<String, Any>) {
        Analytics.shared().screen(pathname, properties: properties)
        return
    }

    @objc func reset() {
        Analytics.shared().reset()
        return
    }
}
