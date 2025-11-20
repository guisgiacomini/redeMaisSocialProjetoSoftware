 <template>
  <div>
    <p class="mb-6 text-gray-600">Candidato informa seu email e CPF (CNPJ, se for pessoa jurídica) e solicita afiliação.</p>
    
    <div class="space-y-4">
      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">E-mail</label>
        <input 
          type="email" 
          v-model="formData.email" 
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 outline-none transition" 
          placeholder="seu@email.com"
        >
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">Tipo de Pessoa</label>
        <select 
          v-model="formData.tipoPessoa" 
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 outline-none bg-white"
        >
          <option value="FISICA">Pessoa Física</option>
          <option value="JURIDICA">Pessoa Jurídica</option>
        </select>
      </div>

      <div>
        <label class="block text-sm font-medium text-gray-700 mb-1">
          {{ formData.tipoPessoa === 'FISICA' ? 'CPF' : 'CNPJ' }}
        </label>
        <input 
          type="text" 
          v-model="formData.documento" 
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 outline-none transition" 
          :placeholder="formData.tipoPessoa === 'FISICA' ? '000.000.000-00' : '00.000.000/0000-00'"
        >
      </div>
    </div>

    <div class="mt-8 flex justify-end">
      <button 
        @click="$emit('next')" 
        :disabled="!isValid" 
        :class="{'opacity-50 cursor-not-allowed': !isValid}" 
        class="bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-2 px-6 rounded-lg shadow transition duration-200 flex items-center"
      >
        Solicitar Afiliação
        <i class="fas fa-arrow-right ml-2"></i>
      </button>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps(['formData']);
const emit = defineEmits(['next']);

const isValid = computed(() => {
  return props.formData.email.includes('@') && props.formData.documento.length >= 11;
});
</script>