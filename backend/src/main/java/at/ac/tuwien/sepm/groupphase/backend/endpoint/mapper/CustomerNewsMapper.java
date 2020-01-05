package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.CustomerNewsDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNews;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface CustomerNewsMapper {

    @Named("customerNews")
    CustomerNews custNewsDtoToCustomerNews(CustomerNewsDto customerNewsDto);

    CustomerNewsDto custNewsToCustomerNewsDto(CustomerNews customerNews);

    List<CustomerNewsDto> cNEListToCNEDtoList(List<CustomerNews> customerNews);

    List<CustomerNews> cNEDtoListToCNEList(List<CustomerNewsDto> customerNewsDtos);
}
