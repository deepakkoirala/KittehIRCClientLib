package org.kitteh.irc.client.library.implementation;

import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.element.Channel;
import org.kitteh.irc.client.library.element.MessageReceiver;
import org.kitteh.irc.client.library.element.User;
import org.kitteh.irc.client.library.element.mode.ModeStatusList;
import org.kitteh.irc.client.library.element.mode.UserMode;
import org.kitteh.irc.client.library.feature.AuthManager;
import org.kitteh.irc.client.library.feature.EventManager;
import org.kitteh.irc.client.library.feature.defaultmanager.DefaultAuthManager;
import org.kitteh.irc.client.library.feature.defaultmanager.DefaultCapabilityManager;
import org.kitteh.irc.client.library.feature.defaultmanager.DefaultEventManager;
import org.kitteh.irc.client.library.feature.defaultmanager.DefaultISupportManager;
import org.kitteh.irc.client.library.feature.defaultmanager.DefaultMessageTagManager;
import org.kitteh.irc.client.library.feature.defaultmanager.DefaultServerInfo;
import org.kitteh.irc.client.library.feature.defaultmessage.DefaultMessageMap;
import org.kitteh.irc.client.library.feature.defaultmessage.SimpleDefaultMessageMap;
import org.kitteh.irc.client.library.feature.sending.MessageSendingQueue;
import org.kitteh.irc.client.library.feature.sts.StsMachine;
import org.kitteh.irc.client.library.util.Cutter;
import org.kitteh.irc.client.library.util.Listener;
import org.kitteh.irc.client.library.util.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

class FakeClient extends InternalClient {
    private final AuthManager authManager = new DefaultAuthManager(this);
    private final DefaultCapabilityManager capabilityManager = new DefaultCapabilityManager(this);
    private final Config config = new Config();
    private final EventManager eventManager = new DefaultEventManager(this);
    private final Listener<Exception> listenerException = new Listener<>(this, null);
    private final Listener<String> listenerInput = new Listener<>(this, null);
    private final Listener<String> listenerOutput = new Listener<>(this, null);
    private Cutter messageCutter = new Cutter.DefaultWordCutter();
    private final DefaultMessageTagManager messageTagManager = new DefaultMessageTagManager(this);
    private final DefaultServerInfo serverInfo = new DefaultServerInfo(this);
    private DefaultMessageMap defaultMessageMap = new SimpleDefaultMessageMap(null);

    @Override
    public void processLine(@Nonnull String line) {

    }

    @Override
    public void sendNickChange(@Nonnull String newNick) {

    }

    @Override
    public void setCurrentNick(@Nonnull String nick) {

    }

    @Override
    public void setUserModes(@Nonnull ModeStatusList<UserMode> userModes) {

    }

    @Override
    public void startSending() {

    }

    @Override
    public void updateUserModes(@Nonnull ModeStatusList<UserMode> userModes) {

    }

    @Override
    public void reconnect() {

    }

    @Override
    public void reconnect(@Nullable String reason) {

    }

    @Override
    public boolean isSecureConnection() {
        return false;
    }

    @Nonnull
    @Override
    Config getConfig() {
        return this.config;
    }

    @Override
    public void setDefaultMessageMap(@Nonnull DefaultMessageMap defaults) {
        this.defaultMessageMap = defaults;
    }

    @Nonnull
    @Override
    public Listener<Exception> getExceptionListener() {
        return this.listenerException;
    }

    @Override
    public void setExceptionListener(@Nullable Consumer<Exception> listener) {

    }

    @Nonnull
    @Override
    public Listener<String> getInputListener() {
        return this.listenerInput;
    }

    @Override
    public void setInputListener(@Nullable Consumer<String> listener) {

    }

    @Override
    public void setMessageCutter(@Nonnull Cutter cutter) {
        this.messageCutter = cutter;
    }

    @Override
    public void setMessageSendingQueueSupplier(@Nonnull Function<Client, ? extends MessageSendingQueue> supplier) {

    }

    @Nonnull
    @Override
    public Set<String> getIntendedChannels() {
        return new HashSet<>();
    }

    @Nonnull
    @Override
    public DefaultISupportManager getISupportManager() {
        return null;
    }

    @Nonnull
    @Override
    public Optional<StsMachine> getStsMachine() {
        return Optional.empty(); // No STS in FakeClient for testing
    }

    @Nonnull
    @Override
    public Cutter getMessageCutter() {
        return this.messageCutter;
    }

