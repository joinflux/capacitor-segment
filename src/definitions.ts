export interface CapacitorSegmentPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
