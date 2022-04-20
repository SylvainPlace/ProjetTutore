<template>
  <div class="container espace">
    <div id="both">
      <AddPatient />
      <div id="detail">
      <DetailPatient />
      </div>
    </div>
    <div id="maladie">
    <DetailMaladie /></div>
  </div>

</template>

<script setup>
import AddPatient from "@/components/AddPatient.vue";
import DetailPatient from "@/components/DetailPatient.vue";
import DetailMaladie from "../components/DetailMaladie.vue";
</script>
<style >
h3{
  color: #5F4850;
}

 select.form-select {
    cursor:pointer;
    color: #5F4850;
    border-radius: 10px 100px/120px;
    background: -webkit-linear-gradient(to top, #f5bba0, #D09478);
    background: linear-gradient(to top, #D09478, #f5bba0)
}

select#maladieSelect{
  background-color: #5F4850;
  color: black;
  border-radius: 10px 100px/120px;
  background: -webkit-linear-gradient(to top, #f5bba0, #D09478);
  background: linear-gradient(to top, #D09478, #f5bba0);
}
div.py-5.table{
  border : black 3px solid ;
  width : 400px; 
}

thead{
  color : white;
  background: -webkit-linear-gradient(to top, #f5bba0, #D09478);
  background: linear-gradient(to top, #D09478, #f5bba0);
  
}
tbody{
  color : #5F4850;
}
#both{
  display : flex;
}

#detail{
  width: 1000px;
  position: relative;
  top: -38px;
}

#maladie{
  position : relative;
  top : 5px;
}
</style>