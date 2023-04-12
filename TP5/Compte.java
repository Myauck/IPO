public class Compte {
    
    private double aSolde;
    
    
    public Compte(final double pSolde) {
        this.aSolde = pSolde;
    }
    
    
    public double getSolde() {
        return this.aSolde;
    }
    
    private void affecte(final double pSolde) {
        double reel = Math.abs(pSolde);
        int entier = (int) (reel*1000);
        if(entier%10 >= 5) reel += 0.01;
        this.aSolde = Math.copySign(((int) (reel*100))/100.0, pSolde);
    }

    public void debite(final double pMontant) {
        this.affecte(this.aSolde-pMontant);
    }

    public void credite(final double pMontant) {
        this.affecte(this.aSolde+pMontant);
    }
    
    
    
    
}