<template>
    <div v-if="!photoUpdate" class="container mt-4">
        <div class="card">
            <img :src="photo.url" class="card-img-top" alt="Photo Image">
            <div class="card-body">
                <h5 class="card-title">
                    Photo: {{ photo.title }}
                </h5>
                <p class="card-text">{{ photo.description }}</p>

            </div>
        </div>
        <a class="btn btn-primary my-3" @click="$emit('closePhoto', photo != selectedPhoto)">Back to list</a>
    </div>
    <photo-form v-else :oldPhoto="selectedPhoto" @back="photoUpdate = false" @created="updatedPhoto" />
</template>

<script setup>
// IMPORT LIBS
import { ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PhotoForm from './PhotoForm.vue';

// PROPS
const props = defineProps({
    photo: {
        type: Object,
        required: true
    }
});

// DATA
const photoUpdate = ref(false);
const selectedPhoto = ref(props.photo);

// EMITS
const emits = defineEmits(["closePhoto", "deletePhoto"]);


</script>

<style scoped>
.link {
    cursor: pointer;
    text-decoration: underline;
}
</style>