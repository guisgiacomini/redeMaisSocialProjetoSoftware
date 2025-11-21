<template>
  <div class="min-h-screen flex flex-col items-center justify-center p-4 bg-gray-50 text-gray-800 font-sans antialiased">
    
    <div class="mb-8 text-center">
      <h1 class="text-3xl font-bold text-indigo-700">Rede Mais Social</h1>
      <p class="text-gray-500 mt-2">Sistema de Gestão de Afiliações</p>
    </div>

    <div class="bg-white w-full max-w-2xl rounded-xl shadow-lg overflow-hidden border border-gray-100">
      
      <div class="bg-gray-200 h-2 w-full">
        <div class="bg-indigo-600 h-2 transition-all duration-500" :style="{ width: progresso + '%' }"></div>
      </div>

      <div class="p-8">
        
        <div class="mb-6" v-if="etapa <= 4">
          <h2 class="text-2xl font-semibold text-gray-800 mb-1">{{ tituloEtapa }}</h2>
          <p class="text-sm text-gray-500">Etapa {{ etapa }} de 4</p>
        </div>

        <div v-if="erroApi" class="mb-4 p-3 bg-red-100 text-red-700 rounded-lg text-sm">
            {{ erroApi }}
        </div>

        <transition name="fade" mode="out-in">
          
          <component 
            :is="componenteAtual" 
            :form-data="form" 
            :is-loading="isLoading"
            @next="handleProximaEtapa" 
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

import { 
  iniciarAfiliacao, 
  submeterDadosIdentificacao, 
  registrarPerfilCompleto, 
  registrarAceite 
} from './services/solicitarAfiliacao.js';

const idsControle = reactive({
  pedidoId: null,
  candidatoId: null
});

const etapa = ref(1);
const isLoading = ref(false);
const erroApi = ref('');

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

const handleProximaEtapa = async () => {
  erroApi.value = '';
  isLoading.value = true;

  try {
    switch (etapa.value) {
      case 1:
        // Chama API para iniciar afiliação
        const resp1 = await iniciarAfiliacao({
          email: form.email,
          documento: form.documento,
          tipoPessoa: form.tipoPessoa
        });
        // Supomos que o backend retorne um objeto com o ID do pedido (ex: { id: 1, ... })
        idsControle.pedidoId = resp1.id || resp1.pedidoId; 
        etapa.value++;
        break;

      case 2:
        // Envia dados de identificação vinculados ao pedidoId
        const resp2 = await submeterDadosIdentificacao({
          pedidoId: idsControle.pedidoId,
          nome: form.nome,
          sexo: form.sexo,
          dataNascimento: form.dataNascimento,
          nacionalidade: form.nacionalidade,
          enderecoResidencial: form.enderecoResidencial,
          enderecoComercial: form.enderecoComercial,
          email: form.email, // Redundante mas solicitado no comentário do service
          documento: form.documento,
          tipoPessoa: form.tipoPessoa
        });
        // Supomos que aqui retorne o ID do candidato criado
        idsControle.candidatoId = resp2.id || resp2.candidatoId; 
        etapa.value++;
        break;

      case 3:
        // Envia perfil. Transforma strings separadas por vírgula em Arrays
        await registrarPerfilCompleto({
          email: form.email,
          habilidades: form.habilidades.split(',').map(s => s.trim()).filter(s => s),
          interesses: form.interesses.split(',').map(s => s.trim()).filter(s => s)
        });
        etapa.value++;
        break;

      case 4:
        // Registra o aceite
        await registrarAceite(
          idsControle.candidatoId, 
          idsControle.pedidoId, 
          "1. O candidato compromete-se a respeitar o estatuto da ONG."
        );
        etapa.value++;
        break;

      case 5:
        // Etapa de validação de email - apenas avança para a tela de "Em análise"
        etapa.value++;
        break;

      default:
        etapa.value++;
    }
  } catch (error) {
    console.error("Erro no fluxo:", error);
    erroApi.value = error.message || "Ocorreu um erro de comunicação com o servidor.";
  } finally {
    isLoading.value = false;
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