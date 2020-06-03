package com.example.codeexp.backend;

import com.example.codeexp.backend.model.Period;

import java.time.LocalDateTime;

public class SampleJobListings {
    public static Job[] getSampleJobs() {
        LocalDateTime sampleStart = LocalDateTime.now();
        LocalDateTime sampleEnd = sampleStart.plusMonths(3).plusDays(20);

        return new Job[]{
                new Job("abc", "Cashier", "abccorp@gmail.com", "ABC CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("def", "Server", "defcorp@ymail.com", "DEF CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("ghi", "Storage manager", "ghicorp@hotmail.com", "GHI CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("jkl", "Packer", "jklcorp@outlook.com", "JKL CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("mno", "General manager", "mnocorp@outlook.com", "MNO CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("pqr", "Receptionist", "pqrcorp@email.com", "PQR CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("stu", "Data entry officer", "stucorp@outlook.com", "STU CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("vwx", "Personal assistant", "vwxcorp@outlook.com", "VWX CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("yza", "Head Chef", "yzacorp@outlook.com", "YZA CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("bcd", "Food handler", "bcdcorp@outlook.com", "BCD CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                ),
                new Job("efg", "Waiter", "efgcorp@outlook.com", "EFG CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd
                )
        };
    }
}
