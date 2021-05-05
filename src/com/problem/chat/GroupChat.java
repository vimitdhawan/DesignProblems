package com.problem.chat;

import java.util.*;

public class GroupChat extends Conversation {
    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public void addParticipant(User user) {
        participants.add(user);
    }
}