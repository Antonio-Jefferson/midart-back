package midart.api.midart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drawings")
public class Drawing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String image_url;
    private Timestamp created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "drawing", cascade = CascadeType.ALL)
    private List<Favorites> favorites;
}
