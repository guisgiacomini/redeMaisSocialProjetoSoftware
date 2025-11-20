<template>
  <div>
    <p class="mb-6 text-gray-600">Informe suas habilidades e interesses para construirmos seu Perfil.</p>

    <div class="space-y-6">
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Habilidades</label>
        <p class="text-xs text-gray-400 mb-2">Separe por vírgulas (ex: Java, Spring Boot, Liderança)</p>
        <textarea 
          v-model="formData.habilidades" 
          rows="3" 
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500 outline-none resize-none"
        ></textarea>
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Áreas de Interesse</label>
        <p class="text-xs text-gray-400 mb-2">Separe por vírgulas (ex: Meio Ambiente, Educação, Saúde)</p>
        <textarea 
          v-model="formData.interesses" 
          rows="3" 
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500 outline-none resize-none"
        ></textarea>
      </div>
    </div>

    <div class="mt-8 flex justify-between">
      <button @click="$emit('back')" class="text-gray-500 hover:text-gray-700 font-medium py-2 px-4">
        Voltar
      </button>
      <button 
        @click="$emit('next')" 
        class="bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-2 px-6 rounded-lg shadow transition duration-200"
      >
        Submeter
      </button>
    </div>
  </div>
</template>

<script setup>
defineProps(['formData']);
defineEmits(['next', 'back']);
</script>