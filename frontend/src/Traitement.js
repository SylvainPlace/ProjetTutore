export default
    class Traitement {
    constructor(medic, medicNom, maladie, maladieNom, utilisateurs, duree, unitduree, freq, unitfreq, qte) {
        this._medic = medic;
        this._medicNom = medicNom;
        this._maladie = maladie;
        this._maladieNom = maladieNom;
        this._utilisateurs = utilisateurs;
        this._duree = duree;
        this._unitduree = unitduree;
        this._freq = freq;
        this._unitfreq = unitfreq;
        this._qte = qte;
        this._date = new Date();
    }

}