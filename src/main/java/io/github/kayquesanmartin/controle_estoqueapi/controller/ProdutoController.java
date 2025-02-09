package io.github.kayquesanmartin.controle_estoqueapi.controller;

import io.github.kayquesanmartin.controle_estoqueapi.model.Produtos;
import io.github.kayquesanmartin.controle_estoqueapi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produtos createProduto(@RequestBody Produtos produto) {
        return produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produtos> getAllProdutos() {
        return produtoRepository.findAll();
    }

    @PutMapping("/{id}/entry")
    public Produtos productEntry(@PathVariable UUID id, @RequestBody Produtos produto) {

        // Buscar produto existente.
        Produtos existingProduct = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        // Validar se a quantidade é maior do que zero
        if(produto.getQuantity() <= 0) {
            throw new RuntimeException("A quantidade adicionada deve ser maior que zero!");
        }

        // Soma a quantidade que já está no estoque com a da entrada
        existingProduct.setQuantity(existingProduct.getQuantity() + produto.getQuantity());

        // Retorna o produto atualizado
        return produtoRepository.save(existingProduct);
    }

    @PutMapping("/{id}/output")
    public Produtos productOutput(@PathVariable UUID id, @RequestBody Produtos produto) {

        // Buscar produto existente.
        Produtos existingProduct = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        // Pega a quantidade de saída passada na requisição
        Integer outputQuantity = produto.getQuantity();

        // Valida se a quantidade de saída é maior que zero
        if(outputQuantity == null || outputQuantity <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero!");
        }

        // Valida se a quantidade de retirada é maior do que possui no estoque
        if(existingProduct.getQuantity() < outputQuantity) {
            throw new RuntimeException("Estoque insuficiente para essa operação!");
        }

        // Subtrai a quantidade de saída do estoque
        existingProduct.setQuantity(existingProduct.getQuantity() - outputQuantity);

        // Salva o produto com o novo estoque
        return produtoRepository.save(existingProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable UUID id) {
        produtoRepository.deleteById(id);
    }

}
