<script setup>
import { reactive } from "vue";
import SelecteurPatient from './SelecteurPatient.vue';
import ListMedicament from './ListMedicament.vue';


const data = reactive({
    patientChoisi: "",
    soigners: [],
    medicaments: [],
    maladies: [],
});

function choixPatient(patient) {
    data.patientChoisi = patient;
    console.log(data.patientChoisi);
    fetchSoignersMedicament();

}
function fetchSoignersMedicament() {
    let url = "http://localhost:8383/api/soignerMedicament/";
    fetch(url + data.patientChoisi)
        .then((response) => response.json())
        .then((json) => {
            console.log("Yes");
            console.log(json[0]);
            data.soigners = json;
            console.log(data.soigners);
        })
        .catch((error) => alert(error));
}


</script>

<template>
    <SelecteurPatient @patientEvent="choixPatient" />
    <!--  <ListMedicament v-if="data.patientChoisi != ''"  :soignersPatient="data.patientChoisi + '/soigners'"  ref="liste"    /> -->
    <div class>
        <table class="table table-bordered table-sm table-hover shadow p-3 mb-5 bg-body rounded-3">
            <thead>
                <tr>
                    <th>Médicament</th>
                    <th>Maladie</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="soigner in data.soigners">
                    <td>{{ soigner.nomMedicament }}</td>
                    <td>{{ soigner.nomMaladie }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
