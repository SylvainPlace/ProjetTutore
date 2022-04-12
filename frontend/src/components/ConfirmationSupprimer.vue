<template>
    <button
        type="button"
        class="btn btn-danger"
        data-bs-toggle="modal"
        :data-bs-target="`#modal${id}`"
    >X</button>

    <!-- Modal -->
    <div
        class="modal fade"
        :id="`modal${id}`"
        tabindex="-1"
        aria-labelledby="Confirmation Supprimer"
        aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalSupprimerLabel">Confirmation de suppression</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                    ></button>
                </div>
                <div class="modal-body">Êtes vous sur de vouloir supprimer cet élément ?</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button
                        type="button"
                        class="btn btn-outline-danger"
                        @click="$emit('supprConfirmed', id)"
                        data-bs-dismiss="modal"
                    >Supprimer</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
defineProps(["id"]);
const emit = defineEmits(['supprConfirmed']);

</script>
