package ru.picker.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @NotNull
    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}