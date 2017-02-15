package com.bitunified.ledconfig.domain;


import com.bitunified.ledconfig.domain.I18N.Locale;
import com.bitunified.ledconfig.domain.modeltypes.ConfigurationModel;
import com.bitunified.ledconfig.domain.modeltypes.RealModel;
import com.bitunified.ledconfig.parts.Relatable;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@XmlSeeAlso({RealModel.class, ConfigurationModel.class})
public class Model extends Relatable implements StepConfig, Serializable {

    private String name = "";

    private String code;
    private Integer step;


    private Map<String, Property> properies = new HashMap<String, Property>();

    private Map<Locale, String> translations = new HashMap<Locale, String>();

    public Property addProperty(Property property) {
        this.properies.put(property.getName(), property);
        return property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Property getProperty(String propName) {
        return properies.get(propName);

    }

    public Set getProperies() {
        return properies.entrySet();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (name != null ? !name.equals(model.name) : model.name != null) return false;
        if (code != null ? !code.equals(model.code) : model.code != null) return false;
        if (step != null ? !step.equals(model.step) : model.step != null) return false;
        return properies != null ? properies.equals(model.properies) : model.properies == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (properies != null ? properies.hashCode() : 0);
        return result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<Locale, String> getTranslations() {
        return translations;
    }

    public void setTranslations(Locale locale, String s) {
        translations.put(locale, s);
    }

    public void setTranslations(Map<Locale, String> translations) {
        this.translations = translations;
    }

}
