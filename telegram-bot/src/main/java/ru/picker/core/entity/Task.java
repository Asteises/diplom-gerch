package ru.picker.core.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "task")
@NoArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @Column(name = "id", nullable = false)
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

}