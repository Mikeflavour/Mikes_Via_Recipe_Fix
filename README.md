# Mike's Via Recipe Fix

**Server-side Fabric mod.** Lets players with an older Minecraft version join through [ViaFabric](https://modrinth.com/mod/viafabric) + ViaBackwards while they have a recipe mod like JEI installed.

## The problem

Recipe mods (JEI, REI, EMI) ask the server for Fabric API's recipe sync. The server sends its recipes in its own format. ViaVersion does not translate that packet, so an older client cannot read it and is disconnected:

`Failed to decode packet 'clientbound/minecraft:custom_payload' (fabric:recipe_sync)`

## The fix

Players whose version differs from the server's (as ViaVersion sees it) get no recipe sync. They can join with JEI; JEI may show fewer server recipes for them. Players on the server's version get everything as before. Without ViaVersion the mod does nothing.

## Installation

Put `mikes_via_recipe_fix-<version>+<mc>.jar` into the server's `mods` folder, next to Fabric API and ViaFabric. Players need nothing.

| Minecraft | Mod version | Loader |
|---|---|---|
| **26.3** | 0.1.0 | Fabric |
