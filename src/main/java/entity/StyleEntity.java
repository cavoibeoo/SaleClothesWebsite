package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "style", schema = "clothesstore", catalog = "")
public class StyleEntity {
    private int styleId;
    private String styleName;

    @Id
    @Column(name = "style_id")
    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    @Basic
    @Column(name = "style_name")
    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StyleEntity that = (StyleEntity) o;
        return styleId == that.styleId && Objects.equals(styleName, that.styleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(styleId, styleName);
    }
}
