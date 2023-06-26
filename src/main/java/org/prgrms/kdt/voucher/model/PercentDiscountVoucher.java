package org.prgrms.kdt.voucher.model;

import java.util.UUID;

public class PercentDiscountVoucher implements Voucher {
    private final UUID voucherId;
    private final long percent;
    private final String voucherName;
    private static int voucherNum = 0;
    private final int voucherTypeNum = 2;


    public PercentDiscountVoucher(UUID voucherId, long percent) {
        this.voucherId = voucherId;
        this.percent = percent;
        this.voucherName = getClass().getName() + voucherNum++;
    }

    @Override
    public UUID getVoucherId() {
        return voucherId;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount * (percent / 100);
    }

    @Override
    public long getBenefit() {
        return percent;
    }

    @Override
    public int getVoucherTypeNum() {
        return voucherTypeNum;
    }

    @Override
    public String getVoucherName() {
        return voucherName;
    }

}