package dev.peertosir.techtasksimbsoft.domain;

import javax.persistence.*;

@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;

    private String word;

    private Long count;

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    public Word() {
    }

    public Word(String word, Long count, History history) {
        this.word = word;
        this.count = count;
        this.history = history;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public History getHistory() {
        return history;
    }
}
