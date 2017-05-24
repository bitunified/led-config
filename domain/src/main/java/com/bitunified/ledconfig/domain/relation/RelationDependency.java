package com.bitunified.ledconfig.domain.relation;


import java.util.ArrayList;
import java.util.List;

public class RelationDependency {

    private List<RelationLink> relations = new ArrayList<RelationLink>();

    public List<RelationLink> getRelations() {
        return relations;
    }

    public void setRelations(List<RelationLink> relations) {
        this.relations = relations;
    }
}
