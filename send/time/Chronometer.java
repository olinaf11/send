package time;

public class Chronometer {
    long tempsDepart ;
    long tempsFin ;
    long pauseDepart ;
    long pauseFin ;
    long duree ;

    public long getTempsDepart() {
        return tempsDepart;
    }

    public void setTempsDepart(long tempsDepart) {
        this.tempsDepart = tempsDepart;
    }

    public long getTempsFin() {
        return tempsFin;
    }

    public void setTempsFin(long tempsFin) {
        this.tempsFin = tempsFin;
    }

    public long getPauseDepart() {
        return pauseDepart;
    }

    public void setPauseDepart(long pauseDepart) {
        this.pauseDepart = pauseDepart;
    }

    public long getPauseFin() {
        return pauseFin;
    }

    public void setPauseFin(long pauseFin) {
        this.pauseFin = pauseFin;
    }

    public long getDuree() {
        return duree;
    }

    public void setDuree(long duree) {
        this.duree = duree;
    }

    public Chronometer(){
        setDuree(0);
        setPauseDepart(0);
        setPauseFin(0);
        setTempsFin(0);
        setTempsDepart(0);
    }
    public void start(){
        setTempsDepart(System.currentTimeMillis());
        setTempsFin(0);
        setPauseDepart(0);
        setPauseFin(0);
        setDuree(0);
    }

    public void pause(){
        if(getTempsDepart()==0){return;}
       setPauseDepart(System.currentTimeMillis());
    }

    public  void resume(){
        if(getTempsDepart()==0){return;}
        if(getTempsDepart()==0){return;}
        setPauseFin(System.currentTimeMillis());
        setTempsDepart(tempsDepart+(pauseFin-pauseDepart));
        setTempsFin(0);
        setPauseDepart(0);
        setPauseFin(0);
        setDuree(0);
    }

    public void stop(){
        if(getTempsDepart()==0){return;}
        setTempsFin(System.currentTimeMillis());
        setDuree(tempsFin-tempsDepart);
        setTempsDepart(0);
        setTempsFin(0);
        setPauseDepart(0);
        setTempsFin(0);
    }

    public long getDureeSec(){
        return duree/1000;
    }

    public long getDureeMs(){
        return duree;
    }
    public String getDureeTxt(){
        return timeToHMS(getDureeSec());
    }

    private String timeToHMS(long dureeSec) {
        int h = (int) (dureeSec/3600);
        int m = (int) ((dureeSec %3600)/60);
        int s = (int) (dureeSec % 60);
        String r ="";
        if(h>0){r+=h+" h ";}
        if(m>0){r+=m+" m ";}
        if(s>0){r+=s+" s ";}
        if(h<=0 && m<=0 && s<=0){r="0s";}
        return r;
    }
}
