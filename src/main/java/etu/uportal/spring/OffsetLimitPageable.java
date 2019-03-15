package etu.uportal.spring;

import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class OffsetLimitPageable extends PageRequest {
    private int offset;

    @ApiParam(hidden = true)
    private int pageNumber;

    @ApiParam(hidden = true)
    private int pageSize;

    @ApiParam(defaultValue = "10", example = "20")
    private int limit;

    @ApiParam(hidden = true)
    private Sort sort;


    public OffsetLimitPageable(int offset, int limit) {
        super(offset, limit);
        this.limit = limit;
        this.offset = offset;
    }

    public OffsetLimitPageable() {
        super(0, 10);
    }


    public int getLimit() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return this.offset;
    }
}
