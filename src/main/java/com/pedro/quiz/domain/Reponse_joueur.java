package com.pedro.quiz.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Reponse_joueur.
 */
@Entity
@Table(name = "reponse_joueur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Reponse_joueur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Column(name = "label")
    private String label;
    
    @Column(name = "label_reponse")
    private String label_reponse;
    
    @Column(name = "reponse_ok")
    private Boolean reponse_ok;

    @ManyToOne
    private User user;

    @OneToOne
    private Question question;

    @OneToOne
    private Reponse reponse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel_reponse() {
        return label_reponse;
    }

    public void setLabel_reponse(String label_reponse) {
        this.label_reponse = label_reponse;
    }

    public Boolean getReponse_ok() {
        return reponse_ok;
    }

    public void setReponse_ok(Boolean reponse_ok) {
        this.reponse_ok = reponse_ok;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reponse_joueur reponse_joueur = (Reponse_joueur) o;

        if ( ! Objects.equals(id, reponse_joueur.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Reponse_joueur{" +
                "id=" + id +
                ", label='" + label + "'" +
                ", label_reponse='" + label_reponse + "'" +
                ", reponse_ok='" + reponse_ok + "'" +
                '}';
    }
}
