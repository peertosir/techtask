package dev.peertosir.techtasksimbsoft.domain;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return Objects.equals(id, word.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
