<template>
    <form @submit.prevent="submit" class="container mt-4">
        <div class="row mb-3">
            <label for="title" class="col-sm-2 col-form-label">Title</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="title" id="title" v-model="newPhoto.title">
            </div>
        </div>

        <div class="row mb-3">
            <label for="url" class="col-sm-2 col-form-label">URL</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="url" id="url" v-model="newPhoto.url">
            </div>
        </div>

        <div class="row mb-3">
            <label for="description" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="description" id="description" v-model="newPhoto.description">
            </div>
        </div>

        <div class="row">
            <div class="col-sm-10 offset-sm-2">
                <button type="submit" class="btn btn-primary me-2">
                    {{ props.oldPhoto == null ? "Add" : "Update" }}
                </button>
                <button type="button" class="btn btn-secondary" @click="$emit('back')">
                    Back
                </button>
            </div>
        </div>
    </form>
</template>

<script setup>
// IMPORT LIBS
import { defineEmits, ref } from 'vue';
import axios from 'axios';

// PROPS
const props = defineProps({
    oldPhoto: {
        type: Object,
        required: false,
        default: null
    }
});

// DATA
const newPhoto = ref({
    title: "",
    url: "",
    description: ""
});
if (props.oldPhoto != null) {

    newPhoto.value = {
        title: props.oldPhoto.title,
        url: props.oldPhoto.url,
        description: props.oldPhoto.description
    };
}

// EMITS
const emits = defineEmits(["back", "created"]);

// FUNCTIONS
const submit = async () => {
    if (props.oldPhoto == null) {

        const data = await axios.post(
            "http://localhost:8080/api/photos",
            newPhoto.value
        );

        console.log("data", data);

        emits("created");

        return;
    }

    const data = await axios.put(
        `http://localhost:8080/api/photos/${props.oldPhoto.id}`,
        newPhoto.value
    );

    console.log("data", data);

    emits("created", newPhoto.value);
}
</script>