package br.com.investimentos.client;

import br.com.investimentos.client.dto.BrapiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "BrapiClient",
        url = "https://brapi.dev"
)
public interface BrapiClient {

    @GetMapping(value = "/api/quote/{acaoId}")
    BrapiResponseDto getAcao(@RequestParam("token") String token,
                             @PathVariable("acaoId") String acaoId);

}
