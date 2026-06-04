package com.wifi32767.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageData<T> {
    private List<T> records;
    private long total;
    private long size;
    private long current;

    public PageData() {
        this.records = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
    }


    public long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }
            return pages;
        }
    }

    public String toString() {
        return String.format("PageData{records=%s, total=%d, size=%d, current=%d}", this.records.toString(), this.total, this.size, this.current);
    }
}
