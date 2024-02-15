<template>
    <b-form @submit.prevent="submitForm">
      <b-form-group
        :state="nameState"
        label="Título de la obra"
        :class="{ 'is-invalid': formSubmitted && !nameState }"
        invalid-feedback="El título es requerido"
      >
        <b-form-input v-model="bookToUpdate.name"></b-form-input>
      </b-form-group>
      <b-form-group
        :state="authorState"
        label="Autor"
        :class="{ 'is-invalid': formSubmitted && !authorState }"
        invalid-feedback="El autor es requerido"
      >
        <b-form-input v-model="bookToUpdate.author"></b-form-input>
      </b-form-group>
      <b-form-group
        :state="publicationYearState"
        label="Año de publicación"
        :class="{ 'is-invalid': formSubmitted && !publicationYearState }"
        invalid-feedback="El año de publicación debe ser menor o igual al año actual"
      >
        <b-form-input v-model="bookToUpdate.publicationYear"></b-form-input>
      </b-form-group>
      <b-form-group
        :state="genreState"
        label="Género"
        :class="{ 'is-invalid': formSubmitted && !genreState }"
        invalid-feedback="El género es requerido"
      >
        <b-form-select v-model="bookToUpdate.genre">
          <option v-for="genre in genres" :key="genre.id" :value="genre.id">{{ genre.description }}</option>
        </b-form-select>
      </b-form-group>
      <b-button type="submit" class="mt-3" variant="primary">Actualizar libro</b-button>
      <div v-if="loading" class="loader">Cargando...</div>
    </b-form>
  </template>
  
  <script>
  import getGenres from '../services/GetGenres';
  import showMessageSuccess from '../services/Alerts';
  import UpdateBook from '../services/UpdateBook';
  
  export default {
    props: {
      bookToUpdate: { // Definir una prop para recibir los datos del libro a actualizar
        type: Object,
        default: () => ({})
      }
    },
    data() {
      return {
        loading: false,
        formSubmitted: false
      };
    },
    computed: {
      nameState() {
        return this.bookToUpdate.name ? true : false;
      },
      authorState() {
        return this.bookToUpdate.author ? true : false;
      },
      publicationYearState() {
        return this.bookToUpdate.publicationYear && parseInt(this.bookToUpdate.publicationYear) <= new Date().getFullYear() ? true : false;
      },
      genreState() {
        return this.bookToUpdate.genre ? true : false;
      }
    },
    methods: {
      async submitForm() {
        this.formSubmitted = true;
  
        // Validaciones y lógica de envío del formulario
      }
    },
    mounted() {
      // Lógica para obtener los géneros
    }
  }
  </script>
  
  <style scoped>
  .is-invalid .form-control,
  .is-invalid .form-control-plaintext,
  .is-invalid .custom-select,
  .is-invalid .form-check-input {
    border-color: #dc3545;
  }
  
  .is-invalid .form-check-input:indeterminate ~ .custom-control-label::before,
  .is-invalid .form-check-input:checked ~ .custom-control-label::before {
    border-color: #dc3545;
    background-color: #dc3545;
  }
  
  .is-invalid .form-check-input:focus ~ .custom-control-label::before {
    box-shadow: 0 0 0 1px #fff, 0 0 0 0.2rem rgba(220, 53, 69, 0.25);
  }
  </style>
  