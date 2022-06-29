
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

## API

<docgen-index>

* [`initialize(...)`](#initialize)
* [`identify(...)`](#identify)
* [`track(...)`](#track)
* [`page(...)`](#page)
* [`reset()`](#reset)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### initialize(...)

```typescript
initialize(options: InitializeOptions) => any
```

| Param         | Type                                                                                                     |
| ------------- | -------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ key: string; trackLifecycle?: boolean; recordScreenViews?: boolean; proxyHost?: string; }</code> |

**Returns:** <code>any</code>

--------------------


### identify(...)

```typescript
identify(options: IdentifyOptions) => any
```

| Param         | Type                                                     |
| ------------- | -------------------------------------------------------- |
| **`options`** | <code>Identity & { traits?: any; options?: any; }</code> |

**Returns:** <code>any</code>

--------------------


### track(...)

```typescript
track(options: TrackOptions) => any
```

| Param         | Type                                                 |
| ------------- | ---------------------------------------------------- |
| **`options`** | <code>{ eventName: string; properties: any; }</code> |

**Returns:** <code>any</code>

--------------------


### page(...)

```typescript
page(options: PageOptions) => any
```

| Param         | Type                               |
| ------------- | ---------------------------------- |
| **`options`** | <code>{ pathname: string; }</code> |

**Returns:** <code>any</code>

--------------------


### reset()

```typescript
reset() => any
```

**Returns:** <code>any</code>

--------------------

</docgen-api>

## License
MIT License
