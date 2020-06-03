package com.example.codeexp.backend;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.codeexp.backend.model.Entity;
import com.example.codeexp.backend.model.JobPresented;
import com.example.codeexp.backend.model.Period;

import java.time.LocalDateTime;

public class SampleJobListings {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static JobPresented[] getSampleJobs() {
        LocalDateTime sampleStart = LocalDateTime.now();
        LocalDateTime sampleEnd = sampleStart.plusMonths(3).plusDays(20);

        return new JobPresented[]{
                new JobPresented("abc", "Cashier", "abccorp@gmail.com", "ABC CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.ENTERPRISE, 2000, 1000, 50, sampleStart
                ),
                new JobPresented("def", "Server", "defcorp@ymail.com", "DEF CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.ENTERPRISE, 2000, 1000, 50, sampleStart
                ),
                new JobPresented("ghi", "Storage manager", "ghicorp@hotmail.com", "GHI CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.ENTERPRISE, 2000, 1000, 50, sampleStart
                ),
                new JobPresented("jkl", "Packer", "jklcorp@outlook.com", "JKL CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.ENTERPRISE, 2000, 1000, 50, sampleStart
                ),
                new JobPresented("mno", "General manager", "mnocorp@outlook.com", "MNO CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.ENTERPRISE, 2000, 1000, 50, sampleStart
                ),
                new JobPresented("pqr", "Receptionist", "pqrcorp@email.com", "PQR CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.INDIVIDUAL, 2000, 1000, 1, sampleStart
                ),
                new JobPresented("stu", "Data entry officer", "stucorp@outlook.com", "STU CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.INDIVIDUAL, 2000, 1000, 1, sampleStart
                ),
                new JobPresented("vwx", "Personal assistant", "vwxcorp@outlook.com", "VWX CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.INDIVIDUAL, 2000, 1000, 1, sampleStart
                ),
                new JobPresented("yza", "Head Chef", "yzacorp@outlook.com", "YZA CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.INDIVIDUAL, 2000, 1000, 1, sampleStart
                ),
                new JobPresented("bcd", "Food handler", "bcdcorp@outlook.com", "BCD CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.INDIVIDUAL, 2000, 1000, 1, sampleStart
                ),
                new JobPresented("efg", "Waiter", "efgcorp@outlook.com", "EFG CORPORATION PTE LTD",
                        "customer service", sampleStart, sampleEnd, Entity.INDIVIDUAL, 2000, 1000, 1, sampleStart
                )
        };
    }
}
