package ies.grupo51.lockedin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ies.grupo51.lockedin.exceptions.ResourceNotFoundException;
import ies.grupo51.lockedin.models.Message;
import ies.grupo51.lockedin.repositories.MessageRepository;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository repository;

    public Message saveMessage(Message message){
        return repository.save(message);
    }

    public List<Message> saveMessages(Set<Message> messages) {
        return repository.saveAll(messages);
    }

    public List<Message> getMessages() {
        return repository.findAll();
    }

    public Message getMessageById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
    }

    public Message updateMessage(Message message) throws ResourceNotFoundException {
        Message existingMessage = repository.findById(message.getId()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
        
        if (existingMessage == null){ return null; }

        existingMessage.setSender_id(message.getSender_id());
        existingMessage.setReciever_id(message.getReciever_id());
        existingMessage.setContent(message.getContent());

        return repository.save(existingMessage);
    }
}
