<template>
  <div>
    <!-- Loader -->
    <div v-if="loading" class="loader">Cargando...</div>

    <!-- Navbar -->
    <b-navbar toggleable="sm" type="dark" variant="dark">
      <b-navbar-toggle target="nav-text-collapse"></b-navbar-toggle>
      <b-navbar-brand>Camargo's Library</b-navbar-brand>
      <b-collapse id="nav-text-collapse" is-nav>
        <b-navbar-nav>
          <b-button v-b-modal.modal-1 variant="danger">Agregar nuevo libro</b-button>
          <b-modal id="modal-1" title="Registrar nuevo libro" hide-footer>
            <BookForm></BookForm>
          </b-modal>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>

    <!-- Lista de libros -->
    <div v-if="!loading">
     <b-container fluid>
      <b-card-group deck>
        <b-row cols="3">
          <b-card v-for="(book, index) in books.data" :key="index" :image-src="book.photo" img-alt="book.name" img-top :title="book.name" class="mt-4">
            <b-card-text>
              {{ book.author }}
            </b-card-text>
            <template #footer>
              <small class="text-muted">Año de publicación: {{ book.publicationYear }}</small>
            </template>
          </b-card>
        </b-row>
      </b-card-group>
     </b-container>
    </div>
  </div>
</template>

<script>
import BookForm from './components/BookForm.vue';
import getBooks from './services/GetBooks.js';
import showMessageSuccess from './services/Alerts.js';

export default {
  components: {
    BookForm,
  },
  data() {
    return {
      loading: true,
      books: []
    };
  },
  methods: {
    async getBook() {
      try {
        const data = await getBooks();
        this.books = data;
        showMessageSuccess("Carga completa de datos", "success");
      } catch (error) {
        console.error("Error getting books: ", error);
        showMessageSuccess("Error al obtener la información del servidor", "error");
      } finally {
        this.loading = false;
      }
    },
  },
  mounted() {
    this.getBook();
  },
};
</script>

<style scoped>
/* Estilos para el loader */
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
