package ru.picker.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotNull
    @Column(name = "test", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String test;

    @NotNull
    @Column(name = "answers", nullable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String answers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_chapter_id")
    private SubChapter subChapter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id)
                && Objects.equals(name, task.name)
                && Objects.equals(test, task.test)
                && Objects.equals(answers, task.answers)
                && Objects.equals(subChapter, task.subChapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}