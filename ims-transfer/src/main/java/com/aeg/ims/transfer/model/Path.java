package com.aeg.ims.transfer.model;


import com.aeg.domain.LayerSupertype;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "PATH")
@EqualsAndHashCode(callSuper=false)
public class Path extends LayerSupertype {

    private String name;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
    private Path parent = null;

    @OneToMany(mappedBy = "parent")
    @OrderColumn
    private Set<Path> children = new HashSet<Path>();


    private Path(final String name) {
        this.parent = null;
        this.name = name;
    }

    public Path(final Path parent, final String name) {
        if(parent==null) throw new IllegalArgumentException("parent required");
        this.parent = parent;
        this.name = name;
        registerInParentsChilds();
    }

    private void registerInParentsChilds() {
        this.parent.children.add(this);
    }

    public Set<Path> getChildren() {
        return Collections.unmodifiableSet(this.children);
    }

    public void move(final Path newParent)  {
        //Preconditions.checkNotNull(newParent, "newParent");

        /* detect circles... */
        /*if (!isProperMoveTarget(newParent)  ) {
            throw new IllegalArgumentException("move", "not a proper new parent", this);
        }*/

        this.parent.children.remove(this);
        this.parent = newParent;
        registerInParentsChilds();
    }

    public static Path createRoot(final String name) {
        return new Path(name);
    }
}
