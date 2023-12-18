<template>
    <div class="container mt-4">
        <h2 class="my-4">Contattaci</h2>
        <form @submit.prevent="submitContactForm" class="needs-validation" novalidate>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input v-model="contactMessage.email" type="email" class="form-control" id="email"
                    placeholder="Inserisci la tua email" required>
                <div class="invalid-feedback">
                    Per favore inserisci un indirizzo email valido.
                </div>
            </div>

            <div class="mb-3">
                <label for="message" class="form-label">Messaggio</label>
                <textarea v-model="contactMessage.message" class="form-control" id="message" rows="4"
                    placeholder="Inserisci il tuo messaggio" required></textarea>
                <div class="invalid-feedback">
                    Per favore inserisci un messaggio.
                </div>
            </div>

            <button type="submit" class="btn btn-primary" :disabled="formStatus.loading">Invia Messaggio</button>
        </form>

        <!-- Feedback Messages -->
        <div v-if="formStatus.loading" class="alert alert-info mt-3">Invio in corso...</div>
        <div v-if="formStatus.success" class="alert alert-success mt-3">Messaggio inviato con successo.</div>
        <div v-if="formStatus.error" class="alert alert-danger mt-3">Errore: {{ formStatus.errorMessage }}</div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

// Data
const contactMessage = ref({
    email: '',
    message: ''
});
const formStatus = ref({
    loading: false,
    success: false,
    error: false,
    errorMessage: ''
});

// Methods
async function submitContactForm() {
    formStatus.value.loading = true;
    formStatus.value.success = false;
    formStatus.value.error = false;
    formStatus.value.errorMessage = '';

    try {
        await axios.post("http://localhost:8080/api/contact", contactMessage.value);
        formStatus.value.success = true;
        contactMessage.value.email = '';
        contactMessage.value.message = '';
    } catch (error) {
        formStatus.value.error = true;
        formStatus.value.errorMessage = error.message || "Si è verificato un errore durante l'invio del messaggio";
        console.error("There was an error submitting the form", error);
    } finally {
        formStatus.value.loading = false;
    }
}
</script>