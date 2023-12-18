<template>
    <div class="container mt-4">
        <!-- Barra di ricerca -->
        <input type="text" class="form-control mb-4" placeholder="Cerca foto..." v-model="searchBar">

        <h1 class="mb-3">Galleria Fotografica</h1>
        <div class="row">
            <div class="col-md-4 mb-2" v-for="photo in filteredPhotos" :key="photo.id">
                <div class="card" @click="$emit('openPhoto', photo.id)">
                    <img :src="photo.url" class="card-img-top" alt="Immagine della foto">
                    <div class="card-body">
                        <p class="card-title">{{ photo.title }}</p>
                    </div>
                </div>
            </div>
        </div>

        <contact-form></contact-form>
    </div>
</template>

<script setup>
// IMPORT 
import { defineProps, ref, computed } from 'vue';
import ContactForm from './ContactForm.vue';

// EMITS
const emits = defineEmits(["openPhoto"]);

// PROPS
const props = defineProps({
    photos: {
        type: Array,
        required: true
    }
});

// Data
const searchBar = ref('');

// Computed property per filtrare le foto
const filteredPhotos = computed(() => {
    return props.photos.filter(photo =>
        photo.title.toLowerCase().includes(searchBar.value.toLowerCase())
    );
});
</script>

<style scoped>
.list-elem {
    cursor: pointer;
    list-style-type: none;
}

.card-img-top {
    width: 100%;
    height: auto;
    object-fit: cover;
}
</style>