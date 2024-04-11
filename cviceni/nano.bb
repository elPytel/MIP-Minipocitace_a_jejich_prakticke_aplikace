DESCRIPTION = "Recipe to build the 'nano' editor"
PV = "6.0"
SRC_URI = "http://www.nano-editor.org/dist/v6/${P}.tar.gz"
# we need to export these variable only in this minimal lab setup
export DL_DIR
export P
export WORKDIR

SRC_URI[sha256sum] = "3c22971432503c0f84e1c0fbe8a04d35acc131034f8a03fdfdbca77a99208e81"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

python do_fetch() {
    bb.note("Downloading source tarball from ${SRC_URI} ...")
    src_uri = (d.getVar('SRC_URI', True) or "").split()
    if len(src_uri) == 0:
        bb.fatal("Empty URI")
    try:
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.download()
    except bb.fetch2.BBFetchException:
        bb.fatal("Could not fetch source tarball.")
    
    bb.note("Download successful.")
}
addtask fetch before do_build

python do_unpack() {
    bb.note("Unpacking source tarball ...")
    os.system("tar x -C ${WORKDIR} -f ${DL_DIR}/${P}.tar.gz")
    bb.note("Unpacked source tarball.")

}
addtask unpack before do_build after do_fetch