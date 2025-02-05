package org.prgrms.assignment.voucher.view;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getCommandInput() {
        return scanner.nextLine();
    }

    @Override
    public String getVoucherInput() {
        return scanner.nextLine();
    }

    @Override
    public Long getBenefit() {
        Long benefit = scanner.nextLong();
        flush();
        return benefit;
    }

    @Override
    public Long getDurationInput() {
        Long durationDate = scanner.nextLong();
        flush();
        return durationDate;
    }

    private void flush() {
        scanner.nextLine();
    }
}
