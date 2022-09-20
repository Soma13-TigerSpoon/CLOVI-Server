package Soma.CLOVI.domain.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
public class Category {

  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  @Size(max = 45)
  @Column(name = "name", length = 45)
  private String name;

  @NotNull
  @Column(name = "depth", nullable = false)
  private Integer depth;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Category ParentCategory;

}