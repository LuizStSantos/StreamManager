# StreamManager API

API para gerenciar canais de streaming. Projeto desenvolvido em **Spring Boot**, usando **Spring Data JPA**, **H2 Database**, testes com **Spring Boot Test** e **MockMvc**, e documentação via **README**.

---

## Tecnologias

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5 + Mockito
- MockMvc

---

## Configuração do Projeto

### Clonar o repositório

```bash
git clone https://github.com/seu-usuario/StreamManager.git
cd StreamManager
```

### Build e Run

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## Endpoints

### Listar todos os canais

**GET** `/channels`

**Exemplo de resposta:**

```json
[
  {
    "id": 1,
    "name": "Canal Teste",
    "description": null,
    "streamUrl": null
  }
]
```

### Criar um novo canal

**POST** `/channels`

**Body (JSON):**

```json
{
  "name": "Canal Novo",
  "description": "Descrição do canal",
  "streamUrl": "http://exemplo.com/novo"
}
```

**Exemplo de resposta:**

```json
{
  "id": 2,
  "name": "Canal Novo",
  "description": "Descrição do canal",
  "streamUrl": "http://exemplo.com/novo"
}
```

### Buscar canal por ID

**GET** `/channels/{id}`

**Exemplo de resposta:**

```json
{
  "id": 1,
  "name": "Canal Teste",
  "description": null,
  "streamUrl": null
}
```

### Atualizar canal

**PUT** `/channels/{id}`

**Body (JSON):**

```json
{
  "name": "Canal Atualizado",
  "description": "Descrição atualizada",
  "streamUrl": "http://exemplo.com/atualizado"
}
```

**Exemplo de resposta:**

```json
{
  "id": 1,
  "name": "Canal Atualizado",
  "description": "Descrição atualizada",
  "streamUrl": "http://exemplo.com/atualizado"
}
```

### Deletar canal

**DELETE** `/channels/{id}`

**Exemplo de resposta:**

```json
{
  "message": "Canal deletado com sucesso"
}
```

---

## Testes

### Rodar os testes

```bash
mvn test
```

Inclui:

- Testes de controller com **MockMvc**
- Testes de integração com **Spring Boot Test**
- Testes de repositório com **H2 Database**

---

## Observações

- Todos os endpoints retornam **JSON**.
- Códigos HTTP retornados:
    - `200 OK` → Sucesso
    - `201 Created` → Criação bem-sucedida
    - `404 Not Found` → Recurso não encontrado
    - `400 Bad Request` → Dados inválidos

---

## License

MIT License