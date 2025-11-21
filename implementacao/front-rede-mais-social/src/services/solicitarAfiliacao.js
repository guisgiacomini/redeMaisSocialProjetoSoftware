const BASE_URL = 'http://localhost:8080/afiliacao';


async function request(endpoint, method = 'GET', body = null) {
    const options = {
        method,
        headers: {
            'Content-Type': 'application/json',
        },
    };

    if (body) {
        options.body = JSON.stringify(body);
        console.log(options.body);
    }

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, options);
        
        // Tenta fazer o parse do JSON, se falhar (ex: retorno string simples), pega o texto
        const isJson = response.headers.get('content-type')?.includes('application/json');
        const data = isJson ? await response.json() : await response.text();

        console.log(response);

        if (!response.ok) {
            // Lança um erro com a mensagem vinda do backend (AfiliacaoResponseDTO ou String)
            const errorMessage = (data && data.mensagem) || (typeof data === 'string' ? data : 'Erro na requisição');
            throw new Error(errorMessage);
        }

        return data;
    } catch (error) {
        console.error(`Erro na API [${endpoint}]:`, error);
        throw error;
    }
}

export async function iniciarAfiliacao(dto) {
    // dto deve conter: { email, documento, tipoPessoa }
    return await request('/iniciar-afiliacao', 'POST', dto);
}

export async function submeterDadosIdentificacao(dto) {
    // dto deve conter: { pedidoId, nome, enderecoResidencial, enderecoComercial, email, documento, tipoPessoa }
    return await request('/submeter-dados-identificacao', 'POST', dto);
}

export async function registrarPerfilCompleto(habilidadesInteresses) {
    // habilidadesInteresses deve conter: { email, habilidades: [], interesses: [] }
    // Nota: No seu controller, 'habilidades' e 'interesses' parecem ser Listas de Strings ou Objetos, ajustar conforme o DTO Java.
    return await request('/registrar-perfil-completo', 'POST', habilidadesInteresses);
}

// --- 4. Registrar Aceite ---
// Endpoint: POST /afiliacao/registar-aceite/{idCandidato}/{idPedido}
export async function registrarAceite(idCandidato, idPedido, condicaoTexto) {
    // O corpo da requisição é uma String pura com o texto da condição
    return await request(`/registar-aceite/${idCandidato}/${idPedido}`, 'POST', condicaoTexto);
}