package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.CustomerNewsDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.CustomerNewsMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNews;
import at.ac.tuwien.sepm.groupphase.backend.service.CustomerNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customernews")
@Slf4j
public class CustomerNewsEndpoint {

    private CustomerNewsMapper customerNewsMapper;
    private CustomerNewsService customerNewsService;

    public CustomerNewsEndpoint(CustomerNewsMapper customerNewsMapper,
                                CustomerNewsService customerNewsService) {
        this.customerNewsMapper = customerNewsMapper;
        this.customerNewsService = customerNewsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find all news entries for all events", authorizations = {@Authorization(value = "apiKey")})
    public List<CustomerNewsDto> getAll() {
        List<CustomerNews> resultList = this.customerNewsService.findAll();
        log.info("The resultList has {} elements.", resultList.size());
        return this.customerNewsMapper.cNEListToCNEDtoList(resultList);
    }

    /**
     * @PutMapping
     *     @ResponseStatus(HttpStatus.OK)
     *     @ApiOperation(value = "Update a news to read", authorizations = {@Authorization(value = "apiKey")})
     *     public CustomerNewsDto setRead(@RequestBody CustomerNewsDto customerNewsDto) {
     *         return this.customerNewsMapper.customerNewsToCustomerNewsDto(
     *             this.customerNewsService.setRead(this.customerNewsMapper.
     *                 customerNewsDtoToCustomerNews(customerNewsDto)));
     *     }
     *
     */

}