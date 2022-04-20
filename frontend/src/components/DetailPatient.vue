<script setup>
import { reactive } from "vue";
import SelecteurPatient from './SelecteurPatient.vue';


const data = reactive({
    patientChoisiId: "",
    soigners: [],
});

function choixPatient(patient) {
    data.patientChoisiId = patient;
    fetchSoignersMedicament();

}
function fetchSoignersMedicament() {
    let url = "http://localhost:8383/api/InfirmierSoignerMedicament/";
    fetch(url + data.patientChoisiId)
        .then((response) => response.json())
        .then((json) => {
            data.soigners = json;
        })
        .catch((error) => alert(error));
}
</script>

<template>
    <div class="py-5">
        <h3>Médicaments du patient</h3>
        <SelecteurPatient @patientEvent="choixPatient" />
        <table v-if="data.patientChoisiId != ''"
            class="table table-bordered table-sm table-hover shadow p-3 mb-5 bg-body rounded-3">
            <thead class="head-table">
                <tr>
                    <th>Médicament</th>
                    <th>Maladie</th>
                </tr>
            </thead>
            <tbody>
                <tr v-if="data.soigners.length != 0" v-for="soigner in data.soigners">
                    <td>{{ soigner.nomMedicament }}</td>
                    <td>{{ soigner.nomMaladie }}</td>
                </tr>
                <tr v-else>
                    <td colspan="2">
                        Ce patient n'a aucun médicament dans sa liste.
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
