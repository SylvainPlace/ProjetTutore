export default
    class Traitement {
    constructor(medic, maladie, utilisateurs, duree, unitduree, freq, unitfreq, qte) {
        this._medic = medic;
        this._maladie = maladie;
        this._utilisateurs = utilisateurs;
        this._duree = duree;
        this._unitduree = unitduree;
        this._freq = freq;
        this._unitfreq = unitfreq;
        this._qte = qte;
        this._date = new Date();
    }

}