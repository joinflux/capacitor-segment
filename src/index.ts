import { registerPlugin } from '@capacitor/core';

import type { SegmentPlugin } from './definitions';

const Segment = registerPlugin<SegmentPlugin>('Segment', {
  web: () => import('./web').then(m => new m.SegmentWeb()),
});

export * from './definitions';
export { Segment };
