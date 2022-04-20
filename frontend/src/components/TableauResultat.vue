<script setup>
import { reactive, onUpdated } from "vue";
import SelecteurPatient from './SelecteurPatient.vue';
import ConfirmationSupprimer from './ConfirmationSupprimer.vue';
import SwitchColonne from './SwitchColonne.vue';

const data = reactive({
    id: "",
    soigners: [],
    medicamentsSuppr: [],
});

onUpdated(() => {
    formeAjd();
});

//executé lorsqu'un patient est sélectionné
function choixPatient(patient) {
    data.id = patient;
    fetchSoignersMedicament();
}

// Récupère les informations à afficher 
function fetchSoignersMedicament() {
    fetch("/api/PatientSoignerMedicament/" + data.id)
        .then((response) => response.json())
        .then((json) => {
            data.soigners = json;
            calculDates();
            if (!gestionTraitementFini()) {
                data.soigners.sort(triDatePriseCroissant);
                gestionPluriels();
            }
        })
        .catch((error) => alert(error));
}

// supprime les traitements et affiche une alerte
function gestionTraitementFini() {
    let bDelete = false;
    for (let s of data.soigners) {
        if (new Date(s.dateFin) < new Date()) {
            document.getElementById("alerteMessage").innerHTML +=
                "<div class='alert alert-success d-flex alert-dismissible fade show' role='alert'> <svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor'class='bi bi-check-circle-fill mx-2' viewBox='0 0 16 16'> <path d='M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z' />   </svg>"
                + "<div> Bravo, vous avez fini de prendre : "
                + s.nomMedicament
                + "</div> <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button></div>";
            deleteFetch(s.id);
            bDelete = true;
        }
    }
    return bDelete;
}


// ajoute les s necessaires pour l'affichage des données
function gestionPluriels() {
    for (let s of data.soigners) {
        if (s.valDuree > 1 && s.uniteDuree != "Mois") {
            s.uniteDuree += "s";
        }
        if (s.doseParPrise >= 2) {
            s.dose = "doses";
        } else {
            s.dose = "dose";
        }
    }
}

// calcule les dates nécessaires pour l'affichage des données
function calculDates() {
    for (let s of data.soigners) {
        calculDateFin(s, new Date(s.dateCreation));
        calculDateProchain(s, new Date(s.dateCreation));
        calculDiffDates();
        formatDates();
    }
}


// calcule la différence entre la date de création et la date actuelle puis l'arrange sous la forme d'un pourcentage d'avancement
function calculDiffDates() {
    for (let s of data.soigners) {
        let reste = new Date(s.dateFin) - new Date();
        let consomme = new Date() - new Date(s.dateCreation);
        s.avancement = Math.round((consomme / (reste + consomme)) * 100);
    }
}

// calcule la date de fin d'un traitement
function calculDateFin(s, date) {
    switch (s.uniteDuree) {
        case "Jour":
            date.setDate(date.getDate() + s.valDuree);
            break;
        case "Semaine":
            date.setDate(date.getDate() + s.valDuree * 7);
            break;
        case "Mois":
            date.setMonth(date.getMonth() + s.valDuree);
            break;
        case "Annee":
            date.setFullYear(date.getFullYear() + s.valDuree);
            break;
        default:
            break;
    }
    s.dateFin = date;
}

// calcule la date du prochain traitement
function calculDateProchain(s, date) {
    let i = 1;
    let ajd = new Date();
    switch (s.uniteFreq) {
        case "Jour":
            date = ajd;
            break;
        case "Semaine":
            while (date < ajd) {
                date.setDate(date.getDate() + Math.round(7 / s.valFreq));
            }
            break;
        case "Mois":
            do {
                date.setDate(date.getDate() + Math.round(30 / s.valFreq));
            } while (date < ajd);
            break;
        default:
            break;
    }
    s.datePrise = date.toISOString().substring(0, 10);
}


// formatte les dates pour l'affichage
function formatDates() {

    let ajd = new Date();
    let demain = new Date();
    demain.setDate(demain.getDate() + 1);
    for (let s of data.soigners) {
        let dateD = new Date(s.dateCreation);
        s.dateCreationAffichage = dateD.toLocaleDateString();
        if (s.dateCreationAffichage == ajd.toLocaleDateString()) {
            s.dateCreationAffichage = "Aujourd'hui";
        }
        let dateF = new Date(s.dateFin);
        s.dateFinAffichage = dateF.toLocaleDateString();
        if (s.dateFinAffichage == ajd.toLocaleDateString()) {
            s.dateFinAffichage = "Aujourd'hui";
        }
        let dateP = new Date(s.datePrise);
        s.datePriseAffichage = dateP.toLocaleDateString();
        if (s.datePriseAffichage == ajd.toLocaleDateString()) {
            s.datePriseAffichage = "Aujourd'hui";
        } else if (s.datePriseAffichage == demain.toLocaleDateString()) {
            s.datePriseAffichage = "Demain";
        }
    }
}


