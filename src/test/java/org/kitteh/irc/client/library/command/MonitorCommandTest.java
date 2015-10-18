package org.kitteh.irc.client.library.command;

import org.junit.Test;
import org.kitteh.irc.client.library.Client;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Tests the MonitorCommand class using mocks.
 *
 * @see MonitorCommand
 */
public class MonitorCommandTest {
    /**
     * Wrapping to a new message occurs after this length.
     */
    public static final int CAPABILITY_REQUEST_SOFT_LIMIT = 200;

    /**
     * Tests adding targets by array.
     */
    @Test
    public void testAddArray() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.action(MonitorCommand.Action.ADD_TARGET);
        command.target("meow", "purr", "purr");
        command.execute();

        Mockito.verify(ircClient).sendRawLine("MONITOR + meow,purr");
    }

    /**
     * Tests adding targets by collection.
     */
    @Test
    public void testAddList() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.action(MonitorCommand.Action.ADD_TARGET);
        command.target(Arrays.asList("meow", "purr", "meow"));
        command.execute();

        Mockito.verify(ircClient).sendRawLine("MONITOR + meow,purr");
    }

    /**
     * Tests removing targets by collection.
     */
    @Test
    public void testRemove() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.action(MonitorCommand.Action.REMOVE_TARGET);
        command.target(Arrays.asList("meow", "purr"));
        command.execute();

        Mockito.verify(ircClient).sendRawLine("MONITOR - meow,purr");
    }

    /**
     * Tests a targetless failure.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddNoTarget() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.action(MonitorCommand.Action.ADD_TARGET);

        command.execute();
    }

    /**
     * Tests invalid target input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTarget() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.target(Arrays.asList("meow,", "purr"));
    }

    /**
     * Tests an actionless execution.
     */
    @Test(expected = IllegalStateException.class)
    public void testAddNoAction() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.target(Arrays.asList("meow", "purr"));

        command.execute();
    }

    /**
     * Tests clearing the list.
     */
    @Test
    public void testClear() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.action(MonitorCommand.Action.CLEAR_ALL_TARGETS);
        command.target("irrelevant");
        command.execute();

        Mockito.verify(ircClient).sendRawLine("MONITOR C");
    }

    /**
     * Tests requesting the list.
     */
    @Test
    public void testList() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.action(MonitorCommand.Action.LIST_TARGETS);
        command.target("irrelevant");
        command.execute();

        Mockito.verify(ircClient).sendRawLine("MONITOR L");
    }

    /**
     * Tests requesting status.
     */
    @Test
    public void testStatus() {
        Client ircClient = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClient);

        command.action(MonitorCommand.Action.STATUS_OUTPUT_ALL);
        command.execute();

        Mockito.verify(ircClient).sendRawLine("MONITOR S");
    }

    /**
     * Ensure if we request more targets than fit in the maximum message
     * length that the request is split up.
     */
    @Test
    public void testManyTargetsResultsInMultipleRequestMessages() {
        Client ircClientMock = getClientMock();
        MonitorCommand command = new MonitorCommand(ircClientMock);

        List<String> list = new LinkedList<>();
        char c = 'a';
        StringBuilder builder = new StringBuilder(10);
        for (int x = 0; x < 21; x++) {
            for (int y = 0; y < 10; y++) {
                builder.append(c);
            }
            list.add(builder.toString());
            builder.setLength(0);
            c++;
        }
        command.action(MonitorCommand.Action.ADD_TARGET);
        command.target(list);
        command.execute();

        Mockito.verify(ircClientMock, Mockito.times(2)).sendRawLine(Mockito.anyString());
    }

    /**
     * Gets the mock for the Client interface.
     *
     * @return Client mock.
     */
    private static Client getClientMock() {
        return Mockito.mock(Client.class);
    }
}
