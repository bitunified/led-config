package com.bitunified.ledconfig.domain.relation;


import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.domain.product.profile.Profile;

import java.util.ArrayList;
import java.util.List;

public class RelationDefinition {

    private RelationState relationState=RelationState.FORBIDDEN;
    private String forBiddenMessage;
    private String allowedWithWarningMessage;

    private List<Model> models=new ArrayList<Model>();

    public RelationDefinition(RelationState state,String message) {
        this.allowedWithWarningMessage=message;
        if (state==RelationState.FORBIDDEN){
            this.forBiddenMessage=message;
        }
        this.relationState=state;
    }


    public String getForBiddenMessage() {
        return forBiddenMessage;
    }

    public void setForBiddenMessage(String forBiddenMessage) {
        this.forBiddenMessage = forBiddenMessage;
    }

    public String getAllowedWithWarningMessage() {
        return allowedWithWarningMessage;
    }

    public void setAllowedWithWarningMessage(String allowedWithWarningMessage) {
        this.allowedWithWarningMessage = allowedWithWarningMessage;
    }


    public RelationState getRelationState() {
        return relationState;
    }

    public void setRelationState(RelationState relationState) {
        this.relationState = relationState;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationDefinition that = (RelationDefinition) o;

        if (relationState != that.relationState) return false;
        return models != null ? models.equals(that.models) : that.models == null;

    }

    @Override
    public int hashCode() {
        int result = relationState != null ? relationState.hashCode() : 0;
        result = 31 * result + (models != null ? models.hashCode() : 0);
        return result;
    }

    public void addModel(Model model) {
        models.add(model);
    }
}
