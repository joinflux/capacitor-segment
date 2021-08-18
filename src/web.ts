import { WebPlugin } from '@capacitor/core';

import type { CapacitorSegmentPlugin } from './definitions';

export class CapacitorSegmentWeb
  extends WebPlugin
  implements CapacitorSegmentPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
