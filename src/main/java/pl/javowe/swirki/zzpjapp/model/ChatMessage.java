package pl.javowe.swirki.zzpjapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable =  false)
    private Long id;

    @Column(name = "message_type", nullable = false)
    private MessageType type;

    @Column(name = "content", nullable = false)
    @NotEmpty
    @Max(500)
    private String content;

    @Column(name = "sender", nullable = false)
    @NotEmpty
    @Max(50)
    private String sender;

    public ChatMessage()
    {

    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
