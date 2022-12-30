package me.cumhax.aehack.mod.modules.misc;

import me.cumhax.aehack.mod.modules.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatSuffix extends Module {
    public ChatSuffix() {
        super("ChatSuffix", "Appends your message", Category.MISC, true, false, false);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        String OnPointSuffix = " | Aehack++";
        if (event.getMessage().startsWith("/") || event.getMessage().startsWith(".")
                || event.getMessage().startsWith(",") || event.getMessage().startsWith("-")
                || event.getMessage().startsWith("!") || event.getMessage().startsWith("@")
                || event.getMessage().startsWith("$") || event.getMessage().startsWith("*")) return;
        event.setMessage(event.getMessage() + OnPointSuffix); // Adds the suffix to the end of the message
    }
}