// supprime un traitement
function deleteFetch(id) {
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {
        method: "DELETE",
        headers: myHeaders,
    };
    fetch("/api/soigners/" + id, fetchOptions)
        .then(() => {
            fetchSoignersMedicament();
        })
        .catch((error) => alert(error));
}

// Fonctions de tri suivant les différents paramètres
function triNomMaladieCroissant(a, b) {
    if (a.nomMaladie < b.nomMaladie) return -1;
    else if (a.nomMaladie == b.nomMaladie) return 0;
    else return 1;
}
function triNomMedicamentCroissant(a, b) {
    if (a.nomMedicament < b.nomMedicament) return -1;
    else if (a.nomMedicament == b.nomMedicament) return 0;
    else return 1;
}
function triDateDebutCroissant(a, b) {
    if (a.dateCreation < b.dateCreation) return -1;
    else if (a.dateCreation == b.dateCreation) return 0;
    else return 1;
}
function triDatePriseCroissant(a, b) {
    if (a.datePrise < b.datePrise) return -1;
    else if (a.datePrise == b.datePrise) return 0;
    else return 1;
}
function triDateFinCroissant(a, b) {
    if (a.dateFin < b.dateFin) return -1;
    else if (a.dateFin == b.dateFin) return 0;
    else return 1;
}
function triAvancementCroissant(a, b) {
    if (a.avancement < b.avancement) return -1;
    else if (a.avancement == b.avancement) return 0;
    else return 1;
}

// Tri les données (les mets dans l'ordre inverse si elles sont déjà triées)
function choixTri(nom) {
    let t = [...data.soigners];
    data.soigners.sort(nom);
    let v = [...data.soigners];
    if (JSON.stringify(t) == JSON.stringify(v)) {
        data.soigners.reverse();
    }
}


// Affiche ou cache la colonne choisie
function cacherAfficherColonne(attibut) {
    var el = document.getElementsByClassName(attibut);
    for (var i = 0; i < el.length; i++) {
        if (el[i].classList.contains("d-none")) {
            el[i].classList.remove("d-none");
        } else {
            el[i].classList.add("d-none");
        }
    }
}


// formatte la date d'aujourd'hui pour la faire ressortir
function formeAjd() {
    var el = document.getElementsByClassName('date');
    for (var i = 0; i < el.length; i++) {
        let parentNode = el[i].parentNode;
        if (el[i].innerHTML == "Aujourd'hui") {
            el[i].classList.add("text-uppercase");
            el[i].classList.add("fw-bold");
            parentNode.classList.add("bg-beige");
        } else {
            el[i].classList.remove("txt-bleuclair");
            el[i].classList.remove("text-uppercase");
            el[i].classList.remove("fw-bold");
            parentNode.classList.remove("bg-beige");
        }
    }
}
</script>

