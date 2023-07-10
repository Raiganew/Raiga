package com.rga.webflux.models;

public class MessageJon
{
    private String message;

    public MessageJon(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
