<img width="348" alt="capacitor-segment logo" src="https://user-images.githubusercontent.com/12596485/147050756-e69b1c19-7adb-4b12-a029-65f7faae3dcb.png">

# capacitor-segment

A Capacitor plugin for Segment analytics

## Install

```sh
npm install @joinflux/capacitor-segment

# yarn
yarn add @joinflux/capacitor-segment

# pnpm
pnpm add @joinflux/capacitor-segment
```

and

```sh
npx cap sync
```

### Versioning

- Version 0.1.3 will target the latest Capacitor version
  - Capacitor 3 is recommended for this version
- Version 1.x.x will target Capacitor 4
- Version 2.x.x will target Capacitor 5

## Usage

First need initialize plugin with you [writeKey](https://segment.com/docs/connections/find-writekey/), and then use track methods:

```ts 
import { Segment } from '@joinflux/capacitor-segment'

Segment.initialize({
  key: 'you write key',
})

// see API section with other methods 
Segment.track({ eventName: 'Hello World'})
```

For prevent crashing app in HMR on init hook, recommended handling exception: 

```ts
await Segment.initialize({
  // ...
}).catch((e) => console.error(e))
```

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

| Param         | Type                                                                                 |
| ------------- | ------------------------------------------------------------------------------------ |
| **`options`** | <code>{ key: string; trackLifecycle?: boolean; recordScreenViews?: boolean; }</code> |

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

## License

MIT License
