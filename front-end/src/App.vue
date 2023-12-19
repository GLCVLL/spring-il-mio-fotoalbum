<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light d-flex align-item-center justify-content-center">
      <div>
        <img src="./assets/photoLogo.jpg" alt="Logo">
      </div>
    </nav>
  </header>
  <main>
    <div class="container mt-4">
      <photo-form v-if="creatingPhoto" @back="creatingPhoto = false" @created="photoCreated" />
      <div v-else>
        <photo-index v-if="photos && photoActive == null" :photos="photos" @open-photo="openPhoto" />
        <photo-show v-else-if="photoActive" :photo="photoActive" @close-photo="closePhoto" @delete-photo="deletePhoto" />
      </div>
    </div>
  </main>
</template>

<script setup>
// IMPORT LIBS
import { onMounted, ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PhotoIndex from './components/PhotoIndex.vue';
import PhotoShow from './components/PhotoShow.vue';
import PhotoForm from './components/PhotoForm.vue';

// DATA
const photos = ref(null);
const photoActive = ref(null);
const creatingPhoto = ref(false);

// FUNCTIONS
const openPhoto = (id) => {
  photos.value.forEach((photo) => {
    if (photo.id === id) {
      photoActive.value = photo;
    }
  });
};
const closePhoto = (update) => {
  photoActive.value = null;
  if (update) updatePhotos();
};
const photoCreated = () => {
  creatingPhoto.value = false;
  updatePhotos();
};
const updatePhotos = async () => {
  const data = await axios.get("http://localhost:8080/api/photos");
  photos.value = data.data;
};
const deletePhoto = () => {
  photoActive.value = null;
  updatePhotos();
};

// HOOKS
onMounted(updatePhotos);
</script>

<style scoped>
img {
  max-width: 100px;
  height: auto;
}
</style>
