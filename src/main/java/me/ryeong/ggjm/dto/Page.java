package me.ryeong.ggjm.dto;

import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class Page<T> {

    protected Collection<T> content;

    private long totalElements;     // 총 건수
    private long totalPages;        // 총 페이지 수

    private long currentPage;       // 현재 페이지
    private int size;               // 검색 건수

    private long startPage;         // 시작 페이지
    private long endPage;           // 마지막 페이지

    private Boolean hasPrevious;    // 이전 페이지가 있는지 유무
    private Boolean hasNext;        // 다음 페이지가 있는지 유무

    public Page(long currentPage, int size, long totalElements, Collection<T> content) {
        this.content = content;

        this.totalElements = totalElements;

        this.currentPage = currentPage;
        this.size = size;

        this.totalPages = totalElements == 0 ? 1 : totalElements / size;
        if (totalElements % size > 0) {
            this.totalPages = this.totalPages + 1;
        }

        this.endPage = (long) (Math.ceil(currentPage / 10.0) * 10);
        this.startPage = endPage - 9;

        this.endPage = Math.min(this.totalPages, this.endPage);

        this.hasPrevious = currentPage > 1;
        this.hasNext = currentPage < this.totalPages;
    }

    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        return new Page<>(currentPage, size, totalElements, getConvertedContent(converter));
    }

    protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
        return this.content.stream().map(converter).collect(Collectors.toList());
    }
}
