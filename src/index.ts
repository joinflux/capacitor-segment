import { registerPlugin } from '@capacitor/core';

import type { CapacitorSegmentPlugin } from './definitions';

const CapacitorSegment = registerPlugin<CapacitorSegmentPlugin>(
  'CapacitorSegment',
  {
    web: () => import('./web').then(m => new m.CapacitorSegmentWeb()),
  },
);

export * from './definitions';
export { CapacitorSegment };