<template>
    <div class="container bg-gradient-bleufonce rounded-3">

        <div class="container bg-marronclair rounded-3">
            <SelecteurPatient @patientEvent="choixPatient" />
            <div class="container" v-if="data.id != ''">
                <div id="alerteMessage"></div>
                <div class="row">
                    <div class="col">
                        <SwitchColonne attribut="avancement" nomAffichage="Avancement" />
                    </div>
                    <div class="col">
                        <div class="form-check form-switch">
                            <label class="form-check-label" for="SwitchDebut">Date de Début</label>
                            <input class="form-check-input border-beige" type="checkbox" role="switch" id="SwitchDebut"
                                @click="cacherAfficherColonne('debut')" />
                        </div>
                    </div>
                    <div class="col">
                        <SwitchColonne attribut="fin" nomAffichage="Date de Fin" />
                    </div>
                    <div class="col">
                        <SwitchColonne attribut="info" nomAffichage="Moyen de Prise" />
                    </div>
                    <div class="col">
                        <SwitchColonne attribut="contreIndication" nomAffichage="Contre Indications" />
                    </div>
                    <div class="col">
                        <SwitchColonne attribut="posologie" nomAffichage="Posologie" />
                    </div>
                    <div class="col">
                        <SwitchColonne attribut="maladie" nomAffichage="Maladie" />
                    </div>
                </div>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover shadow table-sm align-middle bg-bleuclair">
                        <thead class="txt-violet">
                            <tr>
                                <th class="avancement">
                                    Avancement
                                    <i class="pointer arrow down" @click="choixTri(triAvancementCroissant)"></i>
                                </th>
                                <th>
                                    Prochaine Prise
                                    <i class="pointer arrow down" @click="choixTri(triDatePriseCroissant)"></i>
                                </th>
                                <th class="debut d-none">
                                    Date de création
                                    <i class="pointer arrow down" @click="choixTri(triDateDebutCroissant)"></i>
                                </th>
                                <th class="fin">
                                    Date de fin
                                    <i class="pointer arrow down" @click="choixTri(triDateFinCroissant)"></i>
                                </th>
                                <th>
                                    Médicament
                                    <i class="pointer arrow down" @click="choixTri(triNomMedicamentCroissant)"></i>
                                </th>
                                <th class="info">Moyen de prise</th>
                                <th class="contreIndication">Contre Indication</th>
                                <th class="posologie">Posologie</th>
                                <th class="maladie">
                                    Maladie
                                    <i class="pointer arrow down" @click="choixTri(triNomMaladieCroissant)"></i>
                                </th>
                                <th>Supprimer</th>
                            </tr>
                        </thead>
                        <tbody class="txt-violet">
                            <tr v-if="data.soigners.length != 0" v-for="soigner in data.soigners">
                                <td class="avancement">
                                    <div class="progress">
                                        <div class="progress-bar bg-bleufonce" role="progressbar"
                                            :style="`width: ${soigner.avancement}%`"
                                            :aria-valuenow="`${soigner.avancement}`" aria-valuemin="0"
                                            aria-valuemax="100">
                                        </div>
                                    </div>
                                </td>
                                <td class="date">{{ soigner.datePriseAffichage }}</td>
                                <td class="debut d-none">{{ soigner.dateCreationAffichage }}</td>
                                <td class="fin">{{ soigner.dateFinAffichage }}</td>
                                <td>{{ soigner.nomMedicament }}</td>
                                <td class="info">{{ soigner.infoPrises }}</td>
                                <td class="contreIndication">{{ soigner.contreIndications }}</td>
                                <td class="posologie">{{ soigner.doseParPrise }} {{ soigner.dose }} {{
                                    soigner.valFreq
                                }}
                                    fois /
                                    {{
                                        soigner.uniteFreq
                                    }} pendant {{ soigner.valDuree }} {{ soigner.uniteDuree }}</td>
                                <td class="maladie">{{ soigner.nomMaladie }}</td>
                                <td>
                                    <ConfirmationSupprimer @supprConfirmed="deleteFetch" :id="soigner.id" />
                                </td>
                            </tr>
                            <tr v-else>
                                <td colspan="10">
                                    Vous n'avez aucun médicament dans votre liste. Vous pouvez en ajouter dans la page
                                    <a href="/ajoutTraitement" class="text-decoration-none txt-bleufonce">Ajout
                                        Médicament</a>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
/* Crée la fléche de tri*/
.arrow {
    border: solid #5F4850;
    border-width: 0 3px 3px 0;
    display: inline-block;
    padding: 6px;
}

.up {
    transform: rotate(-135deg);
    -webkit-transform: rotate(-135deg);
}

.down {
    transform: rotate(45deg);
    -webkit-transform: rotate(45deg);
}


.bg-bleuclair {
    background-color: #2dafd6d2 !important;
}

.bg-gradient-bleufonce {
    background: -webkit-linear-gradient(to bottom, #2dafd6, #03619f);
    background: linear-gradient(to bottom, #03619f, #2dafd6);
}

.bg-gradient-marron {
    background: linear-gradient(to left, #ad6c4a, #caa391);
}

.bg-bleufonce {
    background-color: #03619F !important;
}

.bg-beige {
    background-color: #caa391 !important;
}

.bg-violet {
    background-color: #5F4850 !important;
}


.txt-violet {
    color: #5F4850;
}

.txt-bleufonce {
    color: #03619F;
}

div .form-check-input {
    border-color: #5f4850;
    background-color: #d09478;
}

div .form-check-input:checked {
    border-color: #5F4850;
    background-color: #5F4850;
}
</style>