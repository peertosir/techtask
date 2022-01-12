package dev.peertosir.techtasksimbsoft.repositories;

import dev.peertosir.techtasksimbsoft.domain.Word;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {
}
