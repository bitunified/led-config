package com.bitunified.ledconfig.domain.relation;


public class RelationLink {

    private String description;
    private RelationDependency relationDependency1;
    private RelationDependency relationDependency2;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RelationDependency getRelationDependency1() {
        return relationDependency1;
    }

    public void setRelationDependency1(RelationDependency relationDependency1) {
        this.relationDependency1 = relationDependency1;
    }

    public RelationDependency getRelationDependency2() {
        return relationDependency2;
    }

    public void setRelationDependency2(RelationDependency relationDependency2) {
        this.relationDependency2 = relationDependency2;
    }
}
