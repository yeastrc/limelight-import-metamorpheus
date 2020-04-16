package org.yeastrc.limelight.xml.metamorpheus.objects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class MetamorpheusProtein {
    private String sequence;
    private Collection<Annotation> annotations;

    public MetamorpheusProtein(String sequence) {
        this.sequence = sequence;
        this.annotations = new HashSet<>();
    }

    public String getSequence() {
        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetamorpheusProtein that = (MetamorpheusProtein) o;
        return sequence.equals(that.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequence);
    }

    private class Annotation {
        private String name;
        private String description;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Annotation that = (Annotation) o;
            return name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
