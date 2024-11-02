package interviews.tech.companies.atlassian.connectionpool;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
}
