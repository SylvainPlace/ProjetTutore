<template>

  <div class="container">
 
    <form
      @submit.prevent="
        handlerAddPatient(
          nom,
          prenom,
          adresse_mail,
          mdp,
          date_de_naiss,
          categorie
        )
      "
      class="row g-3"
      id="form"
    > <h3 id="utilisateur">Ajouter un utilisateur :</h3>
      <div class="col-md-6">
        <label for="Nom" class="form-label">Nom :</label>
        <input
          type="text"
          v-model="nom"
          placeholder="Dupont"
          class="form-control"
          id="Nom"
          required
        />
      </div>
      <div class="col-md-6">
        <label for="Prenom" class="form-label" form-floating>Prenom :</label>
        <input
          type="text"
          v-model="prenom"
          placeholder="Jean"
          class="form-control"
          id="Prenom"
          required
        />
      </div>
      <div class="col-md-4">
        <label for="mail" class="form-label">Adresse e-mail :</label>
        <input
          type="email"
          v-model="adresse_mail"
          placeholder="email@example.com"
          class="form-control"
          id="mail"
          required
        />
      </div>
      <div class="col-md-5">
        <label for="mdp" class="form-label">Mot de passe :</label>
        <input
          type="password"
          v-model="mdp"
          placeholder="********"
          class="form-control"
          id="mdp"
          @input="passwordStrengh()"
          required
        />
        <span id="msgPasswd"></span>
      </div>
      <div class="col-md-2">
        <label for="ddn" class="form-label">Date de naissance :</label>
        <input
          type="date"
          v-model="date_de_naiss"
          placeholder="01/01/2000"
          class="form-control"
          id="ddn"
          required
        />
      </div>
      <div class="col-md-7">
        <label for="type" class="form-label">Categorie d'utilisateur :</label>
        <select class="form-select" id="type" v-model="categorie" required>
          <option selected disabled>Type</option>
          <option v-for="category of listeC">{{ category }}</option>
        </select>
      </div>
      <div class="col-12">
        <input id="ajouter"
          type="submit"
          value="Ajouter un utilisateur"
          class="btn btn-primary"
        />
      </div>
      <div id="texte"></div>
    </form>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
const listeC = reactive([]);
onMounted(() => {
  getCategorie();
});

function getCategorie(event) {
  let url = "/api/allCategorie";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
      dataJSON.forEach((v) => listeC.push(v));
    })
    .catch((error) => {
    });
}

function handlerAddPatient(
  nom,
  prenom,
  adresse_mail,
  mdp,
  date_de_naiss,
  categorie
) {
  let url = "/api/utilisateurs";
  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify({
      adresse_mail: adresse_mail,
      categorie: categorie,
      date_de_naiss: date_de_naiss,
      mdp: mdp,
      nom: nom,
      prenom: prenom,
    }),
  };
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
      document.getElementById("texte").innerHTML =
        categorie +
        " " +
        nom +
        " " +
        prenom +
        " a bien été ajouté à la base de données";
      document.getElementById("form").reset();
    });
}

function passwordStrengh(event) {
  let entree = document.getElementById("mdp").value;
  let msg = document.getElementById("msgPasswd");
  if (entree.length < 3) {
    msg.textContent = "Mot de passe faible";
    msg.style.color = "red";
  } else {
    if (entree.length < 6) {
      msg.textContent = "Mot de passe moyen";
      msg.style.color = "yellow";
    } else {
      msg.textContent = "Mot de passe fort";
      msg.style.color = "green";
    }
  }
}
</script>
<style scoped>
  
  #form{
position : relative ; 
  left : 13px;
  top : 8px;
  height : 433px;
  width : 483px;
  border: 3px solid ;
  background: -webkit-linear-gradient(to left, #D09478, #f5bba0);
    background: linear-gradient(to left, #f5bba0, #D09478);
 
   color : white;
   text-align : center;
    border-radius: 10px 100px / 120px;
  }

  #Nom{
border-radius : 10px;
}   
        
#Prenom{
border-radius : 10px;
}
#mail{
border-radius : 10px;
}

   .col-md-6{
     position : relative;
     top : -27px;
     left: 2px;
     width : 234px;
      /*tout ce qui est  prenom et nom*/
   }
       
    #mdp{
border-radius : 10px;
    }
  
   .col-md-2{  
      position : relative;
      top : -35px;
      left: 7px;
      width : 165px;
       /*tout ce qui est date*/} 
  .col-md-5{  
      position : relative;
      top : -35px;
      left: 7px;
      } 
  #ddn{
border-radius : 10px;
  }
  .col-md-4{
 position : relative;
     top : -35px;
     left: 2px;
     width: 275px ;
  }  
   .col-md-7{
 position : relative;
     top : -35px;
     left: 12px;
     width: 275px ;
  }  
  #type{
    border-radius : 10px;
    	background-color: white;
	border: #B48B75  1px solid;
  }      
 #ajouter{
    /*c'est le bouton ajouter*/
    position : relative;
    	background-color: white;
	border: #B48B75  1px solid;
  border-radius : 10px;
  color : black;
  cursor : pointer;
  top : -36px;
  left : 18px;
  }

  #texte{
    color : rgb(70, 15, 15);
    width : 422px;
    top : -41px;
    left: 57px;
    position : relative;
  }
  </style>