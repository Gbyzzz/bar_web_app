package com.gbyzzz.bar_web_app.bar_email.messaging.entity;

import com.fasterxml.jackson.annotation.*;
import com.gbyzzz.bar_web_app.bar_email.entity.Code;

import java.util.Objects;

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Message.class)
//@JsonTypeName("message")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = Message.class)
public class Message {

    @JsonProperty("object")
    private Code object;
    private String instructions;

    public Message() {
    }

    public Message(Code object, String instructions) {
        this.object = object;
        this.instructions = instructions;
    }

    public Code getObject() {
        return object;
    }

    public void setObject(Code object) {
        this.object = object;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(object, message.object) && Objects.equals(instructions, message.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object, instructions);
    }

    @Override
    public String toString() {
        return "Message{" +
                "object=" + object +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
