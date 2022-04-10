<script setup>
import { onMounted, reactive, ref } from "vue";
onMounted(() => {
  getPatients();
});
const patients = reactive([]);

function getPatients(event) {
  let url = "/api/utilisateurs";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => response.json())
    .then((json) => {
      console.log(json);
      let results = json._embedded.utilisateurs;
      console.log(results);
      results.forEach((v) => patients.push(v));
      console.log(patients);
    })
    .catch((error) => alert(error));
}
</script><template>
  <input type="text" v-model="patientSelected" list="patients" />
  <datalist id="patients">
    <option v-for="patient in patients">{{ patient.nom }}</option>
  </datalist>
</template>
