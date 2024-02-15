<template>
  <b-form v-if="show" @submit.prevent="submitForm">
    <b-form-group
      :state="nameState"
      label="Titulo de la obra"
      :class="{ 'is-invalid': formSubmitted && !nameState }"
      invalid-feedback="El título es requerido"
    >
      <b-form-input v-model="name"></b-form-input>
    </b-form-group>
    <b-form-group
      :state="authorState"
      label="Autor"
      :class="{ 'is-invalid': formSubmitted && !authorState }"
      invalid-feedback="El autor es requerido"
    >
      <b-form-input v-model="author"></b-form-input>
    </b-form-group>
    <b-form-group
      :state="publicationYearState"
      label="Año de publicación"
      :class="{ 'is-invalid': formSubmitted && !publicationYearState }"
      invalid-feedback="El año de publicación debe ser menor o igual al año actual"
    >
      <b-form-input v-model="publicationYear"></b-form-input>
    </b-form-group>
    <b-form-group
      :state="genreState"
      label="Género"
      :class="{ 'is-invalid': formSubmitted && !genreState }"
      invalid-feedback="El género es requerido"
    >
      <b-form-select v-model="selectedGenre">
        <option v-for="genre in genres" :key="genre.id" :value="genre.id">{{ genre.description }}</option>
      </b-form-select>
    </b-form-group>
    <b-button type="submit" class="mt-3" variant="primary">Registrar nuevo libro</b-button>
    <div v-if="loading" class="loader">Cargando...</div>
  </b-form>
</template>

<script>
import getGenres from '../services/GetGenres';
import showMessageSuccess from '../services/Alerts';
import addNewBook from '../services/AddNewBook';

export default {
  data() {
    return {
      name: null,
      author: null,
      publicationYear: null,
      genres: [],
      selectedGenre: null,
      show: true,
      loading: false,
      formSubmitted: false
    };
  },
  computed: {
    nameState() {
      return this.name ? true : false;
    },
    authorState() {
      return this.author ? true : false;
    },
    publicationYearState() {
      return this.publicationYear && parseInt(this.publicationYear) <= new Date().getFullYear() ? true : false;
    },
    genreState() {
      return this.selectedGenre ? true : false;
    }
  },
  methods: {
    async getGenres() {
      try {
        const data = await getGenres();
        this.genres = data.data;
      } catch(error) {
        showMessageSuccess("Error al obtener la información del servidor", "error");
      } 
    },
    async submitForm() {
      this.formSubmitted = true;

      if (!this.name || !this.author || !this.publicationYear || !this.selectedGenre) {
        showMessageSuccess("Todos los campos son requeridos", "error");
        return;
      }

      if (parseInt(this.publicationYear) > new Date().getFullYear()) {
        showMessageSuccess("El año de publicación no puede ser mayor al año actual", "error");
        return;
      }

      try {
        this.loading = true;

        const message = await addNewBook(this.name, this.author, this.publicationYear, this.selectedGenre);
        console.log(message);
        if (message.status === 201) {
          showMessageSuccess("Libro creado exitosamente", "success").then(() => window.location.reload());
        }
      } catch (error) {
        showMessageSuccess("Error al registrar nuevo producto", "error");
        console.error(error);
      } finally {
        this.loading = false;
      }
    }
  },
  mounted() {
    this.getGenres();
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
