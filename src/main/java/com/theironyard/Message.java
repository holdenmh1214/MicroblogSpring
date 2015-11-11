package com.theironyard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by holdenhughes on 11/9/15.
 */
@Entity
public class Message {
    @Id
    @GeneratedValue
    Integer id;
    String text;

    public Message(){

    }

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
