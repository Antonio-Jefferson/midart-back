package midart.api.midart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "followers")
@Entity
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "follower_user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("followers")
    private User follower_user;

    @ManyToOne
    @JoinColumn(name = "followed_user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("followers")
    private User followed_user;
}
