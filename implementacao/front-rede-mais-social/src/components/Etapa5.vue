<template>
  <div class="text-center py-8">
    <div class="w-20 h-20 bg-yellow-100 text-yellow-600 rounded-full flex items-center justify-center mx-auto mb-6 text-3xl">
      <i class="fas fa-envelope"></i>
    </div>
    
    <h2 class="text-2xl font-bold text-gray-800 mb-2">Validação Pendente</h2>
    <p class="text-gray-600 mb-6 max-w-md mx-auto">
      Um e-mail de validação foi enviado para <strong>{{ formData.email }}</strong>. 
      Por favor, acesse seu e-mail e clique no link para continuar o processo.
    </p>

    <button @click="$emit('next')" class="bg-gray-800 hover:bg-gray-900 text-white font-medium py-2 px-10 rounded-lg shadow transition duration-200">
      OK
    </button>
  </div>
</template>

<script setup>
defineProps(['formData']);
defineEmits(['next']);
</script>