<template>
  <div class="min-h-screen flex flex-col items-center justify-center p-4 bg-gray-50 text-gray-800 font-sans antialiased">
    
    <!-- Cabeçalho -->
    <div class="mb-8 text-center">
      <h1 class="text-3xl font-bold text-indigo-700">Rede Mais Social</h1>
      <p class="text-gray-500 mt-2">Sistema de Gestão de Afiliações</p>
    </div>

    <!-- Card Principal -->
    <div class="bg-white w-full max-w-2xl rounded-xl shadow-lg overflow-hidden border border-gray-100">
      
      <!-- Barra de Progresso -->
      <div class="bg-gray-200 h-2 w-full">
        <div class="bg-indigo-600 h-2 transition-all duration-500" :style="{ width: progresso + '%' }"></div>
      </div>

      <div class="p-8">
        
        <!-- Título da Etapa -->
        <div class="mb-6" v-if="etapa <= 4">
          <h2 class="text-2xl font-semibold text-gray-800 mb-1">{{ tituloEtapa }}</h2>
          <p class="text-sm text-gray-500">Etapa {{ etapa }} de 4</p>
        </div>

        <transition name="fade" mode="out-in">
          
          <!-- Renderização Dinâmica dos Componentes -->
          <component 
            :is="componenteAtual" 
            :form-data="form" 
            @next="proximaEtapa" 
            @back="etapa--"
          />

        </transition>

      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed } from 'vue';

// Importação dos componentes
import Etapa1 from './components/Etapa1.vue';
import Etapa2 from './components/Etapa2.vue';
import Etapa3 from './components/Etapa3.vue';
import Etapa4 from './components/Etapa4.vue';
import Etapa5 from './components/Etapa5.vue';
import Etapa6 from './components/Etapa6.vue';

const etapa = ref(1);

// Estado global do formulário compartilhado
const form = reactive({
  email: '',
  tipoPessoa: 'FISICA',
  documento: '',
  nome: '',
  sexo: '',
  dataNascimento: '',
  nacionalidade: '',
  profissao: '',
  enderecoResidencial: '',
  enderecoComercial: '',
  habilidades: '',
  interesses: '',
  aceiteTermos: false
});

// Lógica de Navegação
const proximaEtapa = () => {
  if (etapa.value < 6) {
    etapa.value++;
    if (etapa.value === 5) {
      console.log("Enviando dados para API:", JSON.parse(JSON.stringify(form)));
    }
  }
};

// Computed Properties
const componenteAtual = computed(() => {
  switch (etapa.value) {
    case 1: return Etapa1;
    case 2: return Etapa2;
    case 3: return Etapa3;
    case 4: return Etapa4;
    case 5: return Etapa5;
    case 6: return Etapa6;
    default: return Etapa1;
  }
});

const tituloEtapa = computed(() => {
  switch(etapa.value) {
    case 1: return 'Solicitar Afiliação';
    case 2: return 'Identificação';
    case 3: return 'Formulário de Interesses';
    case 4: return 'Termo de Compromisso';
    default: return '';
  }
});

const progresso = computed(() => {
  if (etapa.value >= 5) return 100;
  return ((etapa.value - 1) / 3) * 100;
});
</script>

<style>
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s ease;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
}
</style>