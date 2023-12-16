package lab6_q1;

import javax.persistence.*;

@Entity
public class CD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CD_NAME", nullable = false)
    private String cdName;

    @Column(name = "BAND_NAME", nullable = false)
    private String bandName;


    // Constructors
    public CD() {
    }

    public CD(String cdName, String bandName) {
        this.cdName = cdName;
        this.bandName = bandName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }
}
