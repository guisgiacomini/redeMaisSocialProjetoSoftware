<template>
  <div class="text-center py-8">
    <div class="w-20 h-20 bg-blue-100 text-blue-600 rounded-full flex items-center justify-center mx-auto mb-6 text-3xl">
      <i class="fas fa-hourglass-half"></i>
    </div>

    <h2 class="text-2xl font-bold text-gray-800 mb-2">Afiliação em Análise</h2>
    
    <div class="bg-blue-50 border border-blue-100 rounded-lg p-4 text-left max-w-md mx-auto mb-6">
      <h3 class="font-semibold text-blue-800 mb-1">Solicitação Recebida</h3>
      <p class="text-sm text-blue-700">
        Seu cadastro foi validado com sucesso e agora está aguardando a aprovação da equipe da Rede Mais Social. 
        Você receberá uma notificação em breve.
      </p>
    </div>

    <p class="text-xs text-gray-400">O sistema informará a situação do candidato em breve.</p>
  </div>
</template>

<script setup>
defineProps(['formData']);
</script>