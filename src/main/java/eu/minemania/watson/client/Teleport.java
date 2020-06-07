package eu.minemania.watson.client;

import java.util.BitSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.minemania.watson.Watson;
import eu.minemania.watson.chat.ChatMessage;
import eu.minemania.watson.config.Configs;

public class Teleport
{
    public static void teleport(int x, int y, int z)
    {
        String format = Configs.Generic.TELEPORT_COMMAND.getStringValue();
        Pattern specifier = Pattern.compile("%[dg]");
        Matcher specifiers = specifier.matcher(format);

        BitSet isDouble = new BitSet();
        int i = 0;
        while (specifiers.find())
        {
            isDouble.set(i, specifiers.group().equals("%g"));
            ++i;
        }
        Number nx = (isDouble.get(0) ? (x+0.5) : x);
        Number ny = (isDouble.get(1) ? (y+0.5) : y);
        Number nz = (isDouble.get(2) ? (z+0.5) : z);
        String command = String.format(format, nx, ny, nz);
        if(Configs.Generic.DEBUG.getBooleanValue())
        {
            Watson.logger.info(command);
        }
        ChatMessage.sendToServerChat(command);
    }
}