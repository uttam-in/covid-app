package org.codejudge.sb.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class Exposed extends Auditable {
    private String uuid; // autogenerated at the time of object creation
    // we will have to place an index on this


    @Override
    public String toString() {
        return ", uuid='" + uuid + '\'' + super.toString();
    }
}
