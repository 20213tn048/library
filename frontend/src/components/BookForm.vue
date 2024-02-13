<template>
  <b-form v-if="show" @submit="addNewBook">
    <b-form-group
      label="Titulo de la obra"
    >
      <b-form-input v-model="name"></b-form-input>
    </b-form-group>
    <b-form-group
      label="Autor"
    >
      <b-form-input v-model="author"></b-form-input>
    </b-form-group>
    <b-form-group
      label="Año de publicación"
    >
      <b-form-input v-model="publicationYear"></b-form-input>
    </b-form-group>
    <b-form-group
      label="Género"
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
      selectedGenre: null, // Agrega una propiedad para almacenar el género seleccionado
      show: true,
      loading: false
    };
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
    async addNewBook(event){
      event.preventDefault()

      try{
        this.loading = true
  
        const message = await addNewBook(this.name, this.author,this.publicationYear,this.selectedGenre)
        console.log(message)
        if(message.status === 201){
          showMessageSuccess("Libro creado exitosamente", "success").then(()=> window.location.reload())
        }
      }catch(error){
        showMessageSuccess("Error al registrar nuevo producto", "error")
        console.error(error)
      }finally{
        this.loading = false
      }
    }
  },
  mounted() {
    this.getGenres();
  }
}
</script>

<style scoped>

.loader {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}
</style>
