<template>
  <div>
    <p class="mb-4 text-gray-600">Candidato aceita as diretrizes estabelecidas no termo de aceite.</p>

    <div class="border border-gray-300 rounded-lg p-4 bg-gray-50 mb-4">
      <div class="h-48 overflow-y-auto text-sm text-gray-600 termo-scroll pr-2 text-justify">
        <p class="font-bold mb-2 text-gray-800">Termos e Condições da Rede Mais Social</p>
        <p class="mb-2">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        <p class="mb-2">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        <p class="mb-2">1. O candidato compromete-se a respeitar o estatuto da ONG.</p>
        <p class="mb-2">2. As informações prestadas são de inteira responsabilidade do declarante.</p>
        <p class="mb-2">3. A afiliação está sujeita a aprovação pelo conselho.</p>
        <p>Ao aceitar este termo, você concorda eletronicamente com todas as cláusulas acima dispostas.</p>
      </div>
    </div>

    <div class="flex items-center mb-6">
      <input 
        id="termo" 
        type="checkbox" 
        v-model="formData.aceiteTermos" 
        class="w-5 h-5 text-indigo-600 border-gray-300 rounded focus:ring-indigo-500 cursor-pointer"
      >
      <label for="termo" class="ml-2 block text-sm text-gray-700 cursor-pointer select-none">
        Li e aceito as diretrizes
      </label>
    </div>

    <div class="mt-8 flex justify-between">
      <button @click="$emit('back')" class="text-gray-500 hover:text-gray-700 font-medium py-2 px-4">
        Voltar
      </button>
      <button 
        @click="$emit('next')" 
        :disabled="!formData.aceiteTermos" 
        :class="{'opacity-50 cursor-not-allowed': !formData.aceiteTermos}" 
        class="bg-green-600 hover:bg-green-700 text-white font-medium py-2 px-6 rounded-lg shadow transition duration-200 flex items-center"
      >
        <i class="fas fa-check mr-2"></i>
        Aceitar e Finalizar
      </button>
    </div>
  </div>
</template>

<script setup>
defineProps(['formData']);
defineEmits(['next', 'back']);
</script>

<style scoped>
.termo-scroll::-webkit-scrollbar {
    width: 8px;
}
.termo-scroll::-webkit-scrollbar-track {
    background: #f1f1f1; 
}
.termo-scroll::-webkit-scrollbar-thumb {
    background: #888; 
    border-radius: 4px;
}
.termo-scroll::-webkit-scrollbar-thumb:hover {
    background: #555; 
}
</style>