# Sistema Bancário Básico em Java

Este projeto tem como objetivo simular um sistema de banco básico, com interface em Java, **sem uso de banco de dados real**. Toda a lógica de persistência é feita em memória ou por meio de DAOs simulados.

## Funcionalidades Principais

- **Cadastro de Usuário:** Permite criar novos usuários com CPF, nome, e-mail e senha (armazenada de forma segura com SHA-512).
- **Login:** Autenticação de usuários pelo CPF e senha.
- **Gestão de Cartões:** Cadastro, listagem e remoção de cartões vinculados ao usuário.
- **Gestão de Contratos:** Controle de contratos de serviços bancários associados ao usuário.
- **Operações e Investimentos:** Registro de operações financeiras, como depósitos e investimentos em criptoativos.
- **Consulta de Saldos:** Visualização do saldo da carteira do usuário.

## Estrutura das Entidades

- **Usuário:**
  - CPF, nome, celular, endereço, e-mail, senha, data de nascimento, saldo da carteira, administrador.
  - Relação com cartões cadastrados, contratos e operações.
- **Cartão:**
  - Agência, conta, data de expiração, banco associado e vínculo ao usuário.
- **Contrato:**
  - Datas de inclusão e encerramento, tipo de contrato e vínculo ao usuário.
- **Operação:**
  - Descrição, valor, tipo de criptoativo, tipo de operação e vínculo ao usuário.

## Observações Técnicas

- **Sem Banco de Dados:** Toda a lógica de persistência é simulada, sem conexão com banco de dados real.
- **Segurança:** As senhas são armazenadas utilizando hash SHA-512.
- **Arquitetura:** O projeto utiliza o padrão de camadas, com DAOs, Facades e Models.

## Como Executar

Este projeto é voltado para fins acadêmicos e de estudo. Para executar:

1. Importe o projeto em sua IDE Java (ex: NetBeans, Eclipse, IntelliJ).
2. Compile e rode a aplicação.
3. Utilize a interface para simular operações bancárias básicas.

## Estrutura de Pastas

- `src/main/java/br/com/pim/model/` — Entidades do sistema (usuário, cartão, contrato, operação, etc.)
- `src/main/java/br/com/pim/dao/` — Objetos de acesso a dados (simulados)
- `src/main/java/br/com/pim/facade/` — Fachadas de operações do sistema
- `src/main/webapp/` — Interface web (caso aplicável)

---

Projeto desenvolvido para fins didáticos.
