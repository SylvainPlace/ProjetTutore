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
}

function fetchSoignersMedicament() {
    fetch("/api/PatientSoignerMedicament/" + data.id)
        .then((response) => response.json())
        .then((json) => {
            data.soigners = json;
            calculDates();
            data.soigners.sort(triNomMedicamentCroissant);
            formatDates();
            gestionPluriels();
        })
        .catch((error) => alert(error));
}

function gestionPluriels() {
    for (let s of data.soigners) {
        if (s.valDuree > 1 && s.uniteDuree != "Mois") {
            s.uniteDuree += "s";
        }
        if (s.doseParPrise > 1) {
            s.dose = "doses";
        } else {
            s.dose = "dose";
        }
    }
}

function calculDates() {
    for (let s of data.soigners) {
        calculDateFin(s, new Date(s.dateCreation));
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

function formatDates() {
    for (let s of data.soigners) {
        let dateD = new Date(s.dateCreation);
        s.dateCreation = dateD.toLocaleDateString();
        let dateF = new Date(s.dateFin);
        s.dateFin = dateF.toLocaleDateString();
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
function triNomMaladieDecroissant(a, b) {
    if (a.nomMaladie > b.nomMaladie) return -1;
    else if (a.nomMaladie == b.nomMaladie) return 0;
    else return 1;
}
function triNomMedicamentCroissant(a, b) {
    if (a.nomMedicament < b.nomMedicament) return -1;
    else if (a.nomMedicament == b.nomMedicament) return 0;
    else return 1;
}
function triNomMedicamentDecroissant(a, b) {
    if (a.nomMedicament > b.nomMedicament) return -1;
    else if (a.nomMedicament == b.nomMedicament) return 0;
    else return 1;
}
function triDateDebutCroissant(a, b) {
    if (a.dateCreation < b.dateCreation) return -1;
    else if (a.dateCreation == b.dateCreation) return 0;
    else return 1;
}
function triDateDebutDecroissant(a, b) {
    if (a.dateCreation > b.dateCreation) return 1;
    else if (a.dateCreation == b.dateCreation) return 0;
    else return -1;
}


</script>


<template>
    <div class="container pb-3">
        <SelecteurPatient @patientEvent="choixPatient" />
    </div>
    <div class="container"></div>
    <!--  <ListMedicament v-if="data.patientChoisi != ''"  :soignersPatient="data.patientChoisi + '/soigners'"  ref="liste"    /> -->
    <div class="container pb-3">
        <table class="table table-bordered table-hover shadow p-3 mb-5 bg-body rounded-3">
            <thead>
                <tr>
                    <th>Date de création</th>
                    <th>Durée</th>
                    <th>Date de fin</th>
                    <th>Médicament</th>
                    <th>Moyen de prise</th>
                    <th>Contre Indication</th>
                    <th>Posologie</th>
                    <th>Maladie</th>
                    <th>Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="soigner in data.soigners">
                    <td>{{ soigner.dateCreation }}</td>
                    <td>{{ soigner.valDuree }} {{ soigner.uniteDuree }}</td>
                    <td>{{ soigner.dateFin }}</td>
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