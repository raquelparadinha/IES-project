package ies.grupo51.lockedin.models;

import java.text.DateFormat;
import java.util.List;
import java.util.UUID;

public interface Staff {

    // SETS

    public void setId(UUID id);
    public void setName(String name);
    public void setEmail(String email);
    public void setPhone(String phone);
    public void setBirth_date(DateFormat birth_date);
    public void setMessages(List<Message> messages);

    // GETS

    public UUID getId();
    public List<Message> getMessages();
    public String getName();
    public String getEmail();
    public String getPhone();
    public DateFormat getBirth_date();

    // CUSTOM

    public void addMessage(Message message);
}
