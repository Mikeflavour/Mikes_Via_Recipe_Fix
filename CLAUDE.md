# Mikes_Via_Recipe_Fix

Tiny server-side Fabric mod: no Fabric recipe sync (`fabric:recipe_sync`) for players ViaVersion reports on another protocol version, so older clients with JEI can join. Part of Mike's "server without client mods" setup (see Mikes_Chest_Server_Test, Mikes_Upper_Only_Server).

## Rules
- Branch = MC version (`26.3`). Bump `mod_version` for every build Mike tests.
- ViaVersion is optional: Via classes only in `ViaCheck`, guarded by `isModLoaded("viaversion")`.
- Uses Fabric internals (`SyncedSerializerAwareConnection`): recheck on every Fabric API update.

## Build
- Only via GitHub Actions (cloud can't reach Fabric/Mojang maven). Java 25.

## Talk to Mike
- German, short, one step at a time.
