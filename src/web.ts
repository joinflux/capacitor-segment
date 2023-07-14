import { WebPlugin } from '@capacitor/core';

import type {
  SegmentPlugin,
  IdentifyOptions,
  InitializeOptions,
  PageOptions,
  TrackOptions,
} from './definitions';

const getSegmentScript = (
  key: string,
  proxyHost?: string
) => `!function(){var analytics=window.analytics=window.analytics||[];if(!analytics.initialize)if(analytics.invoked)window.console&&console.error&&console.error("Segment snippet included twice.");else{analytics.invoked=!0;analytics.methods=["trackSubmit","trackClick","trackLink","trackForm","pageview","identify","reset","group","track","ready","alias","debug","page","once","off","on","addSourceMiddleware","addIntegrationMiddleware","setAnonymousId","addDestinationMiddleware"];analytics.factory=function(e){return function(){var t=Array.prototype.slice.call(arguments);t.unshift(e);analytics.push(t);return analytics}};for(var e=0;e<analytics.methods.length;e++){var key=analytics.methods[e];analytics[key]=analytics.factory(key)}analytics.load=function(key,e){var t=document.createElement("script");t.type="text/javascript";t.async=!0;t.src="${proxyHost ? proxyHost + '/analytics.js/v1/' : 'https://cdn.segment.com/analytics.js/v1/'}" + key + "/analytics.min.js";var n=document.getElementsByTagName("script")[0];n.parentNode.insertBefore(t,n);analytics._loadOptions=e};analytics._writeKey="${key}";analytics.SNIPPET_VERSION="4.13.2";
analytics.load("${key}");
analytics.page();
}}();`;

declare global {
  interface Window {
    analytics?: any;
  }
}
export class SegmentWeb extends WebPlugin implements SegmentPlugin {
  async initialize(options: InitializeOptions): Promise<void> {
    await this.loadScript('segment', getSegmentScript(options.key, options.proxyHost));
  }

  async identify(options: IdentifyOptions): Promise<void> {
    if (!window.analytics) return Promise.reject('Segment is not initialized');
    if (!options.userId)
      return Promise.reject(
        "User ID is required for 'identify' but not supplied",
      );
    window.analytics.identify(options.userId, options.traits, options.options);
  }

  async track(options: TrackOptions): Promise<void> {
    if (!window.analytics) return Promise.reject('Segment is not initialized');
    if (!options.eventName) return Promise.reject('Event name is not supplied');
    window.analytics.track(options.eventName, options.properties);
  }

  async page(options: PageOptions): Promise<void> {
    if (!window.analytics) return Promise.reject('Segment is not initialized');
    if (!options.pathname) return Promise.reject('Pathname was not supplied');
    window.analytics.page(options.pathname);
  }

  async reset(): Promise<void> {
    if (!window.analytics) return Promise.reject('Segment is not initialized');
    window.analytics.flush();
  }

  /**
   * Loaded single script with provided id and source
   * @param id - unique identifier of the script
   * @param src - source of the script
   */
  private loadScript(id: string, script: string): Promise<any> {
    return new Promise(resolve => {
      if (document.getElementById(id)) {
        resolve(null);
      } else {
        const node = document.createTextNode(script);
        const file = document.createElement('script');
        file.type = 'text/javascript';
        file.id = id;
        file.onload = resolve;
        file.onerror = console.error;
        file.appendChild(node);
        document.head.appendChild(file);
      }
    });
  }
}
