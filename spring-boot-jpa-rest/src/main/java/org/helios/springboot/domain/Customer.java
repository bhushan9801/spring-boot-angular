package org.helios.springboot.domain;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customers")
@SequenceGenerator(name = "customers_seq", sequenceName = "customers_seq", allocationSize = 1, initialValue = 1000)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_seq")
    private Long id;
    @Audited
    @Column(nullable = false)
    private String name;
    @Audited
    @Column(unique = true, nullable = false)
    private String email;
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static Customer create(String name, String email) {
        Customer customer = new Customer();
        customer.name = name;
        customer.email = email;
        return customer;
    }
}
