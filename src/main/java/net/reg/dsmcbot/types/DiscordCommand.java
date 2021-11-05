package net.reg.dsmcbot.types;

import java.util.List;

public class DiscordCommand {
    private List<String> callWords;
    private int id;
    private byte priority;

    public DiscordCommand(List<String> callWords, int id, byte priority) {
        this.callWords = callWords;
        this.id = id;
        this.priority = priority;
    }

    public List<String> getCallWords() {
        return callWords;
    }

    public void setCallWords(List<String> callWords) {
        this.callWords = callWords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public boolean isThisCommand(String original) {
        for (String check:callWords) {
            if (!original.contains(check)) return false;
        }
        return true;
    }
}