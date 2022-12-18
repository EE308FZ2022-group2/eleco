package com.idk.eleco.model.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionVO {

    Integer totalNum;

    Integer totalPage;

    Integer nowPage;

    Integer showNum;

    List<SearchPostVO> postArr;

}
