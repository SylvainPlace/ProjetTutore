<script setup>
import { reactive } from "vue";
import SelecteurPatient from './SelecteurPatient.vue';

const data = reactive({
    id: "1",
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
            gestionPluriels();
        })
        .catch((error) => alert(error));
}

function gestionPluriels() {
    for (let s of data.soigners) {
        if (s.valDuree > 1) {
            s.uniteDuree += "s";
        }
        if (s.valFreq > 1) {
            s.uniteFreq += "s";
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

</script>


<template>
    <div class="container pb-3">
        <SelecteurPatient @patientEvent="choixPatient" />
    </div>
    <!--  <ListMedicament v-if="data.patientChoisi != ''"  :soignersPatient="data.patientChoisi + '/soigners'"  ref="liste"    /> -->
    <div class="container pb-3">
        <table class="table table-bordered table-hover shadow p-3 mb-5 bg-body rounded-3">
            <thead>
                <tr>
                    <th>Médicament</th>
                    <th>Moyen de prise</th>
                    <th>Contre Indication</th>
                    <th>Durée</th>
                    <th>Fréquence</th>
                    <th>Dose Par Prise</th>
                    <th>Maladie</th>
                    <th>Supprimer</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="soigner in data.soigners">
                    <td>{{ soigner.nomMedicament }}</td>
                    <td>{{ soigner.infoPrises }}</td>
                    <td>{{ soigner.contreIndications }}</td>
                    <td>{{ soigner.valDuree }} {{ soigner.uniteDuree }}</td>
                    <td>{{ soigner.valFreq }} {{ soigner.uniteFreq }}</td>
                    <td>{{ soigner.doseParPrise }}</td>
                    <td>{{ soigner.nomMaladie }}</td>
                    <td>
                        <input
                            class="btn btn-danger"
                            type="button"
                            @click="deleteFetch(soigner.id)"
                            value="X"
                        />
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>