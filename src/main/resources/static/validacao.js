document.getElementById("cadastroPetsForm")?.addEventListener("submit", function(event) {
    validarCadastro(event);
});

document.getElementById("edicaoPacienteForm")?.addEventListener("submit", function(event) {
    validarCadastro(event);
});

document.getElementById("cadastroConsultaForm")?.addEventListener("submit", function(event) {
    validarConsulta(event);
});

function validarCadastro(event) {
    let valid = true;

    const nome = document.getElementById("nome").value.trim();
    if (nome === "") {
        document.getElementById("nomeError").innerText = "O nome é obrigatório";
        valid = false;
    } else {
        document.getElementById("nomeError").innerText = "";
    }

    const especie = document.getElementById("especie").value.trim();
    if (especie === "") {
        document.getElementById("especieError").innerText = "A espécie é obrigatória";
        valid = false;
    } else {
        document.getElementById("especieError").innerText = "";
    }

    if (!valid) {
        event.preventDefault();
    }
}

function validarConsulta(event) {
    let valid = true;

    const data = document.getElementById("data").value.trim();
    const dataRegex = /^\d{2}\/\d{2}\/\d{4}$/;
    if (!dataRegex.test(data)) {
        document.getElementById("dataError").innerText = "Data inválida (dd/mm/aaaa)";
        valid = false;
    } else {
        document.getElementById("dataError").innerText = "";
    }

    const hora = document.getElementById("hora").value.trim();
    const horaRegex = /^\d{2}:\d{2}$/;
    if (!horaRegex.test(hora)) {
        document.getElementById("horaError").innerText = "Hora inválida (00:00)";
        valid = false;
    } else {
        document.getElementById("horaError").innerText = "";
    }

    const descricao = document.getElementById("descricao").value.trim();
    if (descricao === "") {
        document.getElementById("descricaoError").innerText = "A descrição é obrigatória";
        valid = false;
    } else {
        document.getElementById("descricaoError").innerText = "";
    }

    const paciente = document.getElementById("paciente").value;
    if (paciente === "") {
        document.getElementById("pacienteError").innerText = "Selecione um paciente";
        valid = false;
    } else {
        document.getElementById("pacienteError").innerText = "";
    }

    if (!valid) {
        event.preventDefault();
    }
}



