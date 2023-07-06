package ru.picker.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "chapter")
@Getter
@Setter
@NoArgsConstructor
public class Chapter {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "chapter", fetch = FetchType.EAGER)
    private Set<SubChapter> subChapters = new LinkedHashSet<>();

    @OneToMany(mappedBy = "chapter", fetch = FetchType.EAGER)
    private Set<Theory> theories = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return id.equals(chapter.id)
                && Objects.equals(name, chapter.name)
                && Objects.equals(subChapters, chapter.subChapters)
                && Objects.equals(theories, chapter.theories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}