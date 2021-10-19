package fr.univlyon1.m1if.m1if03.p1507426.classes;

public class Ballot {
    Bulletin bulletin;

    public Ballot(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    
    /** 
     * @return Bulletin
     */
    public Bulletin getBulletin() {
        return bulletin;
    }

    
    /** 
     * @param bulletin
     */
    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }
}
