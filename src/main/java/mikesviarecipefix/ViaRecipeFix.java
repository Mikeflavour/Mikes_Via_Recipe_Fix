package mikesviarecipefix;

import java.util.Set;
import java.util.UUID;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.impl.recipe.sync.SyncedSerializerAwareConnection;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Recipe mods like JEI ask the server for Fabric's recipe sync. That packet carries recipes in the
 * server's format, ViaVersion does not translate it, and an older client fails to decode it and
 * disconnects. So players on another version simply get no recipe sync.
 */
public final class ViaRecipeFix implements ModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("Via Recipe Fix");

    @Override
    public void onInitialize() {
        if (!FabricLoader.getInstance().isModLoaded("viaversion")) {
            LOGGER.info("Via Recipe Fix: ViaVersion not found, nothing to do");
            return;
        }
        // The default phase runs before Fabric's own recipe_sync phase, which sends the recipes.
        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register((player, joined) -> {
            if (ViaCheck.otherVersion(player.getUUID())) {
                ((SyncedSerializerAwareConnection) player.connection.connection)
                    .fabric_setSyncedRecipeSerializers(Set.of());
            }
        });
        LOGGER.info("Via Recipe Fix: active");
    }

    /** Own class, so ViaVersion classes only load when ViaVersion is there. */
    private static final class ViaCheck {
        static boolean otherVersion(UUID player) {
            UserConnection connection = Via.getManager().getConnectionManager().getConnectedClient(player);
            // Unknown to Via: leave the player as it is.
            return connection != null
                && connection.getProtocolInfo().protocolVersion().getVersion()
                    != connection.getProtocolInfo().serverProtocolVersion().getVersion();
        }
    }
}
