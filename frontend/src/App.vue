<template>
  <div>
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
    <BookCards/>
    
  </div>
</template>

<script>
import BookForm from './components/BookForm.vue';
import BookCards from './components/BookCards.vue';
export default {
  components: {
    BookForm,
    BookCards
  }
};
</script>

<style scoped>
</style>