    @Nonnull
    @Override
    public Function<Client, ? extends MessageSendingQueue> getMessageSendingQueueSupplier() {
        return null;
    }

    @Nonnull
    @Override
    public Listener<String> getOutputListener() {
        return this.listenerOutput;
    }

    @Override
    public void setOutputListener(@Nullable Consumer<String> listener) {

    }

    @Nonnull
    @Override
    public String getRequestedNick() {
        return "";
    }

    @Override
    public void connect() {

    }

    @Override
    public void beginMessageSendingImmediate(@Nonnull Consumer<String> consumer) {

    }

    @Nonnull
    @Override
    ActorProvider getActorProvider() {
        return null;
    }

    @Override
    public void ping() {

    }

    @Override
    public void addChannel(@Nonnull String... channel) {

    }

    @Override
    public void addKeyProtectedChannel(@Nonnull String channel, @Nonnull String key) {

    }

    @Override
    public void addKeyProtectedChannel(@Nonnull Pair<String, String>... channelsAndKeys) {

    }

    @Nonnull
    @Override
    public AuthManager getAuthManager() {
        return this.authManager;
    }

    @Nonnull
    @Override
    public DefaultCapabilityManager getCapabilityManager() {
        return this.capabilityManager;
    }

    @Nonnull
    @Override
    public Optional<Channel> getChannel(@Nonnull String name) {
        return Optional.empty();
    }

    @Nonnull
    @Override
    public Set<Channel> getChannels() {
        return Collections.emptySet();
    }

    @Nonnull
    @Override
    public Set<Channel> getChannels(@Nonnull Collection<String> channels) {
        return null;
    }

    @Nonnull
    @Override
    public DefaultMessageMap getDefaultMessageMap() {
        return this.defaultMessageMap;
    }

    @Nonnull
    @Override
    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Nonnull
    @Override
    public String getIntendedNick() {
        return this.config.getNotNull(Config.NICK);
    }

    @Nonnull
    @Override
    public DefaultMessageTagManager getMessageTagManager() {
        return this.messageTagManager;
    }

    @Nonnull
    @Override
    public String getName() {
        return this.config.getNotNull(Config.NAME);
    }

    @Nonnull
    @Override
    public String getNick() {
        return this.config.getNotNull(Config.NICK);
    }

    @Override
    public void setNick(@Nonnull String nick) {

    }

    @Nonnull
    @Override
    public DefaultServerInfo getServerInfo() {
        return this.serverInfo;
    }

    @Override
    public void pauseMessageSending() {

    }

    @Nonnull
    @Override
    public Optional<User> getUser() {
        return Optional.empty();
    }

    @Nonnull
    @Override
    public Optional<ModeStatusList<UserMode>> getUserModes() {
        return Optional.empty();
    }

    @Override
    public void knockChannel(@Nonnull String channelName) {

    }

    @Override
    public void removeChannel(@Nonnull String channel) {

    }

    @Override
    public void removeChannel(@Nonnull String channel, @Nullable String reason) {

    }

    @Override
    public void sendCtcpMessage(@Nonnull String target, @Nonnull String message) {

    }

    @Override
    public void sendCtcpMessage(@Nonnull MessageReceiver target, @Nonnull String message) {

    }

    @Override
    public void sendCtcpReply(@Nonnull String target, @Nonnull String message) {

    }

    @Override
    public void sendMessage(@Nonnull String target, @Nonnull String message) {

    }

    @Override
    public void sendMessage(@Nonnull MessageReceiver target, @Nonnull String message) {

    }

    @Override
    public void sendNotice(@Nonnull String target, @Nonnull String message) {

    }

    @Override
    public void sendNotice(@Nonnull MessageReceiver target, @Nonnull String message) {

    }

    @Override
    public void sendMultiLineMessage(@Nonnull String target, @Nonnull String message, @Nonnull Cutter cutter) {

    }

    @Override
    public void sendMultiLineNotice(@Nonnull String target, @Nonnull String message, @Nonnull Cutter cutter) {

    }

    @Override
    public void sendRawLine(@Nonnull String message) {

    }

    @Override
    public void sendRawLineAvoidingDuplication(@Nonnull String message) {

    }

    @Override
    public void sendRawLineImmediately(@Nonnull String message) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void shutdown(@Nullable String reason) {

    }

    @Nonnull
    @Override
    public Commands commands() {
        return null;
    }
}
