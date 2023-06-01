KBRANCH ?= "v6.4/standard/tiny/base"

KERNEL_VERSION_SANITY_SKIP="1"

#LINUX_KERNEL_TYPE = "tiny"
LINUX_KERNEL_TYPE = "standard"
#KCONFIG_MODE = "--allnoconfig"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "collabora-6.4-rc1"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

SRCREV_machine ?= "29781220497f57419637f7d52b112493849e71f1"

PV = "${LINUX_VERSION}+git${SRCPV}"

SRC_URI = " \
          git://gitlab.collabora.com/hardware-enablement/rockchip-3588/linux.git;protocol=https;branch=rk3588;name=machine \
          "

COMPATIBLE_MACHINE = "^(qemux86|qemux86-64|qemuarm64|qemuarm|qemuarmv5)$"

# Functionality flags
KERNEL_FEATURES = ""

KERNEL_DEVICETREE:qemuarmv5 = "versatile-pb.dtb"

COMPATIBLE_MACHINE:rock-pi-3 = "rock-pi-3"
COMPATIBLE_MACHINE:rock-pi-4 = "rock-pi-4"
COMPATIBLE_MACHINE:rock-5 = "rock-5"
