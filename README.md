# capacitor-segment

A Capacitor plugin for Segment analytics

## Install

```bash
npm install capacitor-segment
npx cap sync
```

### Capacitor 3 vs Capacitor 4

- Version 0.1.3 will target the latest Capacitor version
- Version 1.0.0 will target Capacitor 4

## API

<docgen-index>

- [`initialize(...)`](#initialize)
- [`identify(...)`](#identify)
- [`track(...)`](#track)
- [`page(...)`](#page)
- [`reset()`](#reset)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### initialize(...)

```typescript
initialize(options: InitializeOptions) => any
```

| Param         | Type                          |
| ------------- | ----------------------------- |
| **`options`** | <code>{ key: string; }</code> |

**Returns:** <code>any</code>

---

### identify(...)

```typescript
identify(options: IdentifyOptions) => any
```

| Param         | Type                                                     |
| ------------- | -------------------------------------------------------- |
| **`options`** | <code>Identity & { traits?: any; options?: any; }</code> |

**Returns:** <code>any</code>

---

### track(...)

```typescript
track(options: TrackOptions) => any
```

| Param         | Type                                                 |
| ------------- | ---------------------------------------------------- |
| **`options`** | <code>{ eventName: string; properties: any; }</code> |

**Returns:** <code>any</code>

---

### page(...)

```typescript
page(options: PageOptions) => any
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ pathname: string; }</code> |

**Returns:** <code>any</code>

---

### reset()

```typescript
reset() => any
```

**Returns:** <code>any</code>

---

</docgen-api>
