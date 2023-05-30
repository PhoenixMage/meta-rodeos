setenv bootargs console=ttyS2,1500000 root=/dev/mmcblk1p2 rootwait rw
fatload mmc 0:1 0x00c00800 /fitImage
imxtract 0x00c00800 kernel-1 ${kernel_addr_c}
imxtract 0x00c00800 fdt-rockchip_rk3568-rock-3a.dtb ${fdt_addr_r}
booti ${kernel_addr_c} - ${fdt_addr_r}
