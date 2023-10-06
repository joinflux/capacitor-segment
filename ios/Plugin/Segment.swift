import Foundation
import Segment

@objc public class Segment: NSObject {
    @objc public func initialize(key: String, trackLifecycle: Bool) {
        let config = AnalyticsConfiguration.init(writeKey: key)
        config.trackApplicationLifecycleEvents = trackLifecycle;

        if(!proxyHost.isEmpty) {
            config.requestFactory = { (url: URL) -> NSMutableURLRequest in
                var result = NSMutableURLRequest(url: url)
                if var components = URLComponents(url: url, resolvingAgainstBaseURL: false) {
                    components.host = proxyHost
                    if let transformedURL = components.url {
                        result = NSMutableURLRequest(url: transformedURL)
                    }
                }
                return result
            }
        }
        
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

    @objc public func page(pathname: String) {
        Analytics.shared().screen(pathname)
        return
    }
    
    @objc func reset() {
        Analytics.shared().reset()
        return
    }
}
