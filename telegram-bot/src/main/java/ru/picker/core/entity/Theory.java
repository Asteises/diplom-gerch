package ru.picker.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "theory")
@Getter
@Setter
@NoArgsConstructor
public class Theory {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotNull
    @Column(name = "info", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String info;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theory theory = (Theory) o;
        return id.equals(theory.id)
                && Objects.equals(name, theory.name)
                && Objects.equals(info, theory.info)
                && Objects.equals(chapter, theory.chapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}