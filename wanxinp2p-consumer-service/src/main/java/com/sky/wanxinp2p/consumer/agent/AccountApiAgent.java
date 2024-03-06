package com.sky.wanxinp2p.consumer.agent;

import com.sky.wanxinp2p.account.model.AccountDTO;
import com.sky.wanxinp2p.account.model.AccountRegisterDTO;
import com.sky.wanxinp2p.common.domain.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account-service")
public interface AccountApiAgent {

    /**
     * 用户注册
     * @param accountRegisterDTO
     * @return
     */
    @PostMapping(value = "/account/l/accounts")
    RestResponse<AccountDTO> register(@RequestBody AccountRegisterDTO
                                              accountRegisterDTO);
}
