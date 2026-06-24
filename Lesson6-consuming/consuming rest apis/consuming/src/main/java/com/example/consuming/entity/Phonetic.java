package com.example.consuming.entity;

public class Phonetic {
    private String text;
    private String audio;

    public Phonetic() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    @Override
    public String toString() {
        return "Phonetic{" +
                "text='" + text + '\'' +
                ", audio='" + audio + '\'' +
                '}';
    }
}

