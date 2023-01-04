KBRANCH ?= "v5.19/standard/tiny/base"

#LINUX_KERNEL_TYPE = "tiny"
LINUX_KERNEL_TYPE = "standard"
KCONFIG_MODE = "--allnoconfig"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "5.19.17"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

SRCREV_machine ?= "2b525314c7b57eac29fe8b77a6589428e4a4f6dd"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = " \
          git://kernel.googlesource.com/pub/scm/linux/kernel/git/stable/linux;protocol=https;branch=linux-5.19.y;name=machine \
          "

SRC_URI:append:rk356x = " \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-dts-hdmi-sound.patch;sha256sum=0026ef9c5da1d7a6ca401d324f33994ce2badcef6e83dfc58c8ad28f125ed212 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-dts-hdmi.patch;sha256sum=faa49b353691e54fd765d4afe3f329dd541724a61bab65e06f7fa2e165368ce3 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-dts-pcie2x1.patch;sha256sum=b37bb7a094b220966af5462c1e27162600b8ac5e6a246cf84b46cb16d81cdd0d \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-dts-vop2.patch;sha256sum=8c1557b5c41d0fff5ccb86d97f38f3e9a4efbe879f2a7a39874c7aef5f8f0cf1 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-dts-vpu.patch;sha256sum=f21552466b5962956fcfadd3afc9b078fd24e9ea04ca33c7f0f0ef5513856258 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-pcie3-support.patch;sha256sum=05cf33d428e4867f6183b6e22a1d0af4eedf3e5f0a67d0f5a1035f2a4b6396e3 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-rga.patch;sha256sum=0ccae03dfe9c297a68d49d739a4770165b1aa70a11c570e65c9205a89bfdb6a8 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/rk356x-vop2-support.patch;sha256sum=14abf8d37b33508163a2545bb2a4790dedf6ff08dfcd2d941608f16348256e5e \
          "

SRC_URI:append:rock-pi-3a = " \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/board-rock3a-emmc-sfc.patch;sha256sum=1519bb870229ce6dd6eef2f57fe9d177e987598bbd46694654b22456ef01c9bc \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/board-rock3a-gmac1.patch;sha256sum=665e45a5da282c31e427c70ef1a26f7291e9e1bce202ea04d9f15110235df8cc \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/board-rock3a-hdmi-sound.patch;sha256sum=98cb1d2bdaf911543c587a616b39ec1bc9c3029d6d296e597f11e99ba7fadbf2 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/board-rock3a-pcie.patch;sha256sum=a3436d1b224e806af14bcfdfeb1ebe470fadb4a17fd0d05f7128dbe70d4f3ede \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/board-rock3a-usb3.patch;sha256sum=aafa86cc077011b377b96428d8392807faef7726c4ce3caaecd017f4e4311807 \
          https://github.com/armbian/build/raw/master/patch/kernel/archive/rockchip64-5.19/board-rock3a-vop2-hdmi.patch;sha256sum=4652c405bc5566586cc14b373a1d9d79b44963c479853bf7f587150cfbb65a3a \
          file://defconfig \
          "


COMPATIBLE_MACHINE = "^(qemux86|qemux86-64|qemuarm64|qemuarm|qemuarmv5)$"

# Functionality flags
KERNEL_FEATURES = ""

KERNEL_DEVICETREE:qemuarmv5 = "versatile-pb.dtb"

COMPATIBLE_MACHINE:rock-pi-3 = "rock-pi-3"
COMPATIBLE_MACHINE:rock-pi-4 = "rock-pi-4"
