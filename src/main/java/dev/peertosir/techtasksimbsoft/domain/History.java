package dev.peertosir.techtasksimbsoft.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class History {
    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private Instant parseTime;

    public History() {

    }

    public History(String url, Instant parseTime) {
        this.url = url;
        this.parseTime = parseTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Instant getParseTime() {
        return parseTime;
    }

    public void setParseTime(Instant parseTime) {
        this.parseTime = parseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        History history = (History) o;

        return Objects.equals(id, history.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}

