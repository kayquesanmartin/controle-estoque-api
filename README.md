# 🔹 API de Controle de Estoque
## 📌 Objetivo: Criar um sistema para controle de estoque de produtos, registrando entradas e saídas.

***

### 📌 Conceitos Praticados:
* ✔️ CRUD completo de produtos
* ✔️ Gerenciamento de quantidade disponível
* ✔️ Registro de histórico de movimentações

### 📌 Rotas:

* **POST /produtos** → Cadastrar produto
* **GET /produtos** → Listar todos
* **PUT /produtos/{id}/entry** → Adicionar estoque
* **PUT /produtos/{id}/output** → Remover estoque
* **DELETE /produtos/{id}** → Remover produto
