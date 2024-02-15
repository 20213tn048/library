<template>
    <div>
        <div v-if="loading" class="loader">Cargando...</div>
        <div v-if="!loading">
            <b-container fluid>
                <b-card-group deck>
                    <b-row>
                        <b-col cols="12" md="6" lg="4" v-for="(book, index) in books.data" :key="index" class="mb-4">
                            <b-card :image-src="book.photo" img-alt="book.name" img-top :title="book.name" class="mt-2">
                                <b-card-text>
                                    {{ book.author }}
                                </b-card-text>
                                <template #footer>
                                    <small class="text-muted">Año de publicación: {{ book.publicationYear }}</small>
                                </template>
                                <b-dropdown variant="primary" text="Opciones" class="mt-2">
                                    <b-dropdown-item @click="openUpdateModal">Actualizar</b-dropdown-item>
                                    <b-dropdown-item @click="openDeleteModal(book.id)">Borrar</b-dropdown-item>
                                </b-dropdown>
                            </b-card>
                        </b-col>
                    </b-row>
                </b-card-group>
            </b-container>
        </div>

        <!-- Modal de Actualizar -->
        <b-modal v-model="showUpdateModal" title="Actualizar Libro">
            <!-- Contenido del modal de Actualizar -->
            <!-- Aquí puedes agregar el formulario o contenido que desees para la actualización -->
            <UpdateBook/>
        </b-modal>

        <!-- Modal de Eliminar -->
        <b-modal v-model="showDeleteModal" title="Eliminar Libro" hide-footer hide-header centered>
            <h2 class="mt-2 text-center">¿Estás seguro de borrar el libro?</h2>
            <p class="mt-4 text-center">Los datos se eliminarán permanentemente.</p>
            <div class="mt-4 d-flex justify-content-center">
                <b-button variant="danger" class="mr-2" @click="deleteBook">Si, Borrar</b-button>
                <b-button variant="secondary" class="ml-2" @click="closeDeleteModal">Cancelar</b-button>
            </div>
        </b-modal>
    </div>
</template>

<script>
import getBooks from '../services/GetBooks.js';
import showMessageSuccess from '../services/Alerts';
import DeleteBook from '../services/DeleteBook';
import UpdateBook from './UpdateBook.vue';

export default {
    data() {
        return {
            loading: true,
            books: [],
            showUpdateModal: false,
            showDeleteModal: false,
            bookIdToDelete: null
        }
    },
    methods: {
        async getBook() {
            try {
                this.books = await getBooks();
                showMessageSuccess("Carga completa de datos", "success");
            } catch (error) {
                showMessageSuccess("Error al obtener la información del servidor", "error");
            } finally {
                this.loading = false;
            }
        },
        openUpdateModal() {
            this.showUpdateModal = true;
        },
        openDeleteModal(id) {
            this.showDeleteModal = true;
            this.bookIdToDelete = id;
        },
        closeDeleteModal() {
            this.showDeleteModal = false;
        },
        async deleteBook() {
            try {
                await DeleteBook(this.bookIdToDelete);
                showMessageSuccess("Libro eliminado exitosamente", "success");
                // Actualizar la lista de libros u otras acciones necesarias después de eliminar
                this.getBook();
            } catch (error) {
                showMessageSuccess("Error al eliminar el libro", "error");
            } finally {
                this.closeDeleteModal();
            }
        }
    },
    mounted() {
        this.getBook();
    },
    components: {
        UpdateBook
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
