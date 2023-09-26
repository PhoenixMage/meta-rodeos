DEPENDS:append = " u-boot-tools-native dtc-native"

UBOOT_MKIMAGE ?= "uboot-mkimage"
UBOOT_MKIMAGE_DTCOPTS ??= ""


# Description string
FIT_DESC ?= "GRUB EFI fitImage for ${DISTRO_NAME}/${PV}/${MACHINE}"


do_assemble_fitimage() {
  bbnote "grub-efi-fitmage: do_assemble_fitimage"
	cd ${DEPLOY_DIR_IMAGE}
	fitimage_assemble fit-image.its fitImage ""
}


# $1 ... .its filename
# $2 ... fitImage name

fitimage_assemble() {
  bbnote "grub-efi-fitmage: fitimage_assemble ${1} ${2}"
  bbnote "grub-efi-fitimage: grub-efi-${EFI_BOOT_IMAGE}"
	cat << EOF > $1
/dts-v1/;         
                                                                
/ {
        description = "${FIT_DESC}";
        #address-cells = <1>;

        images {
                grub-efi {                                     
                        description = "Grub EFI";
                        data = /incbin/("grub-efi-${EFI_BOOT_IMAGE}");
                        type = "kernel_noload";
                        arch = "${GRUB_TARGET}";
                        os = "efi";
                        compression = "none";
                        load = <0>;
                        entry = <0>;
                        hash-1 {
                                algo = "sha256";
                        };
                };
                fdt-${KERNEL_DEVICETREE_BASENAME} {
                        description = "Flattened Device Tree blob";
                        data = /incbin/("${KERNEL_DEVICETREE_BASENAME}");
                        type = "flat_dt";
                        arch = "${GRUB_TARGET}";
                        compression = "none";
                         
                        hash-1 {
                                algo = "sha256";
                        };
                };
        };

        configurations {
                default = "conf-${KERNEL_DEVICETREE_BASENAME}";
                conf-${KERNEL_DEVICETREE_BASENAME} {
                        description = "1 GRUB-EFI, FDT blob";
                        kernel = "grub-efi";
                        fdt = "fdt-${KERNEL_DEVICETREE_BASENAME}"; 
                         
                        hash-1 {
                                algo = "sha256";
                        };
                };
        };
};
EOF

	${UBOOT_MKIMAGE} \
		${@'-D "${UBOOT_MKIMAGE_DTCOPTS}"' if len('${UBOOT_MKIMAGE_DTCOPTS}') else ''} \
		-f $1 \
		$2

}

addtask assemble_fitimage before do_package after do_deploy
