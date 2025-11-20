<template>
  <div>
    <p class="mb-6 text-gray-600">Candidato preenche formulário de identificação e submete.</p>

    <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
      <div class="md:col-span-2">
        <label class="block text-sm font-medium text-gray-700 mb-1">Nome Completo</label>
        <input type="text" v-model="formData.nome" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500 outline-none">
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Sexo</label>
        <select v-model="formData.sexo" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 outline-none bg-white">
          <option value="">Selecione...</option>
          <option value="MASCULINO">Masculino</option>
          <option value="FEMININO">Feminino</option>
          <option value="OUTRO">Outro</option>
        </select>
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Data de Nascimento</label>
        <input type="date" v-model="formData.dataNascimento" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 focus:border-indigo-500 outline-none">
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Nacionalidade</label>
        <input type="text" v-model="formData.nacionalidade" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 outline-none">
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Profissão</label>
        <input type="text" v-model="formData.profissao" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 outline-none">
      </div>

      <div class="md:col-span-2">
        <label class="block text-sm font-medium text-gray-700 mb-1">Endereço Residencial</label>
        <input type="text" v-model="formData.enderecoResidencial" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 outline-none">
      </div>

      <div class="md:col-span-2">
        <label class="block text-sm font-medium text-gray-700 mb-1">Endereço Comercial</label>
        <input type="text" v-model="formData.enderecoComercial" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-indigo-500 outline-none">
      </div>
    </div>

    <div class="mt-8 flex justify-between">
      <button @click="$emit('back')" class="text-gray-500 hover:text-gray-700 font-medium py-2 px-4">
        Voltar
      </button>
      <button 
        @click="$emit('next')" 
        :disabled="!isValid" 
        :class="{'opacity-50 cursor-not-allowed': !isValid}" 
        class="bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-2 px-6 rounded-lg shadow transition duration-200"
      >
        Continuar
      </button>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps(['formData']);
defineEmits(['next', 'back']);

const isValid = computed(() => {
  return props.formData.nome.length > 3 && props.formData.nacionalidade && props.formData.enderecoResidencial;
});
</script>