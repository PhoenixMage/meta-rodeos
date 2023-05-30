FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rk3568 = " file://boot.cmd"
UBOOT_ENV:rk3568 = "boot"
UBOOT_ENV_SUFFIX:rk3568 = "scr"
