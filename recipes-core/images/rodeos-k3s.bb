require recipes-core/images/core-image-minimal.bb

DESCRIPTION = "A small image with just enough OS to run k3s/k8s"

#KUBERNETES_CRI ?= "cri-o"

IMAGE_INSTALL += "k3s bash zsh git vim python3-ansible logrotate net-tools util-linux-agetty kmod openssh ntp curl kernel-modules less cloud-init cgroup-lite"

