<script setup>
import { reactive } from "vue";
import SelecteurPatient from './SelecteurPatient.vue';
import ConfirmationSupprimer from './ConfirmationSupprimer.vue';

const data = reactive({
    id: "",
    soigners: [],
});

function choixPatient(patient) {
    data.id = patient;
    fetchSoignersMedicament();
    formeAjd()
}

function fetchSoignersMedicament() {
    fetch("/api/PatientSoignerMedicament/" + data.id)
        .then((response) => response.json())
        .then((json) => {
            data.soigners = json;
            calculDates();
            data.soigners.sort(triDatePriseCroissant);
            gestionPluriels();
        })
        .catch((error) => alert(error));
}

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

function calculDates() {
    for (let s of data.soigners) {
        calculDateFin(s, new Date(s.dateCreation));
        calculDateProchain(s, new Date(s.dateCreation));
        calculDiffDates();
        formatDates();
    }
}

function calculDiffDates() {
    for (let s of data.soigners) {
        let reste = new Date(s.dateFin) - new Date();
        let consomme = new Date() - new Date(s.dateCreation);
        s.avancement = Math.round((consomme / (reste + consomme)) * 100);
    }
}

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

function choixTri(nom) {
    let t = [...data.soigners];
    data.soigners.sort(nom);
    let v = [...data.soigners];
    if (JSON.stringify(t) == JSON.stringify(v)) {
        data.soigners.reverse();
    }
}

function formeAjd() {
    console.log("FormeAjd");
    let trs = document.getElementsByClassName("cellulePrise");
    console.log(trs);
    console.log(trs[1]);
}

</script>


<template>
    <SelecteurPatient @patientEvent="choixPatient" />
    <div class="container rounded-pill bg-info px-5">
        <table class="table table-bordered table-hover shadow p-3 mb-5 bg-body rounded-3">
            <thead>
                <tr>
                    <th>
                        Avancement
                        <i
                            class="pointer arrow down"
                            @click="choixTri(triAvancementCroissant)"
                        ></i>
                    </th>
                    <th>
                        Prochaine Prise
                        <i
                            class="pointer arrow down"
                            @click="choixTri(triDatePriseCroissant)"
                        ></i>
                    </th>
                    <th>
                        Date de création
                        <i
                            class="pointer arrow down"
                            @click="choixTri(triDateDebutCroissant)"
                        ></i>
                    </th>
                    <th>
                        Date de fin
                        <i
                            class="pointer arrow down"
                            @click="choixTri(triDateFinCroissant)"
                        ></i>
                    </th>
                    <th>
                        Médicament
                        <i
                            class="pointer arrow down"
                            @click="choixTri(triNomMedicamentCroissant)"
                        ></i>
                    </th>
                    <th>Moyen de prise</th>
                    <th>Contre Indication</th>
                    <th>Posologie</th>
                    <th>
                        Maladie
                        <i
                            class="pointer arrow down"
                            @click="choixTri(triNomMaladieCroissant)"
                        ></i>
                    </th>
                    <th>Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="soigner in data.soigners">
                    <td>
                        <div class="progress">
                            <div
                                class="progress-bar"
                                role="progressbar"
                                :style="`width: ${soigner.avancement}%`"
                                :aria-valuenow="`${soigner.avancement}`"
                                aria-valuemin="0"
                                aria-valuemax="100"
                            ></div>
                        </div>
                    </td>
                    <td class="cellulePrise">{{ soigner.datePriseAffichage }}</td>
                    <td>{{ soigner.dateCreationAffichage }}</td>
                    <td>{{ soigner.dateFinAffichage }}</td>
                    <td>{{ soigner.nomMedicament }}</td>
                    <td>{{ soigner.infoPrises }}</td>
                    <td>{{ soigner.contreIndications }}</td>
                    <td>{{ soigner.doseParPrise }} {{ soigner.dose }} {{ soigner.valFreq }} fois / {{ soigner.uniteFreq }} pendant {{ soigner.valDuree }} {{ soigner.uniteDuree }}</td>
                    <td>{{ soigner.nomMaladie }}</td>
                    <td>
                        <ConfirmationSupprimer @supprConfirmed="deleteFetch" :id="soigner.id" />
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<style>
.arrow {
    border: solid black;
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
</style>