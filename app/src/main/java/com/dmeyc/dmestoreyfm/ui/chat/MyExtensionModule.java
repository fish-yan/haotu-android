package com.dmeyc.dmestoreyfm.ui.chat;

import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;
import io.rong.sight.SightPlugin;

public class MyExtensionModule extends DefaultExtensionModule {
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {

        List<IPluginModule> pluginModules =  super.getPluginModules(conversationType);
        pluginModules.clear();
        IPluginModule sightPlugin = new SightPlugin();
        pluginModules.add(sightPlugin);
        return pluginModules;
    }
}
