export default
class Traitement {
    constructor(medic, duree, unitduree, freq, unitfreq, qte) {
        this._medic = medic;
        this._duree = duree;
        this._unitduree = unitduree;
        this._freq = freq;
        this._unitfreq = unitfreq;
        this._qte = qte;
    }

    get unitduree() {
        returnthis._unitduree;
    }
}