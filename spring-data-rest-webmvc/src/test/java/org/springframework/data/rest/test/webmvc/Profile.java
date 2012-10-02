package org.springframework.data.rest.test.webmvc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

/**
 * @author Jon Brisbin <jbrisbin@vmware.com>
 */
@Entity
public class Profile {

  @Id @GeneratedValue private Long   id;
  private                     String type;
  private                     String url;
  @JsonBackReference
  @ManyToOne(optional = false)
  private                     Person person;

  public Profile() {
  }

  public Profile(String type, String url) {
    this.type = type;
    this.url = url;
  }

  public Profile(String type, String url, Person person) {
    this.type = type;
    this.url = url;
    this.person = person;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override public boolean equals(Object o) {
    if(!(o instanceof Profile)) {
      return false;
    }

    Profile p2 = (Profile)o;

    boolean idEq;
    if(null != id) {
      idEq = id.equals(p2.id);
    } else {
      idEq = p2.id == null;
    }

    boolean typeEq;
    if(null != type) {
      typeEq = type.equals(p2.type);
    } else {
      typeEq = p2.type == null;
    }

    boolean urlEq;
    if(null != url) {
      urlEq = url.equals(p2.url);
    } else {
      urlEq = p2.url == null;
    }

    return idEq && typeEq && urlEq;
  }

  @Override public String toString() {
    return "Profile{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", url='" + url + '\'' +
        '}';
  }

}
