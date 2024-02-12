<template>
  <b-form v-if="show">
    <b-form-group
      label="Titulo de la obra"
    >
      <b-form-input></b-form-input>
    </b-form-group>
    <b-form-group
      label="Autor"
    >
      <b-form-input></b-form-input>
    </b-form-group>
    <b-form-group
      label="Año de publicación"
    >
      <b-form-input></b-form-input>
    </b-form-group>
    <b-form-group
      label="Portada"
    >
      <b-form-file 
        :state="Boolean(photo)"
        v-model="photo"
        accept=".jpg"
        placeholder="Ingresa una imagen"
      ></b-form-file>
    </b-form-group>
    <b-form-group
      label="Género"
    >
      <b-form-select v-model="selectedGenre">
        <option v-for="genre in genres" :key="genre.id" :value="genre.description">{{ genre.description }}</option>
      </b-form-select>
    </b-form-group>
    <b-button type="submit" class="mt-3" variant="primary">Registrar nuevo libro</b-button>
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
      photo: null,
      publicationYear: null,
      genres: [],
      selectedGenre: null, // Agrega una propiedad para almacenar el género seleccionado
      show: true,
      isLoading: false
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
        this.isLoading = true
        const message = await addNewBook(this.name, this.author,this.publicationYear,this.photo,this.selectedGenre.id, this.selectedGenre.description)
        console.log(message)
      }catch(error){
        showMessageSuccess("Error al registrar nuevo producto", "error")
        console.error(error)
      }finally{
        this.isLoading = false
      }
    }
  },
  mounted() {
    this.getGenres();
  }
}
</script>

<style scoped></style>
