package com.bitunified.ledconfig.parts;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name="partList")
public class PartList {
    public PartList(){

    }
    private Set<Part> parts = new HashSet<Part>();

    public PartList(Set<Part> parts) {
        this.parts=parts;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
